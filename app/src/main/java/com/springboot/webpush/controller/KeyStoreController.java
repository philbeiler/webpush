package com.springboot.webpush.controller;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webpush.common.service.KeyStoreService;

/**
 * The {@link KeyStoreController} is responsible to returning the KeyStore public key.
 */
@RestController
public class KeyStoreController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final Optional<KeyStoreService> keyStoreService;

    /**
     * Constructs a new instance of the {@link KeyStoreController}.
     *
     * @param keyStoreService The {@link KeyStoreService} instance
     */
    public KeyStoreController(final Optional<KeyStoreService> keyStoreService) {
        super();
        this.keyStoreService = keyStoreService;
    }

    @GetMapping("/api/keystore/public")
    ResponseEntity<String> getVapidPublic() {

        if (keyStoreService.isPresent()) {
            final var keyStore = keyStoreService.get().getKeyStore();
            if (keyStore.isValid()) {
                return ResponseEntity.ok(keyStore.getPublicKey());
            }
            LOGGER.error("KeyStore is not valid");
        }
        else {
            LOGGER.error("KeyStoreService is not properly configured");
        }
        return ResponseEntity.notFound().build();
    }

}
