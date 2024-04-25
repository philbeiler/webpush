package com.springboot.webpush.common.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Notification {
    //private static final String OPEN_PWA_ACTION = "openPWA";

    private final String               title;
    private final String               body;
    private final Map<String, Object>  data;
    private final List<Integer>        vibrate;
    private final List<Action>         actions;
    private final String               icon;
    private final String               image;
    private final boolean              requireInteraction;

    /**
     * @param title
     */
    public Notification(final String title) {
        final List<Integer> vibrate = List.of(180, 20, 80, 20, 80, 20, 180, 20, 180);
        this.title              = title;
        this.body               = "What an exciting notification!";
        this.data               = new HashMap<>();
        this.vibrate            = vibrate;
        this.requireInteraction = true;
        //this.actions              = List.of(new Action("Open PWA", "", OPEN_PWA_ACTION));
        this.actions            = List.of();         
        this.icon               = "https://cdn-icons-png.flaticon.com/512/3845/3845831.png";
        this.image              = "https://cdn-teams-slug.flaticon.com/google.jpg";

        // The key of this map should match the action property of the Action class this entry will correspond to
        // Or "default" for clicking the notification itself.
        var onActionClickMap = new HashMap<String, OnActionClick>();
        onActionClickMap.put("default", new OnActionClick("focusLastFocusedOrOpen", null));
        //onActionClickMap.put(OPEN_PWA_ACTION, new OnActionClick("focusLastFocusedOrOpen", "https://angular.jimboradleyha.duckdns.org"));
        this.data.put("onActionClick", onActionClickMap);
    }

    /**
     * @return the actions
     */
    public List<Action> getActions() {
        return actions;
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
     * ng serve --configuration=production --prebundle=true --host 0.0.0.0
     *
     * @return the vibrate
     */
    public List<Integer> getVibrate() {
        return vibrate;
    }

    /**
     * @return the data
     */
    public Map<String, Object> getData() {
        return data;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @return the requireInteraction
     */
    public boolean isRequireInteraction() {
        return requireInteraction;
    }

    @Override
    public String toString() {
        return "Notification [title=" + title + "]";
    }

}
