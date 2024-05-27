package com.springboot.webpush.common.api;

/**
 * The {@link OnActionClick} class is used to control how push messages are to behave. Valid operations are:<br>
 * openWindow Opens a new tab at the specified URL.<br>
 * focusLastFocusedOrOpen Focuses the last focused client. If there is no client open, then it opens a new tab at the
 * specified URL. (url is optional)<br>
 * navigateLastFocusedOrOpen Focuses the last focused client and navigates it to the specified URL. If there is no
 * client open, then it opens a new tab at the specified URL.<br>
 * sendRequest Send a simple GET request to the specified URL.<br>
 *
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
    public OnActionClick(final String operation, final String url) {
        this.operation = operation;
        this.url       = url;
    }

    /**
     * @return The operation, how to behave
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @return The url for navigation, optional, IE may be null.
     */
    public String getUrl() {
        return url;
    }

}
