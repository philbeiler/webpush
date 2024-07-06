package com.springboot.webpush.common.api;

import java.util.List;

import org.springframework.util.Assert;

/**
 * The {@link Notification} class is the primary message structure, as expected by the browser creators web push
 * services.
 */
public class Notification {
    private static final int               VIBRATE_MEDIUM          = 80;
    private static final int               VIBRATE_SHORT           = 20;
    private static final int               VIBRATE_LONG            = 180;
    private static final List<Integer>     DEFAULT_VIBRATE_PATTERN = List.of(VIBRATE_LONG, VIBRATE_SHORT,
            VIBRATE_MEDIUM, VIBRATE_SHORT, VIBRATE_MEDIUM, VIBRATE_SHORT, VIBRATE_LONG, VIBRATE_SHORT, VIBRATE_LONG);

    private final String                   title;
    private final String                   body;
    private final NotificationData         data;
    private final List<Integer>            vibrate;
    private final List<NotificationAction> actions;
    private final String                   icon;
    private final String                   image;
    private final String                   tag;
    private final String                   badge;
    private final long                     timestamp;
    private final boolean                  renotify;
    private final boolean                  requireInteraction;

    /**
     * Constructs a new {@link Notification} instance.
     *
     * @param title              The title associated with the push message.
     * @param body               The body associated with the push message.
     * @param tag                The tag associated with the push message.
     * @param notificationData   The onActionClick / notification data.
     * @param icon               The icon associated with the push message.
     * @param image              The image associated with the push message.
     * @param badge              The badge associated with the push message.
     * @param timestamp          The timestamp associated with the push message.
     * @param renotify           If the notification will re-notify the user, true or false.
     * @param requireInteraction Is user interaction required by this push message, true or false.
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public Notification(final String title,
                        final String body,
                        final String tag,
                        final NotificationData notificationData,
                        final String icon,
                        final String image,
                        final String badge,
                        final long timestamp,
                        final boolean renotify,
                        final boolean requireInteraction) {
        Assert.notNull(title, "title cannot be null");
        Assert.notNull(body, "body cannot be null");

        this.title              = title;
        this.body               = body;
        this.tag                = tag;
        this.data               = notificationData;
        this.vibrate            = DEFAULT_VIBRATE_PATTERN;
        this.renotify           = renotify;
        this.requireInteraction = requireInteraction;
        this.actions            = List.of();
        this.icon               = icon;
        this.image              = image;
        this.badge              = badge;
        this.timestamp          = timestamp;
    }

    /**
     * @return the actions
     */
    public List<NotificationAction> getActions() {
        return actions;
    }

    /**
     * @return The title of the push message.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return The body of the push message
     */
    public String getBody() {
        return body;
    }

    /**
     * @return The vibration pattern to be used my mobile devices.
     */
    public List<Integer> getVibrate() {
        return vibrate;
    }

    /**
     * @return The internal data included with the push message.
     */
    public NotificationData getData() {
        return data;
    }

    /**
     * @return The icon URI.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @return The image URI.
     */
    public String getImage() {
        return image;
    }

    /**
     * @return The tag of the push message.
     */
    public String getTag() {
        return tag;
    }

    /**
     * @return The badge URI.
     */
    public String getBadge() {
        return badge;
    }

    /**
     * @return The time stamp associated with the push message.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @return The renotify option, should the notification re-notify the user if not acted upon, true or false.
     */
    public boolean isRenotify() {
        return renotify;
    }

    /**
     * @return The requires user interaction indicator. When TRUE, the user must click on the notification; when false,
     *         the notification will disappear after some predetermined time.
     */
    public boolean isRequireInteraction() {
        return requireInteraction;
    }

    @Override
    public String toString() {
        return "Notification [title=" + title + ", body=" + body + ", data=" + data + ", vibrate=" + vibrate
                + ", actions=" + actions + ", icon=" + icon + ", image=" + image + ", tag=" + tag + ", badge=" + badge
                + ", timestamp=" + timestamp + ", renotify=" + renotify + ", requireInteraction=" + requireInteraction
                + "]";
    }

}
