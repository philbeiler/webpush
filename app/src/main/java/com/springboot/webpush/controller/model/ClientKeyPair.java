package com.springboot.webpush.controller.model;

import java.util.Base64;

import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;

import nl.martijndwars.webpush.Utils;

public class ClientKeyPair {

	private final ECPublicKey publicKey;
	private final ECPrivateKey privateKey;

	public ClientKeyPair(ECPublicKey publicKey, ECPrivateKey privateKey) {
		super();
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	public ECPublicKey getPublicKey() {
		return publicKey;
	}

	public ECPrivateKey getPrivateKey() {
		return privateKey;
	}

	public String getPublicKeyAsString() {
		final byte[] encodedPublicKey = Utils.encode(publicKey);
		return Base64.getUrlEncoder().encodeToString(encodedPublicKey);
	}

	public String getPrivateKeyAsString() {
		final byte[] encodedPrivateKey = Utils.encode(privateKey);
		return Base64.getUrlEncoder().encodeToString(encodedPrivateKey);
	}

}
