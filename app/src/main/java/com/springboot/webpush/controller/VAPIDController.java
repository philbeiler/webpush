package com.springboot.webpush.controller;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webpush.service.VAPIDService;

@RestController
public class VAPIDController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private final VAPIDService vapidService;

	public VAPIDController(final VAPIDService vapidService) {
		super();
		this.vapidService = vapidService;
	}

	@GetMapping("/api/vapid-public")
	public String vapidPublic() {
		return vapidService.getPublicKey();
	}

}
