package com.springboot.webpush.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webpush.controller.model.ClientKeyPair;
import com.springboot.webpush.service.KeyPairService;

@RestController
@RequestMapping("/api/keypair")
public class KeyPairController {

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
