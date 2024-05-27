package com.springboot.webpush.common.configuration;

import java.io.File;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

/**
 * The {@link KeyStoreConfiguration} specifies the configuration of the {@link KeyStoreConfiguration} key store, used
 * for encrypting the push messages.
 */
@Configuration
@ConfigurationProperties(prefix = "keystore")
public class KeyStoreConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final File          storage;

    /**
     * Constructs a new {@link KeyStoreConfiguration} instance.
     *
     * @param storageDirectory The storage directory path.
     * @param storageName      The file name of the storage.
     */
    public KeyStoreConfiguration(@Value("${storage-directory:/tmp}") final String storageDirectory,
            @Value("${storage-name:PushKeyStore.yaml}") final String storageName) {
        this.storage = new File(storageDirectory, storageName);
    }

    /**
     * Logs the storage information to the logging system.
     */
    @PostConstruct
    void log() {
        LOGGER.info("Storage [{}]", storage);
    }

    /**
     * @return The storageDirectory, fully qualified path.
     */
    public File getStorage() {
        return storage;
    }

}
