package com.springboot.webpush.service;

import java.lang.invoke.MethodHandles;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.springboot.webpush.common.api.BrowserSubscriptionService;
import com.springboot.webpush.common.api.PushSubscription;

/**
 * The {@link InMemoryBrowserSubscriptionService} manages the user's application subscriptions, allowing them to
 * subscribe and unsubscribe to the message push service.
 */
@Service
public class InMemoryBrowserSubscriptionService implements BrowserSubscriptionService {
    private static final Logger                 LOGGER                 = LoggerFactory
            .getLogger(MethodHandles.lookup().lookupClass());

    private final Map<String, PushSubscription> subscriptionsByBrowser = new ConcurrentHashMap<>();

    /**
     * The subscribe method is use to subscribe to the message push service.
     *
     * @param subscription The {@link PushSubscription} instance, from the user's browser.
     */
    @Override
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
    @Override
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
     * Convenience method to determine if anyone is currently subscribed to the message service.
     *
     * @return Returns TRUE if there are active subscriptions, otherwise FALSE
     */
    @Override
    public boolean hasSubscriptions() {
        return !subscriptionsByBrowser.isEmpty();
    }

    /**
     *
     * @return Returns a {@link Collection} of {@link PushSubscription} instances (subscriptions).
     */
    @Override
    public Collection<PushSubscription> getSubscriptions() {
        return subscriptionsByBrowser.values();
    }
}
