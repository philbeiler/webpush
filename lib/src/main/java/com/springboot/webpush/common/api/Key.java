package com.springboot.webpush.common.api;

/**
 * The {@link Key} authentication data, to be used for encrypting the push messages from the backend service to the
 * browser specific endpoints (URI).
 */
public class Key {
    private final String p256dh;
    private final String auth;

    /**
     * Constructs a new {@link Key} instance from the user's browser data.
     *
     * @param p256dh The Diffie-Hellman key
     * @param auth   The authentication key
     */
    public Key(final String p256dh, final String auth) {
        this.p256dh = p256dh;
        this.auth   = auth;
    }

    /**
     * @return The p256dh Diffie-Hellman key
     */
    public String getP256dh() {
        return p256dh;
    }

    /**
     * @return The auth browser authentication key
     */
    public String getAuth() {
        return auth;
    }

}
