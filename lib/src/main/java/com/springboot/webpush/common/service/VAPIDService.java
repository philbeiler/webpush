package com.springboot.webpush.common.service;

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

@Service
public class VAPIDService {
    private static final Logger  LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final StorageService storageService;
    private final KeyStore       keyStore;

    public VAPIDService(final StorageService storageService) {
        super();
        this.storageService = storageService;
        this.keyStore       = storageService.load().orElseGet(() -> generate().orElse(new KeyStore()));
        if (keyStore.invalid()) {
            LOGGER.error("Unable to generate VAPID keys, nothing is going to work!");
        }
    }

    public KeyStore getKeyStore() {
        return keyStore;
    }

    public Optional<KeyStore> generate() {
        KeyPair keyPair;
        try {
            keyPair = generateKeyPair();
            final var publicKey         = (ECPublicKey) keyPair.getPublic();
            final var privateKey        = (ECPrivateKey) keyPair.getPrivate();

            final var encodedPublicKey  = Utils.encode(publicKey);
            final var encodedPrivateKey = Utils.encode(privateKey);

            return storageService.save(Base64.getUrlEncoder().encodeToString(encodedPrivateKey),
                    Base64.getUrlEncoder().encodeToString(encodedPublicKey));
        }
        catch (InvalidAlgorithmParameterException | NoSuchProviderException | NoSuchAlgorithmException e) {
            LOGGER.error("Error", e);
        }
        return Optional.empty();
    }

    /**
     * Generate an EC keypair on the prime256v1 curve.
     *
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     */
    private KeyPair generateKeyPair()
            throws InvalidAlgorithmParameterException, NoSuchProviderException, NoSuchAlgorithmException {
        final var parameterSpec    = ECNamedCurveTable.getParameterSpec(CURVE);

        final var keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM, PROVIDER_NAME);
        keyPairGenerator.initialize(parameterSpec);

        return keyPairGenerator.generateKeyPair();
    }

}
