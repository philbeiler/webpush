package com.springboot.webpush.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.springboot.webpush.controller.model.ClientKeyPair;
import com.springboot.webpush.controller.model.PushKey;
import com.springboot.webpush.exception.PushkeyNotFoundException;
import com.springboot.webpush.impl.Notification;
import com.springboot.webpush.impl.PushService;
import com.springboot.webpush.impl.Subscription;

@Service
public class NotificationPushService {
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationPushService.class);
	private PushKey pushkey;
	private final ClientKeyPair clientKeyPair;

	public NotificationPushService(final KeyPairService keyPairService) {
		clientKeyPair = keyPairService.generate();
	}

	/**
	 * @param pushkey the pushkey to set
	 */
	public void setPushkey(final PushKey pushkey) {
		Assert.notNull(pushkey, "PushKey must not be empty");
		this.pushkey = pushkey;
	}

	/**
	 * @return the pushkey
	 */
	public Optional<PushKey> getPushkey() {
		return Optional.ofNullable(pushkey);
	}

	public void send(final String payLoad) {
		PushService pushService;
		try {
			pushService = new PushService() //
					.setPublicKey(clientKeyPair.getPublicKey()) //
					.setPrivateKey(clientKeyPair.getPrivateKey()) //
					.setSubject("mailto:admin@domain.com");
			final Notification notification = new Notification(getSubscription(), payLoad);
			final HttpResponse response = pushService.send(notification);
			LOGGER.info("{}", response);
		}
		catch (GeneralSecurityException | IOException | JoseException | ExecutionException | InterruptedException e) {
			LOGGER.info("Error", e);
		}
	}

	private Subscription getSubscription() {
		if (pushkey == null) {
			throw new PushkeyNotFoundException();
		}
		return new Subscription(pushkey.getEndpoint(),
				new Subscription.Keys(pushkey.getKeys().getP256dh(), pushkey.getKeys().getAuth()));
	}

}
