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

import com.springboot.webpush.common.api.KeyStoreDiskStoreConfiguration;
import com.springboot.webpush.common.api.KeyStoreStorageService;
import com.springboot.webpush.common.util.KeyStoreGenerator;
import com.springboot.webpush.service.KeyStoreDiskStorageService;

/**
 *
 */
class StorageServiceTest {

    private static final String                         NEW_KEYSTORE_NAME = "PushKeyStoreTest.yaml";
    private static final String                         PATH              = "/tmp";
    private static final File                           LOCATION          = new File(PATH + "/" + NEW_KEYSTORE_NAME);
    private static final KeyStoreDiskStoreConfiguration CONFIG            = () -> LOCATION;

    @BeforeAll
    public static void set() {
        Security.addProvider(new BouncyCastleProvider());
        LOCATION.delete();
    }

    @Test
    void create() {
        final KeyStoreStorageService storageService = new KeyStoreDiskStorageService(CONFIG);
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
