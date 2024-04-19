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

@Service
public class NotificationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final SubscriptionService subscriptionService;
	private PushService pushService;
	private final VAPIDService vapidService;
	private final ObjectMapper objectMapper;

	public NotificationService(final SubscriptionService subscriptionService, final VAPIDService vapidService,
			final ObjectMapper objectMapper) {
		super();
		this.subscriptionService = subscriptionService;
		this.vapidService = vapidService;
		this.objectMapper = objectMapper;

		try {
			this.pushService = new PushService(vapidService.getPublicKey(), vapidService.getPrivateKey())
					.setSubject("mailto:admin@domain.com");
		}
		catch (final GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void notifyAll(final WebPushMessage message) {

		LOGGER.info("Notificaiton Message [{}]", message);
		for (final WebPushSubscription subscription : subscriptionService.getSubscriptions()) {
			LOGGER.info("Notifying [{}]", subscription.getEndpoint());

			try {
				LOGGER.info("creating notificatoin");
				// The payload here must be of the form { notification: Notification }
				// Where notification is defined here:
				// https://developer.mozilla.org/en-US/docs/Web/API/Notification

				final var notification = new Notification(subscription.get(), objectMapper.writeValueAsString(message),
						Urgency.HIGH);
				/*
				 * var notification = new Notification(subscription.get(),
				 * objectMapper.writeValueAsString(message), Urgency.HIGH);
				 */
				final var rc = pushService.send(notification, Encoding.AES128GCM);

				LOGGER.info("{}", EntityUtils.toString(rc.getEntity()));

			}
			catch (IOException | GeneralSecurityException | JoseException | ExecutionException |

					InterruptedException e) {
				LOGGER.error("Unabled to send notification", e);

			}

		}
	}

}
