package com.springboot.webpush.controller.model;

import java.security.KeyPair;

public class ClientKeyPair {

	private final KeyPair keyPair;

	public ClientKeyPair(KeyPair keyPar) {
		super();
		this.keyPair = keyPar;
	}

	public KeyPair getKeyPair() {
		return keyPair;
	}
}
