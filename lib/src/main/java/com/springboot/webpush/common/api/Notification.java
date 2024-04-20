package com.springboot.webpush.common.api;

import java.util.List;

public class Notification {
    private final String        title;
    private final String        body;
    private final Data          data;
    private final List<Integer> vibrate;
    private final String        click;
    private final String        icon;
    private final String        image;
    private final boolean       requireInteraction;

    public class Data {
        private final String url;

        public Data(String url) {
            super();
            this.url = url;
        }

        /**
         * @return the url
         */
        public String getUrl() {
            return url;
        }

    }

    /**
     * @param title
     */
    public Notification(final String title) {
        final List<Integer> vibrate = List.of(200, 100, 200);
        this.title              = title;
        this.body               = "body test";
        this.data               = new Data("https://example.com/review/12345");
        this.vibrate            = vibrate;
        this.requireInteraction = true;
        this.click              = "https://www.evhgear.com/";
        this.icon               = "https://cdn-teams-slug.flaticon.com/google.jpg";
        this.image              = "https://cdn-teams-slug.flaticon.com/google.jpg";
    }

    /**
     * @return the click
     */
    public String getClick() {
        return click;
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
    public Data getData() {
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
