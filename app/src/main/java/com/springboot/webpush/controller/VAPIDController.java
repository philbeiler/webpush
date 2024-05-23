package com.springboot.webpush.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webpush.common.service.VAPIDService;

@RestController
public class VAPIDController {
    private final VAPIDService vapidService;

    public VAPIDController(final VAPIDService vapidService) {
        super();
        this.vapidService = vapidService;
    }

    @GetMapping("/api/vapid-public")
    ResponseEntity<String> getVapidPublic() {

        final var keyStore = vapidService.getKeyStore();
        if (keyStore.valid()) {
            return ResponseEntity.ok(keyStore.getPublicKey());
        }
        return ResponseEntity.notFound().build();
    }

}
