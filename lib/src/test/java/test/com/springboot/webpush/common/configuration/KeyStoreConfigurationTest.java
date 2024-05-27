package test.com.springboot.webpush.common.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.springboot.webpush.common.configuration.KeyStoreConfiguration;

class KeyStoreConfigurationTest {

    private static final String NAME = "PushKeyStore.yaml";
    private static final String PATH = "/tmp";

    @Test
    void test() {
        final var config = new KeyStoreConfiguration(PATH, NAME);
        assertNotNull(config);
        assertEquals(PATH + "/" + NAME, config.getStorage().getAbsolutePath());
    }

}
