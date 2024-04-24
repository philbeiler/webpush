package com.springboot.webpush.common.api;

public class OnActionClick {
    /* valid operations are:
         openWindow	Opens a new tab at the specified URL.
         focusLastFocusedOrOpen	Focuses the last focused client. If there is no client open, then it opens a new tab at the specified URL. (url is optional)
         navigateLastFocusedOrOpen	Focuses the last focused client and navigates it to the specified URL. If there is no client open, then it opens a new tab at the specified URL.
         sendRequest	Send a simple GET request to the specified URL.
    */
    private final String operation;
    private final String url;

    public  OnActionClick(String operation, String url) {
        this.operation = operation;
        this.url = url;
    }

    /**
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

}