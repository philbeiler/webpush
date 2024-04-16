package com.springboot.webpush.service;

import java.lang.invoke.MethodHandles;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.springboot.webpush.controller.model.WebPushSubscription;

@Service
public class SubscriptionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private Map<String, WebPushSubscription> subscriptions = new ConcurrentHashMap<>();

	public void subscribe(WebPushSubscription subscription) {
		var sub = subscriptions.put(subscription.getEndpoint(), subscription);
		if (sub == null) {
			LOGGER.info("Subscription added [{}]", subscription.getEndpoint());
		} else {
			LOGGER.info("Subscription updated [{}]", subscription.getEndpoint());
		}
	}

	public void unsubscribe(WebPushSubscription subscription) {
		var sub = subscriptions.remove(subscription.getEndpoint());
		if (sub == null) {
			LOGGER.error("Subscription not found [{}]", subscription.getEndpoint());
		} else {
			LOGGER.info("Subscription removed [{}]", subscription.getEndpoint());
		}
	}

	public Collection<WebPushSubscription> getSubscriptions() {
		return subscriptions.values();
	}
}
