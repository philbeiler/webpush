package com.springboot.webpush.common.api;

import java.util.Optional;

/**
 * The {@link KeyStore} is a persistent storage interface to allow the key store to be stored in different places
 * (IE.file or database).
 */
public interface KeyStoreStorageService {

    /**
     * Saves the public/private key information to the file system.
     *
     * @param privateKey The private key.
     * @param publicKey  The public key.
     * @return Returns the {@link KeyStore} instance. Optional.empty() is returned if the data could not be stored.
     */
    Optional<KeyStore> save(String privateKey, String publicKey);

    /**
     * Loads the {@link KeyStore} from persistent storage.
     *
     * @return The {@link KeyStore} instance from storage. Optional.empty() is returned if the data could not be
     *         retrieved.
     */
    Optional<KeyStore> load();

    /**
     * Save the {@link KeyStore} instance to persistent storage.
     *
     * @param keyStore The {@link KeyStore} instance to persist.
     *
     * @return The {@link KeyStore} instance from storage. Optional.empty() is returned if the data could not be saved.
     */
    Optional<KeyStore> save(KeyStore keyStore);

}
