package com.springboot.webpush.controller.model;

public class SubscriptionKey {
    private String p256dh;
    private String auth;

    /**
     * @return the p256dh
     */
    public String getP256dh() {
        return p256dh;
    }

    /**
     * @param p256dh the p256dh to set
     */
    public void setP256dh(final String p256dh) {
        this.p256dh = p256dh;
    }

    /**
     * @return the auth
     */
    public String getAuth() {
        return auth;
    }

    /**
     * @param auth the auth to set
     */
    public void setAuth(final String auth) {
        this.auth = auth;
    }

}
