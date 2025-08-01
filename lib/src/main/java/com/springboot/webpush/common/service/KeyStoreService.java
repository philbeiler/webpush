package com.springboot.webpush.common.service;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import com.springboot.webpush.common.api.KeyStore;
import com.springboot.webpush.common.api.KeyStoreStorageService;
import com.springboot.webpush.common.util.KeyStoreGenerator;

/**
 * The {@link KeyStoreService} is responsible for managing (creating/retrieving/providing) the key information required
 * to encrypt the push messages.
 */
@Service
@ConditionalOnBean(KeyStoreStorageService.class)
public class KeyStoreService {
    private static final Logger          LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final KeyStoreStorageService storageService;

    /**
     * Constructs a new instance of the {@link KeyStoreService}.
     *
     * @param storageService The {@link KeyStoreDiskStorageService} instance
     */
    public KeyStoreService(final KeyStoreStorageService storageService) {
        super();
        this.storageService = storageService;
    }

    /**
     * Return the {@link KeyStore} instance. A new one will be created if not found.
     *
     * @return The {@link KeyStore} instance.
     */
    public KeyStore getKeyStore() {
        var keyStore = storageService.load();
        if (keyStore.isEmpty()) {
            keyStore = generate();
        }

        if (keyStore.isEmpty() || keyStore.get().isInvalid()) {
            LOGGER.error("Unable to generate KeyStore, nothing is going to work!");
        }
        return keyStore.orElse(new KeyStore());
    }

    /**
     * Generates a new {@link KeyStore} instance.
     *
     * @return The new {@link KeyStore} instance. Optional.empty() is returned if the key store could not be generated.
     */
    public final Optional<KeyStore> generate() {
        final var ks = new KeyStoreGenerator().generate();
        if (ks.isPresent()) {
            storageService.save(ks.get());
        }
        return ks;
    }
}
