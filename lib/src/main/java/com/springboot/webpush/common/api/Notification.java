package com.springboot.webpush.common.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.springboot.webpush.common.api.types.OnActionClickOperation;

/**
 * The {@link Notification} class is the primary message structure, as expected by the browser creators web push
 * services.
 */
public class Notification {
    private final String              title;
    private final String              body;
    private final Map<String, Object> data;
    private final List<Integer>       vibrate;
    private final List<Action>        actions;
    private final String              icon;
    private final String              image;
    private final boolean             requireInteraction;

    /**
     * Constructs a new {@link Notification} instance.
     *
     * @param title                  The title associated with the push message.
     * @param body                   The body associated with the push message.
     * @param icon                   The icon associated with the push message.
     * @param image                  The image associated with the push message.
     * @param onActionClickOperation The operation to perform when the notification is clicked on by the user.
     * @param onActionClickURI       The URI to navigate to, when the notification is clicked on by the user.
     * @param requireInteraction     Is user interaction required by this push message, true or false.
     */
    public Notification(final String title, final String body, final String icon, final String image,
            final OnActionClickOperation onActionClickOperation, final String onActionClickURI,
            final boolean requireInteraction) {
        Assert.notNull(onActionClickOperation, "OnActionClickOperation cannot be null");
        Assert.notNull(title, "title cannot be null");
        Assert.notNull(body, "body cannot be null");

        final List<Integer> vibrate = List.of(180, 20, 80, 20, 80, 20, 180, 20, 180);
        this.title              = title;
        this.body               = body;
        this.data               = new HashMap<>();
        this.vibrate            = vibrate;
        this.requireInteraction = true;
        this.actions            = List.of();
        this.icon               = icon;
        this.image              = image;

        // The key of this map should match the action property of the Action class this entry will correspond to
        // Or "default" for clicking the notification itself.
        final var onActionClickMap = new HashMap<String, OnActionClick>();
        if (onActionClickOperation != OnActionClickOperation.NOOP) {
            data.put("onActionClick", onActionClickMap);
            onActionClickMap.put("default", new OnActionClick(onActionClickOperation.toString(), onActionClickURI));
        }
        // onActionClickMap.put(OPEN_PWA_ACTION, new OnActionClick("focusLastFocusedOrOpen",
        // "https://angular.jimboradleyha.duckdns.org"));
    }

    /**
     * @return the actions
     */
    public List<Action> getActions() {
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
    public Map<String, Object> getData() {
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
     * @return The requires user interaction indicator. When TRUE, the user must click on the notification; when false,
     *         the notification will disappear after some
     */
    public boolean isRequireInteraction() {
        return requireInteraction;
    }

    @Override
    public String toString() {
        return "Notification [title=" + title + "]";
    }

}
