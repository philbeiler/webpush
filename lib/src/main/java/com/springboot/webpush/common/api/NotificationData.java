package com.springboot.webpush.common.api;

import java.util.HashMap;

import com.springboot.webpush.common.api.types.OnActionClickOperation;

public class NotificationData {
    // The key of this map should match the action property of the NotificationAction class this entry will correspond to
    // Or "default" for clicking the notification itself.
    HashMap<String, OnActionClick> onActionClick;

    public NotificationData(HashMap<String, OnActionClick> onActionClick) {
        this.onActionClick = onActionClick;
    }

    public void setOnActionClick(HashMap<String, OnActionClick> onActionClick) {
        this.onActionClick = onActionClick;
    }

    public HashMap<String, OnActionClick> getOnActionClick() {
        return onActionClick;
    }

    public static NotificationData of(OnActionClickOperation onActionClickOperation, String onActionClickURI) {
        HashMap<String, OnActionClick> onActionClickMap = new HashMap<>(); 
        if (onActionClickOperation != OnActionClickOperation.NOOP) {
            onActionClickMap.put("default", new OnActionClick(onActionClickOperation, onActionClickURI));
        }
        return new NotificationData(onActionClickMap);
    }
}
