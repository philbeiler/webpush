package com.springboot.webpush.exception;

/**
 * CLient KeyPair Exception exception wrapper.
 */
public class ClientKeyPairException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new {@link ClientKeyPairException} instance.
     */
    public ClientKeyPairException() {
        super("ClientKeyPairException");
    }

}
