package com.springboot.webpush.common.api;

public class KeyStore {
    private String privateKey;
    private String publicKey;

    /**
     * @return the privateKey
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * @return the publicKey
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * @param privateKey the privateKey to set
     */
    public void setPrivateKey(final String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * @param publicKey the publicKey to set
     */
    public void setPublicKey(final String publicKey) {
        this.publicKey = publicKey;
    }

    public boolean invalid() {
        return privateKey == null || publicKey == null;
    }

    public boolean valid() {
        return !invalid();
    }
}
