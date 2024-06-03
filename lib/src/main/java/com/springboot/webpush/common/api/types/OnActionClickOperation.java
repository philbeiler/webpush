package com.springboot.webpush.common.api.types;

/**
 * The {@link OnActionClickOperation} enumeration represents all of the actions that can happen on a push message.
 */
public enum OnActionClickOperation {
    /**
     * Do no utilize the OnActionClick functionality.
     */
    NOOP("noop"),
    /**
     * Opens a new tab at the specified URL.
     */
    OPEN_WINDOW("openWindow"),
    /**
     * Focuses the last focused client. If there is no client open, then it opens a new tab at the specified URL. (URL
     * is optional)
     */
    FOCUS_LAST("focusLastFocusedOrOpen"),
    /**
     * Focuses the last focused client and navigates it to the specified URL. If there is no client open, then it opens
     * a new tab at the specified URL.
     */
    NAVIGATE_LAST("navigateLastFocusedOrOpen"),
    /**
     * Send a simple GET request to the specified URL.
     */
    SEND_REQUEST("sendRequest");

    private final String operation;

    /**
     * Constructs a new instance.
     *
     * @param operation The operation to perform, in string format.
     */
    private OnActionClickOperation(final String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return this.operation;
    }
}
