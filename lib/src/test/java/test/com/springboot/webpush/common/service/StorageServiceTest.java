/**
 *
 */
package test.com.springboot.webpush.common.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.springboot.webpush.common.configuration.KeyStoreConfiguration;
import com.springboot.webpush.common.service.StorageService;
import com.springboot.webpush.common.util.KeyStoreGenerator;

/**
 *
 */
class StorageServiceTest {

    private static final String NEW_KEYSTORE_NAME = "PushKeyStoreTest.yaml";
    private static final String PATH              = "/tmp";

    @BeforeAll
    public static void set() {
        Security.addProvider(new BouncyCastleProvider());
        new File(PATH + "/" + NEW_KEYSTORE_NAME).delete();
    }

    @Test
    void create() {
        final var storageService = new StorageService(new KeyStoreConfiguration(PATH, NEW_KEYSTORE_NAME));
        assertNotNull(storageService);
        var keys = storageService.load();
        assertTrue(keys.isEmpty());

        final var keyStore = new KeyStoreGenerator().generate();
        assertTrue(keyStore.isPresent());
        assertTrue(keyStore.get().isValid());
        storageService.save(keyStore.get());

        keys = storageService.load();
        assertTrue(keys.isPresent());
        assertNotNull(keys.get().getPublicKey());
        assertNotNull(keys.get().getPrivateKey());
    }

}
