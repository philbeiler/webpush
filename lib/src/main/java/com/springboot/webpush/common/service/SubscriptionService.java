package com.springboot.webpush.common.service;

import java.lang.invoke.MethodHandles;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.springboot.webpush.common.api.PushSubscription;

/**
 * The {@link SubscriptionService} manages the user's application subscriptions, allowing them to subscribe and
 * unsubscribe to the message push service.
 */
@Service
public class SubscriptionService {
    private static final Logger                 LOGGER                 = LoggerFactory
            .getLogger(MethodHandles.lookup().lookupClass());

    private final Map<String, PushSubscription> subscriptionsByBrowser = new ConcurrentHashMap<>();

    /**
     * The subscribe method is use to subscribe to the message push service.
     *
     * @param subscription The {@link PushSubscription} instance, from the user's browser.
     */
    public void subscribe(final PushSubscription subscription) {
        final var existingSubscription = subscriptionsByBrowser.put(subscription.getEndpoint(), subscription);
        if (existingSubscription == null) {
            LOGGER.info("Subscription added [{}]", subscription.getEndpoint());
        }
        else {
            LOGGER.info("Subscription updated [{}]", subscription.getEndpoint());
        }
    }

    /**
     * The unsubscribe method is used to unsubscribe the user from the message push service.
     *
     * @param subscription The {@link PushSubscription} instance, from the user's browser.
     * @return Returns TRUE if the use we successfully unsubscribed from the service, otherwise FALSE
     */
    public boolean unsubscribe(final PushSubscription subscription) {
        final var existingSubscription = subscriptionsByBrowser.remove(subscription.getEndpoint());
        if (existingSubscription == null) {
            LOGGER.error("Subscription not found [{}]", subscription.getEndpoint());
        }
        else {
            LOGGER.info("Subscription removed [{}]", subscription.getEndpoint());
        }
        return existingSubscription != null;
    }

    /**
     * Convenience method to determin if anyone is currently subscribed to the message service.
     *
     * @return Returns TRUE if there are active subscriptions, otherwise FALSE
     */
    public boolean hasSubscriptions() {
        return !subscriptionsByBrowser.isEmpty();
    }

    /**
     *
     * @return Returns a {@link Collection} of {@link PushSubscription} instances (subscriptions).
     */
    public Collection<PushSubscription> getSubscriptions() {
        return subscriptionsByBrowser.values();
    }
}
