package com.springboot.webpush.common.api;

import java.util.List;

import org.springframework.util.Assert;

/**
 * The {@link Notification} class is the primary message structure, as expected by the browser creators web push
 * services.
 */
public class Notification {
    private static final int           VIBRATE_MEDIUM          = 80;
    private static final int           VIBRATE_SHORT           = 20;
    private static final int           VIBRATE_LONG            = 180;
    private static final List<Integer> DEFAULT_VIBRATE_PATTERN = List.of(VIBRATE_LONG, VIBRATE_SHORT, VIBRATE_MEDIUM,
            VIBRATE_SHORT, VIBRATE_MEDIUM, VIBRATE_SHORT, VIBRATE_LONG, VIBRATE_SHORT, VIBRATE_LONG);

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
     * Returns the actions property.
     *
     * @return The actions property.
     */
    public List<NotificationAction> getActions() {
        return actions;
    }

    /**
     * The title of the push message.
     *
     * @return The title of the push message.
     */
    public String getTitle() {
        return title;
    }

    /**
     * The body of the push message.
     *
     * @return The body of the push message
     */
    public String getBody() {
        return body;
    }

    /**
     * The vibration pattern to be used my mobile devices.
     *
     * @return The vibration pattern to be used my mobile devices.
     */
    public List<Integer> getVibrate() {
        return vibrate;
    }

    /**
     * The internal data included with the push message.
     *
     * @return The internal data included with the push message.
     */
    public NotificationData getData() {
        return data;
    }

    /**
     * Returns the icon property.
     *
     * @return The icon property.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Returns the image property.
     *
     * @return The image property.
     */
    public String getImage() {
        return image;
    }

    /**
     * Returns the tag property.
     *
     * @return The tag property.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Returns the badge property.
     *
     * @return The badge property.
     */
    public String getBadge() {
        return badge;
    }

    /**
     * Return he time stamp associated with the push message.
     *
     * @return The time stamp associated with the push message.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * The renotify option, should the notification re-notify the user if not acted upon, true or false.
     *
     * @return The TRUE/FALSE, depending on if re-notify is enabled.
     */
    public boolean isRenotify() {
        return renotify;
    }

    /**
     * When TRUE, the user must click on the notification; when false, the notification will disappear after some
     * predetermined time.
     *
     * @return The requires user interaction indicator.
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
