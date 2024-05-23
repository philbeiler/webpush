package com.springboot.webpush.common.api;

public class PushMessage {
    private Notification notification;

    /**
     * @param notification
     */
    private PushMessage(final Notification notification) {
        this.notification = notification;
    }

    /**
     * @return the notification
     */
    public Notification getNotification() {
        return notification;
    }

    /**
     * @param notification the notification to set
     */
    public void setNotification(final Notification notification) {
        this.notification = notification;
    }

    @Override
    public String toString() {
        return "WebPushMessage [notification=" + notification + "]";
    }

    public static PushMessage of(final String message) {
        return new PushMessage(new Notification(message));
    }
}
