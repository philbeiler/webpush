package com.springboot.webpush.common.api;

/**
 * The {@link NotificationAction} class manages the "actions" for the associated {@link Notification} instance.
 */
public class NotificationAction {

    private final String action;
    private final String title;
    private final String icon;

    /**
     * Constructs a new instance of the {@link NotificationAction} class.
     *
     * @param action The notification action.
     * @param title  The notification title.
     * @param icon   The notification icon.
     */
    public NotificationAction(final String action,
                              final String title,
                              final String icon) {
        this.action = action;
        this.title  = title;
        this.icon   = icon;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

}
