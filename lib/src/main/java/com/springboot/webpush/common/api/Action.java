package com.springboot.webpush.common.api;

import java.util.List;

/**
 * The action class, specific properties to be used to send the {@link PushMessage} to the service.
 */
public class Action {
    private final List<Integer> vibrate = List.of(180, 20, 80, 20, 80, 20, 180, 20, 180);
    private final String        title;
    private final String        iconURI;
    private final String        imageURI;
    private final String        body;
    private final boolean       requireInteraction;

    /**
     * Constructs a new Action instance.
     *
     * @param title The title of the push message.
     * @param body  The body of the push message.
     */
    public Action(final String title, final String body) {
        this.title              = title;
        this.body               = body;
        this.requireInteraction = true;
        this.iconURI            = "https://cdn-icons-png.flaticon.com/512/3845/3845831.png";
        this.imageURI           = "https://cdn-teams-slug.flaticon.com/google.jpg";

    }

    /**
     * @return The vibration sequence.
     */
    public List<Integer> getVibrate() {
        return vibrate;
    }

    /**
     * @return The title of the push message.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The iconURI.
     */
    public String getIconURI() {
        return iconURI;
    }

    /**
     * @return The imageURI.
     */
    public String getImageURI() {
        return imageURI;
    }

    /**
     * @return The body of the push message.
     */
    public String getBody() {
        return body;
    }

    /**
     * @return he requireInteraction property, TRUE requires user interaction, otherwise no action required by the user.
     */
    public boolean isRequireInteraction() {
        return requireInteraction;
    }

    @Override
    public String toString() {
        return "Action [vibrate=" + vibrate + ", title=" + title + ", iconURI=" + iconURI + ", imageURI=" + imageURI
                + ", body=" + body + ", requireInteraction=" + requireInteraction + "]";
    }

}
