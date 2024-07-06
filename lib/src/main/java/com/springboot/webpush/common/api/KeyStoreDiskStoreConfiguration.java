package com.springboot.webpush.common.api;

import java.io.File;

/**
 * The {@link KeyStoreDiskStoreConfiguration} specifies the configuration of the {@link KeyStoreDiskStoreConfiguration}
 * key store, used for encrypting the push messages.
 */

public interface KeyStoreDiskStoreConfiguration {

    /**
     * @return The fully qualified filename to where the key store data should be persisted.
     */
    File getStorage();

}
