package com.springboot.webpush.common.api;

import java.util.HashMap;

import com.springboot.webpush.common.api.types.OnActionClickOperation;

/**
 * The {@link NotificationData} instance managed the on action click information.
 */
public final class NotificationData {
    // The key of this map should match the action property of the
    // NotificationAction class this entry will correspond
    // to Or "default" for clicking the notification itself.
    private HashMap<String, OnActionClick> onActionClick;

    /**
     * Constructs a new instance of the {@link NotificationData} class.
     *
     * @param onActionClick The {@link OnActionClick} instance to add to the notification.
     */
    private NotificationData(final HashMap<String, OnActionClick> onActionClick) {
        this.onActionClick = onActionClick;
    }

    /**
     * Sets the {@link OnActionClick} instance.
     *
     * @param onActionClick The {@link OnActionClick} instance.
     */
    public void setOnActionClick(final HashMap<String, OnActionClick> onActionClick) {
        this.onActionClick = onActionClick;
    }

    /**
     *
     * @return Returns the {@link OnActionClick} instance.
     */
    public HashMap<String, OnActionClick> getOnActionClick() {
        return onActionClick;
    }

    /**
     * Factory method to create {@link NotificationData} instances.
     *
     * @param onActionClickOperation The {@link OnActionClickOperation} action.
     * @param onActionClickURI       The URI for the action to be performed.
     * @return A new {@link NotificationData} instance.
     */
    public static NotificationData of(final OnActionClickOperation onActionClickOperation,
                                      final String onActionClickURI) {
        final var onActionClickMap = new HashMap<String, OnActionClick>();
        if (onActionClickOperation != OnActionClickOperation.NOOP) {
            onActionClickMap.put("default", new OnActionClick(onActionClickOperation, onActionClickURI));
        }
        return new NotificationData(onActionClickMap);
    }
}
