package com.springboot.webpush.common.util;

import static nl.martijndwars.webpush.Utils.ALGORITHM;
import static nl.martijndwars.webpush.Utils.CURVE;
import static org.bouncycastle.jce.provider.BouncyCastleProvider.PROVIDER_NAME;

import java.lang.invoke.MethodHandles;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;
import java.util.Optional;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.springboot.webpush.common.api.KeyStore;

import nl.martijndwars.webpush.Utils;

/**
 * The {@link KeyStoreGenerator} is responsible for generating the {@link KeyStore} required to encrypt the push
 * messages.
 */
@Service
public class KeyStoreGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Generates a new {@link KeyStore} instance, containing a public and private key.
     *
     * @return The generated {@link KeyStore} instance. If any problems are encountered, an Optional.empty() will be
     *         returned.
     */
    public Optional<KeyStore> generate() {
        KeyPair keyPair;
        try {
            keyPair = generateKeyPair();
            final var publicKey  = (ECPublicKey) keyPair.getPublic();
            final var privateKey = (ECPrivateKey) keyPair.getPrivate();

            final var encodedPublicKey  = Utils.encode(publicKey);
            final var encodedPrivateKey = Utils.encode(privateKey);

            return Optional.of(new KeyStore(Base64.getUrlEncoder().encodeToString(encodedPrivateKey),
                    Base64.getUrlEncoder().encodeToString(encodedPublicKey)));
        }
        catch (InvalidAlgorithmParameterException | NoSuchProviderException | NoSuchAlgorithmException e) {
            LOGGER.error("Error", e);
        }
        return Optional.empty();
    }

    /**
     * Generate an EC keypair on the prime256v1 curve.
     *
     * @return                                    The generated {@link KeyPair} instance.
     *
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     */
    private KeyPair generateKeyPair()
            throws InvalidAlgorithmParameterException, NoSuchProviderException, NoSuchAlgorithmException {
        final var parameterSpec = ECNamedCurveTable.getParameterSpec(CURVE);

        final var keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM, PROVIDER_NAME);
        keyPairGenerator.initialize(parameterSpec);

        return keyPairGenerator.generateKeyPair();
    }

}
