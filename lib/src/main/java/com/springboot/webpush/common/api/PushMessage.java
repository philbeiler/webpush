package com.springboot.webpush.common.api;

import com.springboot.webpush.common.api.types.OnActionClickOperation;

/**
 * The message that will be sent to the user's browser (PushMessage).
 */
public class PushMessage {
    private final Notification notification;

    /**
     * Constructs a new {@link PushMessage} instance.
     *
     * @param notification The {@link Notification} instance to be sent.
     */
    private PushMessage(final Notification notification) {
        this.notification = notification;
    }

    /**
     * @return The {@link Notification} instance.
     */
    public Notification getNotification() {
        return notification;
    }

    @Override
    public String toString() {
        return "WebPushMessage [notification=" + notification + "]";
    }

    /**
     * Creates a new PushMessage instance.
     *
     * @param title                  The message to be sent.
     * @param body                   The body of the message to be sent.
     * @param tag                    The tag of the message to be sent.
     * @param iconURI                The URI of the icon to be displayed in the notification.
     * @param imageURI               The URI of the image to be displayed in the notification.
     * @param badgeURI               The URI of the badge to be displayed in the notification.
     * @param onActionClickOperation The on click action to perform on the notification.
     * @param onActionClickURI       The URI for navigation, when the user clicks the notification.
     * @param timestamp              The time stamp to be associated with the notification.
     * @param renotify               TRUE or FALSE, if the notification behavior is to re-notify the user.
     * @param requiresInteraction    TRUE or FALSE, if the notification requires the user to click to dismiss.
     * @return The new PushMessage instance.
     */
    public static PushMessage of(final String title,
                                 final String body,
                                 final String tag,
                                 final String iconURI,
                                 final String imageURI,
                                 final String badgeURI,
                                 final OnActionClickOperation onActionClickOperation,
                                 final String onActionClickURI,
                                 final long timestamp,
                                 final boolean renotify,
                                 final boolean requiresInteraction) {
        return new PushMessage(new Notification(title, body, tag, iconURI, imageURI, badgeURI, onActionClickOperation,
                onActionClickURI, timestamp, renotify, requiresInteraction));
    }
}
