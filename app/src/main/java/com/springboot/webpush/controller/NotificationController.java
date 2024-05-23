package com.springboot.webpush.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webpush.common.service.NotificationService;
import com.springboot.webpush.controller.model.WebPushMessage;

import nl.martijndwars.webpush.Urgency;

@RestController
public class NotificationController {
    private final NotificationService notificationService;

    /**
     *
     * @param notificationService
     */
    public NotificationController(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PutMapping("/api/notify-all")
    ResponseEntity<String> notifyAll(@RequestBody final WebPushMessage message) {
        final var rc = notificationService.notifyAll(message.get(), message.getUrgency());
        if (rc.isError()) {
            return ResponseEntity.internalServerError().body(rc.getMessage().orElse("Fail"));
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/urgency")
    ResponseEntity<Collection<Urgency>> getUrgencyValues() {
        return ResponseEntity.ok().body(List.of(Urgency.values()));
    }

}
