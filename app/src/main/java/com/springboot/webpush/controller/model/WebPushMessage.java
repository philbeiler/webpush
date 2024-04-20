package com.springboot.webpush.controller.model;

import org.springframework.util.StringUtils;

import com.springboot.webpush.common.api.PushMessage;

import nl.martijndwars.webpush.Urgency;

public class WebPushMessage {
    private Urgency      urgency;
    private String       message;
    @Deprecated
    private Notification notification;

    /**
     * @return the urgency
     */
    public Urgency getUrgency() {
        return urgency == null ? Urgency.NORMAL : urgency;
    }

    /**
     * @param urgency the urgency to set
     */
    public void setUrgency(final Urgency urgency) {
        this.urgency = urgency;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(final String message) {
        this.message = message;
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

    public PushMessage get() {
        final String msg;
        if (notification != null && StringUtils.hasText(notification.getTitle())) {
            msg = notification.getTitle();
        }
        else {
            msg = message;
        }
        return PushMessage.of(msg);
    }
}
