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
     * Returns the privateKey property.
     *
     * @return The privateKey property.
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * Sets the value of the privateKey property.
     *
     * @param privateKey The value to set the privateKey property.
     */
    public void setPrivateKey(final String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * Returns the publicKey property.
     *
     * @return The publicKey property.
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * Sets the value of the publicKey property.
     *
     * @param publicKey The value to set the publicKey property.
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
