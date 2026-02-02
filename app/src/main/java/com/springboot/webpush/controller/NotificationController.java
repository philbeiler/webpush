package com.springboot.webpush.controller;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webpush.common.api.BrowserSubscriptionService;
import com.springboot.webpush.common.api.types.OnActionClickOperation;
import com.springboot.webpush.common.service.NotificationService;
import com.springboot.webpush.controller.converter.PushMessageConverter;
import com.springboot.webpush.controller.model.WebPushMessage;

/**
 * The {@link NotificationController} instance is the primary method for generating Push Notifications from the provided
 * application/service.
 */
@RestController
public class NotificationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final Optional<NotificationService> notificationService;
    private final BrowserSubscriptionService    browserSubscriptionService;
    private final PushMessageConverter          pushMessageConverter;

    /**
     * Constructs a new {@link NotificationController} instance.
     *
     * @param browserSubscriptionService The {@link BrowserSubscriptionService} instance.
     * @param notificationService        The {@link NotificationService} instance.
     * @param pushMessageConverter       The {@link PushMessageConverter} instance.
     */
    public NotificationController(final BrowserSubscriptionService browserSubscriptionService,
                                  final Optional<NotificationService> notificationService,
                                  final PushMessageConverter pushMessageConverter) {
        if (notificationService.isEmpty()) {
            LOGGER.error("PushNotifications are not currently configured");
        }
        this.browserSubscriptionService = browserSubscriptionService;
        this.notificationService        = notificationService;
        this.pushMessageConverter       = pushMessageConverter;
    }

    /**
     * The notify-all method will notify all of the current subscribers with the provide message.
     *
     * @param  message The notification message.
     *
     * @return         A {@link ResponseEntity} OK or internal server error.
     */
    @PutMapping("/api/notify-all")
    ResponseEntity<String> notifyAll(@RequestBody final WebPushMessage message) {

        if (notificationService.isPresent()) {
            message.setOnActionClickOperation(OnActionClickOperation.FOCUS_LAST);
            final var rc = notificationService.get().notifyAll(browserSubscriptionService.getSubscriptions(),
                    pushMessageConverter.convert(message), message.getUrgency());
            if (rc.isError()) {
                return ResponseEntity.internalServerError().body(rc.getMessage().orElse("Fail"));
            }
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
