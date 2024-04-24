package com.springboot.webpush.common.api;

public class Action {
    private final String        title;
    private final String        icon;
    private final String        action;

    /**
     * @param title
     */
    public Action(final String title, String icon, String action) {
        this.title              = title;
        this.icon               = icon;
        this.action             = action;
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

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "Action [title=" + title + "]";
    }

}
