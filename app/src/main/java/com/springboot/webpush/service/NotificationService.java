package com.springboot.webpush.service;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.security.GeneralSecurityException;
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
import java.security.KeyPair;
import java.security.PublicKey;

@Service
public class NotificationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final SubscriptionService subscriptionService;
	private final ObjectMapper objectMapper;
	private PushService pushService;
	private final KeyPair keyPair;

	public NotificationService(SubscriptionService subscriptionService, ObjectMapper objectMapper,
			final KeyPairService keyPairService) {
		super();
		this.subscriptionService = subscriptionService;
		this.objectMapper = objectMapper;
		this.keyPair = keyPairService.generate().getKeyPair();

		/*
		 * this.pushService = new PushService(this.keyPair) //
		 * .setSubject("mailto:admin@domain.com");
		 */
		try {
			this.pushService = new PushService(
					"BJ3kKgvZR34Wt-dJMG9L7Lh03ISRlWueIgg-Vp5V5oYsDPr47_szfNsxxLQrghPtuN3XaI6A3FVZVZuVa7LZOFY",
					"pMicnxO7KOCxUjbA6--6S1R5NCbnyE4A0-EZxravdGA").setSubject("mailto:admin@domain.com");
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PublicKey getPublicKey() {
		return this.keyPair.getPublic();
	}

	public void notifyAll(WebPushMessage message) {

		LOGGER.info("Notificaiton Message [{}]", message);
		for (WebPushSubscription subscription : subscriptionService.getSubscriptions()) {
			LOGGER.info("Notifying [{}]", subscription.getEndpoint());

			try {
				LOGGER.info("creating notificatoin");
				// The payload here must be of the form { notification: Notification }
				// Where notification is defined here:
				// https://developer.mozilla.org/en-US/docs/Web/API/Notification
				var notification = new Notification(subscription.get(),
						"{\"notification\":{\"title\":\"hello world\"}}",
						Urgency.HIGH);
				/*
				 * var notification = new Notification(subscription.get(),
				 * objectMapper.writeValueAsString(message),
				 * Urgency.HIGH);
				 */
				var rc = pushService.send(notification, Encoding.AES128GCM);

				LOGGER.info("{}", EntityUtils.toString(rc.getEntity()));

			} catch (IOException | GeneralSecurityException | JoseException | ExecutionException |

					InterruptedException e) {
				LOGGER.error("Unabled to send notification", e);

			}

		}
	}

}
