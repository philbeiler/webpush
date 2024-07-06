package test.com.springboot.webpush.common.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.springboot.webpush.config.KeyStoreDiskStoreConfiguration;

class KeyStoreConfigurationTest {

    private static final String NAME = "PushKeyStore.yaml";
    private static final String PATH = "/tmp";

    @Test
    void test() {
        final var config = new KeyStoreDiskStoreConfiguration(PATH, NAME);
        assertNotNull(config);
        assertEquals(PATH + "/" + NAME, config.getStorage().getAbsolutePath());
    }

}
