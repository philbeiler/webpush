package com.springboot.webpush.common.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The {@link KeyStore} is the storage class for the key information used to encrypt push messages.
 */
public class KeyStore {
    private String privateKey;
    private String publicKey;

    /**
     * Default constructor.
     */
    public KeyStore() {
        super();
    }

    /**
     * Constructs a new {@link KeyStore} instance.
     *
     * @param privateKey The private key
     * @param publicKey  The public key
     */
    public KeyStore(final String privateKey,
                    final String publicKey) {
        this.privateKey = privateKey;
        this.publicKey  = publicKey;
    }

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

    /**
     * Ensures that the instance has both a public and private key.
     *
     * @return Returns TRUE if either of the private and public key are null, otherwise FALSE.
     */
    @JsonIgnore
    public boolean isInvalid() {
        return privateKey == null || publicKey == null;
    }

    /**
     * Ensures that the instance has both a public and private key.
     *
     * @return Returns TRUE if both the private and public key are present, otherwise FALSE.
     */
    @JsonIgnore
    public boolean isValid() {
        return !isInvalid();
    }
}
