package com.springboot.webpush.common.service;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.webpush.common.api.KeyStore;
import com.springboot.webpush.common.configuration.VAPIDConfiguration;
import com.springboot.webpush.common.util.VAPIDConfigurationAware;

@Service
public class StorageService extends VAPIDConfigurationAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final ObjectMapper  objectMapper;

    /**
     * @param objectMapper
     */
    public StorageService(final ObjectMapper objectMapper, final VAPIDConfiguration vapidConfiguration) {
        super(vapidConfiguration);
        this.objectMapper = objectMapper;
    }

    public Optional<KeyStore> save(final String privateKey, final String publicKey) {
        final var rc = new KeyStore();
        rc.setPrivateKey(privateKey);
        rc.setPublicKey(publicKey);
        return save(rc);
    }

    public Optional<KeyStore> load() {

        try {
            final var storage = getVapidConfiguration().getStorage();
            if (!storage.exists()) {
                LOGGER.error("File does not exist [{}]", storage);
                return Optional.empty();
            }
            LOGGER.info("Reading existing VAPID key [{}]", storage.getAbsolutePath());
            return Optional.of(objectMapper.readValue(storage, KeyStore.class));
        }
        catch (final IOException e) {
            LOGGER.error("Unable to read file", e);
        }
        return Optional.empty();
    }

    public Optional<KeyStore> save(final KeyStore keyStore) {
        final var writer = objectMapper.writer(new DefaultPrettyPrinter());
        try {
            final var storage = getVapidConfiguration().getStorage();
            writer.writeValue(storage, keyStore);
            LOGGER.info("Generating a new VAPID key [{}]", storage.getAbsolutePath());
            return Optional.of(keyStore);
        }
        catch (final IOException e) {
            LOGGER.error("Unable to write file", e);
        }
        return Optional.empty();
    }
}
