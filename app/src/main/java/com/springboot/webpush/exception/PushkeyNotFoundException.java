package com.springboot.webpush.exception;

/**
 * PushkeyNotFound exception.
 */
public class PushkeyNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new {@link PushkeyNotFoundException} instance.
     */
    public PushkeyNotFoundException() {
        super("PushKey not updated");
    }

}
