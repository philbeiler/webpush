package com.springboot.webpush.service;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.ExecutionException;

import org.apache.http.util.EntityUtils;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.webpush.controller.model.WebPushMessage;
import com.springboot.webpush.controller.model.WebPushSubscription;

import nl.martijndwars.webpush.Encoding;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Urgency;

@Service
public class NotificationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static final int ONE_DAY_DURATION_IN_SECONDS = 86400;
	private static int DEFAULT_TTL = 28 * ONE_DAY_DURATION_IN_SECONDS;

	private final SubscriptionService subscriptionService;
	private final ObjectMapper objectMapper;
	private final PushService pushService;

	public NotificationService(SubscriptionService subscriptionService, ObjectMapper objectMapper,
			final KeyPairService keyPairService) {
		super();
		this.subscriptionService = subscriptionService;
		this.objectMapper = objectMapper;

		PushService pushService;
		try {
			var clientKeyPair = keyPairService.generate();
			pushService = new PushService() //
					.setPublicKey(clientKeyPair.getPublicKeyAsString()) //
					.setPrivateKey(clientKeyPair.getPrivateKeyAsString()) //
					.setSubject("mailto:admin@domain.com");
		} catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e) {
			e.printStackTrace();
			pushService = new PushService();
		}

		this.pushService = pushService;
	}

	public void notifyAll(WebPushMessage message) {

		LOGGER.info("Notificaiton Message [{}]", message);
		for (WebPushSubscription subscription : subscriptionService.getSubscriptions()) {
			LOGGER.info("Notifying [{}]", subscription.getEndpoint());

			try {
				var notification = new Notification(subscription.get(), objectMapper.writeValueAsString(message),
						Urgency.HIGH);
				var rc = pushService.send(notification, Encoding.AES128GCM);

				LOGGER.info("{}", EntityUtils.toString(rc.getEntity()));

			} catch (IOException | GeneralSecurityException | JoseException | ExecutionException |

					InterruptedException e) {
				LOGGER.error("Unabled to send notification", e);

			}

		}
	}

}
