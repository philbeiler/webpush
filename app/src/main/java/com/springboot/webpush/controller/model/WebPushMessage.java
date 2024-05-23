package com.springboot.webpush.controller.model;

import com.springboot.webpush.common.api.PushMessage;

import nl.martijndwars.webpush.Urgency;

public class WebPushMessage {
    private Urgency urgency;
    private String  message;

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

    public PushMessage get() {
        return PushMessage.of(message);
    }
}
