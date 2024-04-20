package com.springboot.webpush.controller.model;

import com.springboot.webpush.common.api.PushSubscription;

public class WebPushSubscription {
    private String          endpoint;
    private Integer         expirationTime;
    private SubscriptionKey keys;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(final String endpoint) {
        this.endpoint = endpoint;
    }

    public Integer getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(final Integer expirationTime) {
        this.expirationTime = expirationTime;
    }

    public SubscriptionKey getKeys() {
        return keys;
    }

    public void setKeys(final SubscriptionKey keys) {
        this.keys = keys;
    }

    public PushSubscription get() {
        return new PushSubscription(endpoint, expirationTime,
                new com.springboot.webpush.common.api.Key(keys.getP256dh(), keys.getAuth()));
    }

}
