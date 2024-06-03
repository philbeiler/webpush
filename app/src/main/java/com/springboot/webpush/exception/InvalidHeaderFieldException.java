package com.springboot.webpush.exception;

/**
 * InvalidHeaderFieldException class.
 */
public class InvalidHeaderFieldException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new {@link InvalidHeaderFieldException} instance.
     *
     * @param message The exception message.
     */
    public InvalidHeaderFieldException(final String message) {
        super(message);
    }

}
