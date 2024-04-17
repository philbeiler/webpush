package com.springboot.webpush.service;

import static org.bouncycastle.jce.provider.BouncyCastleProvider.PROVIDER_NAME;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.springboot.webpush.controller.model.ClientKeyPair;
import com.springboot.webpush.exception.ClientKeyPairException;

@Service
public class KeyPairService {
	private static final Logger LOGGER = LoggerFactory.getLogger(KeyPairService.class);

	public ClientKeyPair generate() {
		try {
			return new ClientKeyPair(generateKeyPair());

		} catch (InvalidAlgorithmParameterException | NoSuchProviderException | NoSuchAlgorithmException e) {
			LOGGER.error("Exception", e);
			throw new ClientKeyPairException();
		}

	}

	/**
	 * Generate an EC keypair on the prime256v1 curve.
	 *
	 * @return
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 */
	public KeyPair generateKeyPair()
			throws InvalidAlgorithmParameterException, NoSuchProviderException, NoSuchAlgorithmException {
		final ECNamedCurveParameterSpec parameterSpec = ECNamedCurveTable.getParameterSpec("prime256v1");

		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDH", PROVIDER_NAME);
		keyPairGenerator.initialize(parameterSpec);

		return keyPairGenerator.generateKeyPair();
	}
}
