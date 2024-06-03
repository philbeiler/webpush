package com.springboot.webpush.controller.model;

/**
 * The {@link WebPushSubscription} class matches the external browsers API for capturing the associated Push
 * Notification service supported by the browser.
 */
public class WebPushSubscription {
    private String          endpoint;
    private Integer         expirationTime;
    private SubscriptionKey keys;

    /**
     * @return the endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @param endpoint the endpoint to set
     */
    public void setEndpoint(final String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @return the expirationTime
     */
    public Integer getExpirationTime() {
        return expirationTime;
    }

    /**
     * @param expirationTime the expirationTime to set
     */
    public void setExpirationTime(final Integer expirationTime) {
        this.expirationTime = expirationTime;
    }

    /**
     * @return the keys
     */
    public SubscriptionKey getKeys() {
        return keys;
    }

    /**
     * @param keys the keys to set
     */
    public void setKeys(final SubscriptionKey keys) {
        this.keys = keys;
    }

}
