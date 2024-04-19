package com.springboot.webpush.controller.model;

public class WebPushMessage {
	private Notification notification;

	/**
	 * @return the notification
	 */
	public Notification getNotification() {
		return notification;
	}

	/**
	 * @param notification the notification to set
	 */
	public void setNotification(final Notification notification) {
		this.notification = notification;
	}
}
