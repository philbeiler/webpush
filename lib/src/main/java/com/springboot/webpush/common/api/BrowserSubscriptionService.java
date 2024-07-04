package com.springboot.webpush.common.api;

import java.util.Collection;

/**
 * The {@link BrowserSubscriptionService} is used to manage all of the browser subscriptions for the application.
 */
public interface BrowserSubscriptionService {

    /**
     * The subscribe method is use to subscribe to the message push service.
     *
     * @param subscription The {@link PushSubscription} instance, from the user's browser.
     */
    void subscribe(PushSubscription subscription);

    /**
     * The unsubscribe method is used to unsubscribe the user from the message push service.
     *
     * @param subscription The {@link PushSubscription} instance, from the user's browser.
     * @return Returns TRUE if the use we successfully unsubscribed from the service, otherwise FALSE
     */
    boolean unsubscribe(PushSubscription subscription);

    /**
     * Convenience method to determine if anyone is currently subscribed to the message service.
     *
     * @return Returns TRUE if there are active subscriptions, otherwise FALSE
     */
    boolean hasSubscriptions();

    /**
     * Returns all active subscriptions.
     *
     * @return Returns a {@link Collection} of {@link PushSubscription} instances (subscriptions).
     */
    Collection<PushSubscription> getSubscriptions();

}
