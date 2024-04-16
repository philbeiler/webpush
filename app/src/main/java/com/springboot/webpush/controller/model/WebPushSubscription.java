package com.springboot.webpush.controller.model;

public class WebPushSubscription {
	private String endpoint;
	private Integer expriationTime;
	private Key keys;

	public WebPushSubscription() {
		super();
	}

	/**
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * @param endpoint the endpoint to set
	 */
	public void setEndpoint(final String endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * @return the expriationTime
	 */
	public Integer getExpriationTime() {
		return expriationTime;
	}

	/**
	 * @param expriationTime the expriationTime to set
	 */
	public void setExpriationTime(final Integer expriationTime) {
		this.expriationTime = expriationTime;
	}

	/**
	 * @return the keys
	 */
	public Key getKeys() {
		return keys;
	}

	/**
	 * @param keys the keys to set
	 */
	public void setKeys(final Key keys) {
		this.keys = keys;
	}

}
