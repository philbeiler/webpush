package com.springboot.webpush.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webpush.common.service.KeyStoreService;

/**
 * The {@link VAPIDController} is responsible to returning the KeyStore public key.
 */
@RestController
@Deprecated
public class VAPIDController {
    private final Optional<KeyStoreService> keyStoreService;

    /**
     * Constructs a new instance of the {@link VAPIDController}.
     *
     * @param keyStoreService The {@link KeyStoreService} instance
     */
    public VAPIDController(final Optional<KeyStoreService> keyStoreService) {
        super();
        this.keyStoreService = keyStoreService;
    }

    @GetMapping("/api/vapid-public")
    ResponseEntity<String> getVapidPublic() {

        if (keyStoreService.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        final var keyStore = keyStoreService.get().getKeyStore();
        if (keyStore.isValid()) {
            return ResponseEntity.ok(keyStore.getPublicKey());
        }
        return ResponseEntity.notFound().build();
    }

}
