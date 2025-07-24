package com.springboot.webpush.common.api;

import com.springboot.webpush.common.api.types.OnActionClickOperation;

/**
 * The {@link OnActionClick} class is used to control how push messages are to behave.
 */
public class OnActionClick {
    private final String operation;
    private final String url;

    /**
     * Constructs a new {@link OnActionClick} instance.
     *
     * @param operation The operation, how to behave.
     * @param url       The URL to open.
     */
    public OnActionClick(final OnActionClickOperation operation,
                         final String url) {
        this.operation = operation.toString();
        this.url       = url;
    }

    /**
     * Return the operation, IE, how to behave.
     *
     * @return The operation, how to behave
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Return The url for navigation; Optional, IE may be null.
     *
     * @return The url for navigation.
     */
    public String getUrl() {
        return url;
    }

}
