package com.springboot.webpush.controller.model;

import nl.martijndwars.webpush.Subscription;
import nl.martijndwars.webpush.Subscription.Keys;

public class WebPushSubscription {
	private String endpoint;
	private Integer expirationTime;
	private Key keys;

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public Integer getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Integer expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Key getKeys() {
		return keys;
	}

	public void setKeys(Key keys) {
		this.keys = keys;
	}

	public Subscription get() {
		return new Subscription(endpoint, new Keys(keys.getP256dh(), keys.getAuth()));
	}

}
