package com.springboot.webpush.common.api;

import nl.martijndwars.webpush.Subscription;
import nl.martijndwars.webpush.Subscription.Keys;

public class PushSubscription {
    private final String  endpoint;
    private final Integer expirationTime;
    private final Key     keys;

    /**
     * @param endpoint
     * @param expirationTime
     * @param keys
     */
    public PushSubscription(final String endpoint, final Integer expirationTime, final Key keys) {
        this.endpoint       = endpoint;
        this.expirationTime = expirationTime;
        this.keys           = keys;
    }

    /**
     * @return the endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @return the expirationTime
     */
    public Integer getExpirationTime() {
        return expirationTime;
    }

    /**
     * @return the keys
     */
    public Key getKeys() {
        return keys;
    }

    public Subscription get() {
        return new Subscription(endpoint, new Keys(keys.getP256dh(), keys.getAuth()));
    }
}
