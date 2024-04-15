package com.springboot.webpush.controller;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webpush.controller.model.ClientKeyPair;
import com.springboot.webpush.service.KeyPairService;

@RestController
@RequestMapping("/api/keypair")
public class KeyPairController {
    private static final Logger LOGGER         = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private final KeyPairService keyPairService;

	/**
	 * @param keyPairService
	 */
	public KeyPairController(final KeyPairService keyPairService) {
		this.keyPairService = keyPairService;
	}

	@GetMapping
	public ClientKeyPair generate() {
		return keyPairService.generate();
	}

}
