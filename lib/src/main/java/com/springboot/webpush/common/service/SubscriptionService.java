package com.springboot.webpush.common.service;

import java.lang.invoke.MethodHandles;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.springboot.webpush.common.api.PushSubscription;

@Service
public class SubscriptionService {
    private static final Logger                    LOGGER        = LoggerFactory
            .getLogger(MethodHandles.lookup().lookupClass());
    private final Map<String, PushSubscription> subscriptions = new ConcurrentHashMap<>();

    public void subscribe(final PushSubscription subscription) {
        final var existingSubscription = subscriptions.put(subscription.getEndpoint(), subscription);
        if (existingSubscription == null) {
            LOGGER.info("Subscription added [{}]", subscription.getEndpoint());
        }
        else {
            LOGGER.info("Subscription updated [{}]", subscription.getEndpoint());
        }
    }

    public boolean unsubscribe(final PushSubscription subscription) {
        final var existingSubscription = subscriptions.remove(subscription.getEndpoint());
        if (existingSubscription == null) {
            LOGGER.error("Subscription not found [{}]", subscription.getEndpoint());
        }
        else {
            LOGGER.info("Subscription removed [{}]", subscription.getEndpoint());
        }
        return existingSubscription != null;
    }

    public boolean hasSubscriptions() {
        return !subscriptions.isEmpty();
    }

    public Collection<PushSubscription> getSubscriptions() {
        return subscriptions.values();
    }
}
