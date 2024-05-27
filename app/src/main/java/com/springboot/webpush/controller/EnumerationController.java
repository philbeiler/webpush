package com.springboot.webpush.controller;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webpush.controller.model.Enumeration;
import com.springboot.webpush.service.EnumerationService;

/**
 * The {@link EnumerationController} instance is the used to pull back all of the enumerations and values used by the
 * API.
 */
@RestController
public class EnumerationController {
    private final EnumerationService enumerationService;

    /**
     * Constructs a new {@link EnumerationController} instance.
     *
     * @param enumerationService The {@link EnumerationService} instance.
     */
    public EnumerationController(final EnumerationService enumerationService) {
        this.enumerationService = enumerationService;
    }

    /**
     * Returns all of the enumerations used by the external API.
     *
     * @return A {@link Collection} on Enumeration instances (names and values).
     */
    @GetMapping("/api/enum")
    ResponseEntity<Collection<Enumeration>> getAllValues() {
        return ResponseEntity.ok().body(enumerationService.getEnumerations());
    }

}
