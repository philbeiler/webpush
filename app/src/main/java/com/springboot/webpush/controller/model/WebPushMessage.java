package com.springboot.webpush.controller.model;

public class WebPushMessage {

	private String title;
	private String clickTarget;
	private String message;

	public WebPushMessage() {
	}

	public WebPushMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClickTarget() {
		return clickTarget;
	}

	public void setClickTarget(String clickTarget) {
		this.clickTarget = clickTarget;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "WebPushMessage [title=" + title + ", clickTarget=" + clickTarget + ", message=" + message + "]";
	}

}