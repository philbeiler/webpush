package com.springboot.webpush.service;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.springboot.webpush.common.api.KeyStore;
import com.springboot.webpush.common.api.KeyStoreStorageService;
import com.springboot.webpush.config.KeyStoreDiskStoreConfiguration;

import tools.jackson.core.JacksonException;
import tools.jackson.dataformat.yaml.YAMLMapper;

/**
 * The {@link KeyStoreDiskStorageService} is used to preserve the public/private key used to encrypt the push message.
 * The keys are stored on the local file system as specified by the {@link KeyStoreDiskStoreConfiguration} instance.
 */
@Service
public class KeyStoreDiskStorageService implements KeyStoreStorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final File          storage;
    private final YAMLMapper    yamlMapper;

    /**
     * Constructs a new {@link KeyStoreDiskStorageService} instance.
     *
     * @param keystoreConfiguration The {@link KeyStoreDiskStoreConfiguration} instance.
     * @param yamlMapper            The {@link YAMLMapper} instance.
     *
     */
    public KeyStoreDiskStorageService(final KeyStoreDiskStoreConfiguration keystoreConfiguration,
                                      final YAMLMapper yamlMapper) {
        super();
        this.storage    = keystoreConfiguration.getStorage();
        this.yamlMapper = yamlMapper;
    }

    /**
     * Saves the public/private key information to the file system.
     *
     * @param  privateKey The private key.
     * @param  publicKey  The public key.
     *
     * @return            Returns the {@link KeyStore} instance. Optional.empty() is returned if the data could not be
     *                    stored.
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
            if (!storage.exists()) {
                LOGGER.error("File does not exist [{}]", storage);
                return Optional.empty();
            }
            LOGGER.info("Reading existing key store [{}]", storage.getAbsolutePath());
            return Optional.of(yamlMapper.readValue(storage, KeyStore.class));

        }
        catch (final JacksonException e) {
            LOGGER.error("Unable to read file", e);
        }
        return Optional.empty();
    }

    /**
     * Save the {@link KeyStore} instance to persistent storage.
     *
     * @param  keyStore The {@link KeyStore} instance to persist.
     *
     * @return          The {@link KeyStore} instance from storage. Optional.empty() is returned if the data could not
     *                  be saved.
     */
    @Override
    public Optional<KeyStore> save(final KeyStore keyStore) {
        Assert.notNull(keyStore, "Keystore cannot be null");
        final var writer = yamlMapper.writerWithDefaultPrettyPrinter();
        try {
            writer.writeValue(storage, keyStore);
            LOGGER.info("Generating a new key store [{}]", storage.getAbsolutePath());
            return Optional.of(keyStore);
        }
        catch (final JacksonException e) {
            LOGGER.error("Unable to write file", e);
        }
        return Optional.empty();
    }
}
