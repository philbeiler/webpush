package com.springboot.webpush.common.service;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.springboot.webpush.common.api.KeyStore;
import com.springboot.webpush.common.util.KeyStoreGenerator;

/**
 * The {@link KeyStoreService} is responsible for managing (creating/retrieving/providing) the key information required
 * to encrypt the push messages.
 */
@Service
public class KeyStoreService {
    private static final Logger  LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final KeyStore       keyStore;
    private final StorageService storageService;

    /**
     * Constructs a new instance of the {@link KeyStoreService}.
     *
     * @param storageService The {@link StorageService} instance
     */
    public KeyStoreService(final StorageService storageService) {
        super();
        this.storageService = storageService;

        var ks = storageService.load();
        if (ks.isEmpty()) {
            ks = generate();
        }

        this.keyStore = ks.orElse(new KeyStore());
        if (keyStore.isInvalid()) {
            LOGGER.error("Unable to generate KeyStore, nothing is going to work!");
        }
    }

    /**
     *
     * @return Returns the {@link KeyStore} instance.
     */
    public KeyStore getKeyStore() {
        return keyStore;
    }

    /**
     * Generates a new {@link KeyStore} instance.
     *
     * @return The new {@link KeyStore} instance. Optional.empty() is returned if the key store could not be generated.
     */
    public Optional<KeyStore> generate() {
        final var ks = new KeyStoreGenerator().generate();
        if (ks.isPresent()) {
            storageService.save(ks.get());
        }
        return ks;
    }
}
