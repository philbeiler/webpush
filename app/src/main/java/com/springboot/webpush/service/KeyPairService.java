package com.springboot.webpush.service;

import static com.springboot.webpush.impl.Utils.ALGORITHM;
import static com.springboot.webpush.impl.Utils.CURVE;
import static org.bouncycastle.jce.provider.BouncyCastleProvider.PROVIDER_NAME;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.springboot.webpush.controller.model.ClientKeyPair;
import com.springboot.webpush.exception.ClientKeyPairException;
import com.springboot.webpush.impl.Utils;

@Service
public class KeyPairService {
	private static final Logger LOGGER = LoggerFactory.getLogger(KeyPairService.class);
	private final File publicKeyFile;

	public KeyPairService(@Value("${public.key.location:/tmp/public.key}") final String publicKeyFile) {
		this.publicKeyFile = new File(publicKeyFile);
	}

	public ClientKeyPair generate() {
		try {
			final KeyPair keyPair = generateKeyPair();
			final ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
			final ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();

			final byte[] encodedPublicKey = Utils.encode(publicKey);
			final byte[] encodedPrivateKey = Utils.encode(privateKey);

			writeKey(keyPair.getPublic(), publicKeyFile);

			return new ClientKeyPair( //
					Base64.getUrlEncoder().encodeToString(encodedPublicKey),
					Base64.getUrlEncoder().encodeToString(encodedPrivateKey));

		}
		catch (InvalidAlgorithmParameterException | NoSuchProviderException | NoSuchAlgorithmException
				| IOException e) {
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
		final ECNamedCurveParameterSpec parameterSpec = ECNamedCurveTable.getParameterSpec(CURVE);

		final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM, PROVIDER_NAME);
		keyPairGenerator.initialize(parameterSpec);

		return keyPairGenerator.generateKeyPair();
	}

	/**
	 * Write the given key to the given file.
	 *
	 * @param key
	 * @param file
	 */
	private void writeKey(final Key key, final File file) throws IOException {
		file.createNewFile();

		try (PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
			final PemObject pemObject = new PemObject("Key", key.getEncoded());

			pemWriter.writeObject(pemObject);
		}
	}
}
