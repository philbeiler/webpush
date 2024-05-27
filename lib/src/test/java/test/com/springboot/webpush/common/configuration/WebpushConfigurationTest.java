package test.com.springboot.webpush.common.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.springboot.webpush.common.configuration.WebPushConfiguration;

/**
 * {@link WebpushConfigurationTest} class.
 */
class WebpushConfigurationTest {

    private static final String EMAIL_ADDRESS = "admin@domain.com";
    private static final String BODY          = "What an exciting notification!";
    private static final String ICON          = "https://cdn-icons-png.flaticon.com/512/3845/3845831.png";
    private static final String IMAGE         = "https://cdn-teams-slug.flaticon.com/google.jpg";

    /**
     * Tests the email method.
     */
    @Test
    void config() {
        final var config = new WebPushConfiguration(EMAIL_ADDRESS, null, null, null, false);

        assertEquals(EMAIL_ADDRESS, config.getEmailAddress());
        assertEquals(BODY, config.getDefaultBody());
        assertEquals(ICON, config.getDefaultIconURI());
        assertEquals(IMAGE, config.getDefaultImageURI());
        assertFalse(config.getRequireInteraction());
    }

}
