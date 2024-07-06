package com.springboot.webpush.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.springboot.webpush.common.api.BrowserSubscriptionService;
import com.springboot.webpush.controller.converter.WebPushSubscriptionConverter;
import com.springboot.webpush.controller.model.WebPushSubscription;

/**
 * The {@link BrowserSubscriptionController} is used to manage the user's subscription to the push notification service.
 */
@RestController
public class BrowserSubscriptionController {
    private final BrowserSubscriptionService browserSubscriptionService;

    /**
     * Constructs a new {@link BrowserSubscriptionController} instance.
     *
     * @param browserSubscriptionService The {@link BrowserSubscriptionService} instance.
     */
    public BrowserSubscriptionController(final BrowserSubscriptionService browserSubscriptionService) {
        super();
        this.browserSubscriptionService = browserSubscriptionService;
    }

    /**
     * The subscribe method is used to subscribe users to the notification service.
     *
     * @param subscription The browser's subscription information.
     * @returns {@link ResponseEntity} OK
     */
    @PostMapping("/api/subscribe")
    ResponseEntity<String> subscribe(@RequestBody final WebPushSubscription subscription) {
        browserSubscriptionService.subscribe(WebPushSubscriptionConverter.convert(subscription));
        return ResponseEntity.ok("OK");
    }

    /**
     * The unsubscribe method is used to unsubscribe users from the notification service.
     *
     * @param subscription The browser's subscription information.
     * @return Returns {@link EntityResponse} OK when successful, otherwise {@link EntityResponse} NotFound.
     */
    @PostMapping("/api/unsubscribe")
    ResponseEntity<String> unsubscribe(@RequestBody final WebPushSubscription subscription) {
        if (browserSubscriptionService.unsubscribe(WebPushSubscriptionConverter.convert(subscription))) {
            return ResponseEntity.ok("OK");
        }
        return ResponseEntity.notFound().build();
    }
}
