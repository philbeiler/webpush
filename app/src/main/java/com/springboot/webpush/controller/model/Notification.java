package com.springboot.webpush.controller.model;

public class Notification {
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Notification [title=" + title + "]";
	}

}