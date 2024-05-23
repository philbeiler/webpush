package com.springboot.webpush.common.api;

public class Key {
    private final String p256dh;
    private final String auth;

    /**
     * @param p256dh
     * @param auth
     */
    public Key(final String p256dh, final String auth) {
        this.p256dh = p256dh;
        this.auth   = auth;
    }

    /**
     * @return the p256dh
     */
    public String getP256dh() {
        return p256dh;
    }

    /**
     * @return the auth
     */
    public String getAuth() {
        return auth;
    }

}
