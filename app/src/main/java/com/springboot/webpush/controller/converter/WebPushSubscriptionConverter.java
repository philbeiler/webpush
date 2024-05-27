package com.springboot.webpush.controller.converter;

import com.springboot.webpush.common.api.PushSubscription;
import com.springboot.webpush.controller.model.WebPushSubscription;
import com.springboot.webpush.security.SpringSecurityHelper;

/**
 * The {@link WebPushSubscriptionConverter} converts external, user facing API data to the required internal API format.
 */
public final class WebPushSubscriptionConverter {

    /**
     * Converts a {@link WebPushSubscription} instance to a {@link PushSubscription} instance.
     *
     * @param model The external {@link WebPushSubscription} instance.
     * @return The internal {@link PushSubscription} instance.
     */
    public static PushSubscription convert(final WebPushSubscription model) {

        final var currentPrincipalName = SpringSecurityHelper.get();
        return new PushSubscription(currentPrincipalName, model.getEndpoint(), model.getExpirationTime(),
                new com.springboot.webpush.common.api.Key(model.getKeys().getP256dh(), model.getKeys().getAuth()));
    }
}
