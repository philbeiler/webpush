package com.springboot.webpush.common.configuration;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import jakarta.annotation.PostConstruct;

/**
 * The {@link WebPushConfiguration} specifies the configuration of the {@link WebPushConfiguration} key store, used for
 * encrypting the push messages.
 */
@Configuration
@ConfigurationProperties(prefix = "webpush")
public class WebPushConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final String        emailAddress;
    private final String        defaultBody;
    private final String        defaultImageURI;
    private final String        defaultIconURI;
    private final boolean       requireInteraction;

    /**
     * Constructs a new {@link WebPushConfiguration} instance.
     *
     * @param emailAddress       The email address to use when sending push messages, the external administrator email
     *                           address for the application.
     * @param defaultBody        The default body of the notification, if none is provided by the caller.
     * @param defaultImageURI    The default image URI for the notification, if none is provided by the caller.
     * @param defaultIconURI     The default icon URI for the notification, if none is provided by the caller.
     * @param requireInteraction TRUE or FALSE if the default behavior for the notification requires user interaction.
     *
     */
    public WebPushConfiguration(@Value("${email:mailto:admin@domain.com}") final String emailAddress,
            @Value("${default.body:#{null}}") final String defaultBody,
            @Value("${default.imageURI:#{null}}") final String defaultImageURI,
            @Value("${default.iconURI:#{null}}") final String defaultIconURI,
            @Value("${default.requireInteraction:#{null}}") final Boolean requireInteraction) {
        this.emailAddress       = emailAddress;
        this.defaultBody        = StringUtils.hasText(defaultBody) ? defaultBody : "What an exciting notification!";
        this.defaultIconURI     = StringUtils.hasText(defaultIconURI) ? defaultIconURI
                : "https://cdn-icons-png.flaticon.com/512/3845/3845831.png";
        this.defaultImageURI    = StringUtils.hasText(defaultImageURI) ? defaultImageURI
                : "https://cdn-teams-slug.flaticon.com/google.jpg";
        this.requireInteraction = requireInteraction != null ? requireInteraction : true;
    }

    /**
     * Logs the storage information to the logging system.
     */
    @PostConstruct
    void log() {
        LOGGER.info("Email Address       [{}]", getEmailAddress());
        LOGGER.info("Body                [{}]", getDefaultBody());
        LOGGER.info("IconURI             [{}]", getDefaultIconURI());
        LOGGER.info("ImageURI            [{}]", getDefaultImageURI());
        LOGGER.info("Iteraction Required [{}]", getRequireInteraction());
    }

    /**
     * @return The emailAddress of external administrator.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @return the defaultBody
     */
    public String getDefaultBody() {
        return defaultBody;
    }

    /**
     * @return the defaultIconURI
     */
    public String getDefaultIconURI() {
        return defaultIconURI;
    }

    /**
     * @return the defaultImageURI
     */
    public String getDefaultImageURI() {
        return defaultImageURI;
    }

    /**
     * @return the requireInteraction
     */
    public boolean getRequireInteraction() {
        return requireInteraction;
    }

}
