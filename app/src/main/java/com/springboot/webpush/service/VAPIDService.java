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

@Service
public class VAPIDService {
	private static final Logger LOGGER = LoggerFactory.getLogger(VAPIDService.class);
	private final KeyPair keyPair;

	public VAPIDService() {
		super();
		keyPair = generateKeyPair();
	}

	/**
	 * Generate an EC keypair on the prime256v1 curve.
	 *
	 * @return
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 */
	public KeyPair generateKeyPair() {
		try {
			final ECNamedCurveParameterSpec parameterSpec = ECNamedCurveTable.getParameterSpec("prime256v1");

			final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDH", PROVIDER_NAME);
			keyPairGenerator.initialize(parameterSpec);

			return keyPairGenerator.generateKeyPair();
		}
		catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
			LOGGER.error("ERROR", e);
		}
		return null;
	}

	public String getPrivateKey() {
		return "pMicnxO7KOCxUjbA6--6S1R5NCbnyE4A0-EZxravdGA";
	}

	public String getPublicKey() {
		LOGGER.info("VAPID***********************************");
		/*
		 * PublicKey publicKey = this.notificationService.getPublicKey();
		 * System.out.println(publicKey); byte[] encodedPublicKey =
		 * publicKey.getEncoded(); String b64PublicKey =
		 * Base64.getEncoder().encodeToString(encodedPublicKey); return b64PublicKey;
		 */
		return "BJ3kKgvZR34Wt-dJMG9L7Lh03ISRlWueIgg-Vp5V5oYsDPr47_szfNsxxLQrghPtuN3XaI6A3FVZVZuVa7LZOFY";
	}
}
