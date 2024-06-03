package com.springboot.webpush.exception;

/**
 * InvalidFieldException class.
 */
public class InvalidFieldException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new {@link InvalidFieldException} exception.
     *
     * @param message The exception message.
     */
    public InvalidFieldException(final String message) {
        super(message);
    }

}
