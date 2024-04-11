package com.springboot.webpush.controller.model;

public class ClientKeyPair {
	private final String publicKey;
	private final String privateKey;

	/**
	 * @param publicKey
	 * @param privateKey
	 */
	public ClientKeyPair(final String publicKey, final String privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	/**
	 * @return the publicKey
	 */
	public String getPublicKey() {
		return publicKey;
	}

	/**
	 * @return the privateKey
	 */
	public String getPrivateKey() {
		return privateKey;
	}
}
