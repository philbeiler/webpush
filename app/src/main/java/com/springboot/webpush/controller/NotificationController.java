package com.springboot.webpush.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    private final NotificationService  notificationService;
    private final PushMessageConverter pushMessageConverter;

    /**
     * Constructs a new {@link NotificationController} instance.
     *
     * @param notificationService  The {@link NotificationService} instance.
     * @param pushMessageConverter The {@link PushMessageConverter} instance.
     */
    public NotificationController(final NotificationService notificationService,
            final PushMessageConverter pushMessageConverter) {
        this.notificationService  = notificationService;
        this.pushMessageConverter = pushMessageConverter;
    }

    /**
     * The notify-all method will notify all of the current subscribers with the provide message.
     *
     * @param message The notification message.
     * @return A {@link ResponseEntity} OK or internal server error.
     */
    @PutMapping("/api/notify-all")
    ResponseEntity<String> notifyAll(@RequestBody final WebPushMessage message) {

        message.setOnActionClickOperation(OnActionClickOperation.FOCUS_LAST);
        final var rc = notificationService.notifyAll(pushMessageConverter.convert(message), message.getUrgency());
        if (rc.isError()) {
            return ResponseEntity.internalServerError().body(rc.getMessage().orElse("Fail"));
        }
        return ResponseEntity.ok().build();
    }

}
