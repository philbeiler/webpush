package com.springboot.webpush.controller.converter;

import java.time.Instant;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.springboot.webpush.common.api.NotificationData;
import com.springboot.webpush.common.api.PushMessage;
import com.springboot.webpush.common.api.types.OnActionClickOperation;
import com.springboot.webpush.config.WebPushConfiguration;
import com.springboot.webpush.controller.model.WebPushMessage;

/**
 * The {@link PushMessageConverter} is used to convert the Web (external API) messages to the internal API. It will
 * attempt to default any parameters that were not provide to generate a consistent notification message to the user.
 * These classes are typically static, however this one is a singleton to provide customizable default behavior and
 * values.
 */
@Component
public class PushMessageConverter {
    private final WebPushConfiguration webPushConfiguration;

    /**
     * Constructs a new {@link PushMessageConverter}.
     *
     * @param webPushConfiguration The {@link WebPushConfiguration} instance, default values and settings.
     */
    public PushMessageConverter(final WebPushConfiguration webPushConfiguration) {
        this.webPushConfiguration = webPushConfiguration;
    }

    /**
     * Converts a user facing external API to the required internal data format.
     *
     * @param message The external {@link WebPushMessage} instance.
     * @return An internal {@link PushMessage} instance, created from the input parameter and default configuration
     *         values.
     */
    public PushMessage convert(final WebPushMessage message) {
        return PushMessage.of(message.getTitle(),
                StringUtils.hasText(message.getBody()) ? message.getBody() : webPushConfiguration.getDefaultBody(),
                StringUtils.hasText(message.getTag()) ? message.getTag() : webPushConfiguration.getDefaultTag(),
                NotificationData.of(message.getOnActionClickOperation() != null ? message.getOnActionClickOperation()
                        : OnActionClickOperation.NOOP, message.getOnActionClickURI()),
                StringUtils.hasText(message.getIconURI()) ? message.getIconURI()
                        : webPushConfiguration.getDefaultIconURI(),
                StringUtils.hasText(message.getImageURI()) ? message.getImageURI()
                        : webPushConfiguration.getDefaultImageURI(),
                StringUtils.hasText(message.getBadgeURI()) ? message.getBadgeURI()
                        : webPushConfiguration.getDefaultBadgeURI(),
                Instant.now().toEpochMilli(),
                message.getRenotify() != null ? message.getRenotify() : webPushConfiguration.isRenotify(),
                message.getRequireInteraction() != null ? message.getRequireInteraction()
                        : webPushConfiguration.isRequireInteraction());
    }

}
