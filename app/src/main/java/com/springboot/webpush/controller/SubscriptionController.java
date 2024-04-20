package com.springboot.webpush.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webpush.common.service.SubscriptionService;
import com.springboot.webpush.controller.model.WebPushSubscription;

@RestController
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(final SubscriptionService subscriptionService) {
        super();
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/api/subscribe")
    ResponseEntity<String> subscribe(@RequestBody final WebPushSubscription subscription) {
        subscriptionService.subscribe(subscription.get());
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/api/unsubscribe")
    ResponseEntity<String> unsubscribe(@RequestBody final WebPushSubscription subscription) {
        if (subscriptionService.unsubscribe(subscription.get())) {
            return ResponseEntity.ok("OK");
        }
        return ResponseEntity.notFound().build();
    }
}
