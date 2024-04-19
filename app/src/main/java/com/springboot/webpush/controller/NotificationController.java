package com.springboot.webpush.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webpush.controller.model.WebPushMessage;
import com.springboot.webpush.service.NotificationService;

@RestController
public class NotificationController {
	private final NotificationService notificationService;

	public NotificationController(final NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@PutMapping("/api/notify-all")
	public WebPushMessage notifyAll(@RequestBody final WebPushMessage message) {
		notificationService.notifyAll(message);
		return message;
	}

}
