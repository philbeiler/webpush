package com.springboot.webpush;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.util.Base64;
import java.util.HashMap;

import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.springboot.webpush.impl.Encoding;
import com.springboot.webpush.impl.HttpEce;
import com.springboot.webpush.impl.Utils;

class HttpEceTest {
	@BeforeAll
	public static void addSecurityProvider() {
		Security.addProvider(new BouncyCastleProvider());
	}

	private byte[] decode(final String s) {
		return Base64.getUrlDecoder().decode(s);
	}

	@Test
	public void testZeroSaltAndKey() throws GeneralSecurityException {
		final HttpEce httpEce = new HttpEce();
		final String plaintext = "Hello";
		final byte[] salt = new byte[16];
		final byte[] key = new byte[16];
		final byte[] actual = httpEce.encrypt(plaintext.getBytes(), salt, key, null, null, null, Encoding.AES128GCM);
		final byte[] expected = decode("AAAAAAAAAAAAAAAAAAAAAAAAEAAAMpsi6NfZUkOdJI96XyX0tavLqyIdiw");

		assertArrayEquals(expected, actual);
	}

	/**
	 * See
	 * https://tools.ietf.org/html/draft-ietf-httpbis-encryption-encoding-09#section-3.1
	 *
	 * - Record size is 4096. - Input keying material is identified by an empty
	 * string.
	 *
	 * @throws GeneralSecurityException
	 */
	@Test
	public void testSampleEncryption() throws GeneralSecurityException {
		final HttpEce httpEce = new HttpEce();

		final byte[] plaintext = "I am the walrus".getBytes();
		final byte[] salt = decode("I1BsxtFttlv3u_Oo94xnmw");
		final byte[] key = decode("yqdlZ-tYemfogSmv7Ws5PQ");
		final byte[] actual = httpEce.encrypt(plaintext, salt, key, null, null, null, Encoding.AES128GCM);
		final byte[] expected = decode("I1BsxtFttlv3u_Oo94xnmwAAEAAA-NAVub2qFgBEuQKRapoZu-IxkIva3MEB1PD-ly8Thjg");

		assertArrayEquals(expected, actual);
	}

	@Test
	public void testSampleEncryptDecrypt() throws GeneralSecurityException {
		final String encodedKey = "yqdlZ-tYemfogSmv7Ws5PQ";
		final String encodedSalt = "I1BsxtFttlv3u_Oo94xnmw";

		// Prepare the key map, which maps a keyid to a keypair.
		final PrivateKey privateKey = Utils.loadPrivateKey(encodedKey);
		final PublicKey publicKey = Utils.loadPublicKey((ECPrivateKey) privateKey);
		final KeyPair keyPair = new KeyPair(publicKey, privateKey);

		final HashMap<String, KeyPair> keys = new HashMap<>();
		keys.put("", keyPair);

		final HashMap<String, String> labels = new HashMap<>();
		labels.put("", "P-256");

		// Run the encryption and decryption
		final HttpEce httpEce = new HttpEce(keys, labels);

		final byte[] plaintext = "I am the walrus".getBytes();
		final byte[] salt = decode(encodedSalt);
		final byte[] key = decode(encodedKey);
		final byte[] ciphertext = httpEce.encrypt(plaintext, salt, key, null, null, null, Encoding.AES128GCM);
		final byte[] decrypted = httpEce.decrypt(ciphertext, null, key, null, Encoding.AES128GCM);

		assertArrayEquals(plaintext, decrypted);
	}

	/**
	 * See
	 * https://tools.ietf.org/html/draft-ietf-httpbis-encryption-encoding-09#section-3.2
	 *
	 * TODO: This test is disabled because the library does not deal with multiple
	 * records yet.
	 *
	 * @throws GeneralSecurityException
	 */
	@Test
	@Disabled
	public void testEncryptionWithMultipleRecords() throws GeneralSecurityException {
		final HttpEce httpEce = new HttpEce();

		final byte[] plaintext = "I am the walrus".getBytes();
		final byte[] salt = decode("uNCkWiNYzKTnBN9ji3-qWA");
		final byte[] key = decode("BO3ZVPxUlnLORbVGMpbT1Q");
		final byte[] actual = httpEce.encrypt(plaintext, salt, key, null, null, null, Encoding.AES128GCM);
		final byte[] expected = decode(
				"uNCkWiNYzKTnBN9ji3-qWAAAABkCYTHOG8chz_gnvgOqdGYovxyjuqRyJFjEDyoF1Fvkj6hQPdPHI51OEUKEpgz3SsLWIqS_uA");

		assertArrayEquals(expected, actual);
	}
}
