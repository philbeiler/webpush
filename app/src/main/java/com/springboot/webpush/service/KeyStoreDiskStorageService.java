package com.springboot.webpush.service;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.springboot.webpush.common.api.KeyStore;
import com.springboot.webpush.common.api.KeyStoreDiskStoreConfiguration;
import com.springboot.webpush.common.api.KeyStoreStorageService;

/**
 * The {@link KeyStoreDiskStorageService} is used to preserve the public/private key used to encrypt the push message.
 * The keys are stored on the local file system as specified by the {@link KeyStoreDiskStoreConfiguration} instance.
 */
@Service
public class KeyStoreDiskStorageService implements KeyStoreStorageService {
    private static final Logger                  LOGGER       = LoggerFactory
            .getLogger(MethodHandles.lookup().lookupClass());

    private final ObjectMapper                   objectMapper = new ObjectMapper(new YAMLFactory());
    private final KeyStoreDiskStoreConfiguration keystoreConfiguration;

    /**
     * Constructs a new {@link KeyStoreDiskStorageService} instance.
     *
     * @param keystoreConfiguration The {@link KeyStoreDiskStoreConfiguration} instance.
     *
     */
    public KeyStoreDiskStorageService(final KeyStoreDiskStoreConfiguration keystoreConfiguration) {
        super();
        this.keystoreConfiguration = keystoreConfiguration;
    }

    /**
     * Saves the public/private key information to the file system.
     *
     * @param privateKey The private key.
     * @param publicKey  The public key.
     * @return Returns the {@link KeyStore} instance. Optional.empty() is returned if the data could not be stored.
     */
    @Override
    public Optional<KeyStore> save(final String privateKey, final String publicKey) {
        final var rc = new KeyStore();
        rc.setPrivateKey(privateKey);
        rc.setPublicKey(publicKey);
        return save(rc);
    }

    /**
     * Loads the {@link KeyStore} from persistent storage.
     *
     * @return The {@link KeyStore} instance from storage. Optional.empty() is returned if the data could not be
     *         retrieved.
     */
    @Override
    public Optional<KeyStore> load() {
        try {
            final var storage = keystoreConfiguration.getStorage();
            if (!storage.exists()) {
                LOGGER.error("File does not exist [{}]", storage);
                return Optional.empty();
            }
            LOGGER.info("Reading existing key store [{}]", storage.getAbsolutePath());
            return Optional.of(objectMapper.readValue(storage, KeyStore.class));
        }
        catch (final IOException e) {
            LOGGER.error("Unable to read file", e);
        }
        return Optional.empty();
    }

    /**
     * Save the {@link KeyStore} instance to persistent storage.
     *
     * @param keyStore The {@link KeyStore} instance to persist.
     *
     * @return The {@link KeyStore} instance from storage. Optional.empty() is returned if the data could not be saved.
     */
    @Override
    public Optional<KeyStore> save(final KeyStore keyStore) {
        Assert.notNull(keyStore, "Keystore cannot be null");
        final var writer = objectMapper.writer(new DefaultPrettyPrinter());
        try {
            final var storage = keystoreConfiguration.getStorage();
            writer.writeValue(storage, keyStore);
            LOGGER.info("Generating a new key store [{}]", storage.getAbsolutePath());
            return Optional.of(keyStore);
        }
        catch (final IOException e) {
            LOGGER.error("Unable to write file", e);
        }
        return Optional.empty();
    }
}
