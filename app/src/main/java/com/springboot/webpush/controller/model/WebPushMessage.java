package com.springboot.webpush.controller.model;

import com.springboot.webpush.common.api.types.OnActionClickOperation;

import nl.martijndwars.webpush.Urgency;

/**
 * The external {@link WebPushMessage} contains the data required to generate a custom web notification.
 */
public class WebPushMessage {
    private Urgency                urgency;
    private String                 title;
    private String                 body;
    private String                 tag;
    private String                 iconURI;
    private String                 imageURI;
    private String                 badgeURI;
    private Boolean                renotify;
    private Boolean                requireInteraction;
    private OnActionClickOperation onActionClickOperation;
    private String                 onActionClickURI;

    /**
     * @param urgency the urgency to set
     */
    public void setUrgency(final Urgency urgency) {
        this.urgency = urgency;
    }

    /**
     * @param onActionClickOperation the onActionClickOperation to set
     */
    public void setOnActionClickOperation(final OnActionClickOperation onActionClickOperation) {
        this.onActionClickOperation = onActionClickOperation;
    }

    /**
     * @return the urgency
     */
    public Urgency getUrgency() {
        return urgency == null ? Urgency.NORMAL : urgency;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @return the iconURI
     */
    public String getIconURI() {
        return iconURI;
    }

    /**
     * @return the imageURI
     */
    public String getImageURI() {
        return imageURI;
    }

    /**
     * @return the requireInteraction
     */
    public Boolean getRequireInteraction() {
        return requireInteraction;
    }

    /**
     * @return the onActionClickOperation
     */
    public OnActionClickOperation getOnActionClickOperation() {
        return onActionClickOperation;
    }

    /**
     * @return the onActionClickURI
     */
    public String getOnActionClickURI() {
        return onActionClickURI;
    }

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @return the badgeURI
     */
    public String getBadgeURI() {
        return badgeURI;
    }

    /**
     * @return the renotify
     */
    public Boolean getRenotify() {
        return renotify;
    }

}
