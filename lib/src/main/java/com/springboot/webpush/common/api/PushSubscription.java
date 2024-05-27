package com.springboot.webpush.common.api;

import nl.martijndwars.webpush.Subscription;
import nl.martijndwars.webpush.Subscription.Keys;

/**
 * The browser specific push notification subscription.
 */
public class PushSubscription {
    private final String  username;
    private final String  endpoint;
    private final Integer expirationTime;
    private final Key     keys;

    /**
     * Constructs a new {@link PushSubscription} instance using values from the user's specific browser.
     *
     * @param username       The user name associated with this subscription.
     * @param endpoint       The browser creator's specific push notification service URI.
     * @param expirationTime The expiration time of the subscription information.
     * @param keys           The keys for encrypting the push message.
     */
    public PushSubscription(final String username, final String endpoint, final Integer expirationTime,
            final Key keys) {
        this.username       = username;
        this.endpoint       = endpoint;
        this.expirationTime = expirationTime;
        this.keys           = keys;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return The browser specific push notification endpoint URI.
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @return The expirationTime of the subscription.
     */
    public Integer getExpirationTime() {
        return expirationTime;
    }

    /**
     * @return The keys associated with the subscription, used to encrypt the push message.
     */
    public Key getKeys() {
        return keys;
    }

    /**
     * @return Returns the internal API {@link Subscription} instance.
     */
    public Subscription get() {
        return new Subscription(endpoint, new Keys(keys.getP256dh(), keys.getAuth()));
    }
}
