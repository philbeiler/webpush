package com.springboot.webpush.config;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.springboot.webpush.common.api.NotificationServiceConfiguration;

import jakarta.annotation.PostConstruct;

/**
 * The {@link WebPushConfiguration} specifies the configuration of the {@link WebPushConfiguration} key store, used for
 * encrypting the push messages.
 */
@Configuration
@ConfigurationProperties(prefix = "webpush")
public class WebPushConfiguration implements NotificationServiceConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final String        adminEmailAddress;
    private final String        defaultBody;
    private final String        defaultTag;
    private final String        defaultImageURI;
    private final String        defaultIconURI;
    private final String        defaultBadgeURI;
    private final boolean       renotify;
    private final boolean       requireInteraction;

    /**
     * Constructs a new {@link WebPushConfiguration} instance.
     *
     * @param adminEmailAddress  The email address to use when sending push messages, the external administrator email
     *                           address for the application.
     * @param defaultBody        The default body of the notification, if none is provided by the caller.
     * @param defaultTag         The default tag for the notification, if none is provided by the caller.
     * @param defaultImageURI    The default image URI for the notification, if none is provided by the caller.
     * @param defaultIconURI     The default icon URI for the notification, if none is provided by the caller.
     * @param defaultBadgeURI    The default badge URI for the notification, in not is provided by the caller.
     * @param renotify           TRUE or FALSE, if the default behavior for the notification will re-notify the user.
     * @param requireInteraction TRUE or FALSE if the default behavior for the notification requires user interaction.
     *
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public WebPushConfiguration(@Value("${email:mailto:admin@domain.com}") final String adminEmailAddress,
                                @Value("${default.body:#{null}}") final String defaultBody,
                                @Value("${default.tag:#{null}}") final String defaultTag,
                                @Value("${default.imageURI:#{null}}") final String defaultImageURI,
                                @Value("${default.iconURI:#{null}}") final String defaultIconURI,
                                @Value("${default.badgeURI:#{null}}") final String defaultBadgeURI,
                                @Value("${default.renotify:#{null}}") final Boolean renotify,
                                @Value("${default.requireInteraction:#{null}}") final Boolean requireInteraction) {
        this.adminEmailAddress  = adminEmailAddress;
        this.defaultBody        = StringUtils.hasText(defaultBody) ? defaultBody : "What an exciting notification!";
        this.defaultTag         = StringUtils.hasText(defaultTag) ? defaultTag : "TAG";
        this.defaultIconURI     = StringUtils.hasText(defaultIconURI) ? defaultIconURI
                : "https://cdn-icons-png.flaticon.com/512/3845/3845831.png";
        this.defaultImageURI    = StringUtils.hasText(defaultImageURI) ? defaultImageURI
                : "https://cdn-teams-slug.flaticon.com/google.jpg";
        this.defaultBadgeURI    = StringUtils.hasText(defaultBadgeURI) ? defaultBadgeURI
                : "https://cdn-icons-png.flaticon.com/512/3845/3845131.png";
        this.renotify           = renotify != null ? renotify : false;
        this.requireInteraction = requireInteraction != null ? requireInteraction : true;
    }

    /**
     * Logs the storage information to the logging system.
     */
    @PostConstruct
    void log() {
        LOGGER.info("Email Address       [{}]", getAdminEmailAddress());
        LOGGER.info("Body                [{}]", getDefaultBody());
        LOGGER.info("Tag                 [{}]", getDefaultTag());
        LOGGER.info("IconURI             [{}]", getDefaultIconURI());
        LOGGER.info("ImageURI            [{}]", getDefaultImageURI());
        LOGGER.info("BadgeURI            [{}]", getDefaultBadgeURI());
        LOGGER.info("Re-notify           [{}]", isRenotify());
        LOGGER.info("Iteraction Required [{}]", isRequireInteraction());
    }

    /**
     * @return The adminEmailAddress of external administrator.
     */
    @Override
    public String getAdminEmailAddress() {
        return adminEmailAddress;
    }

    /**
     * @return the defaultBody
     */
    public String getDefaultBody() {
        return defaultBody;
    }

    /**
     * @return the defaultTag
     */
    public String getDefaultTag() {
        return defaultTag;
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
     * @return the defaultBadgeURI
     */
    public String getDefaultBadgeURI() {
        return defaultBadgeURI;
    }

    /**
     * @return the renotify
     */
    public boolean isRenotify() {
        return renotify;
    }

    /**
     * @return the requireInteraction
     */
    public boolean isRequireInteraction() {
        return requireInteraction;
    }

}
