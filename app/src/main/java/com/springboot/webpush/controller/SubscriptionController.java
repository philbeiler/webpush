package com.springboot.webpush.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webpush.controller.model.WebPushSubscription;
import com.springboot.webpush.service.SubscriptionService;

@RestController
public class SubscriptionController {
	private SubscriptionService subscriptionService;

	public SubscriptionController(SubscriptionService subscriptionService) {
		super();
		this.subscriptionService = subscriptionService;
	}

	@PostMapping("/api/subscribe")
	public void subscribe(@RequestBody WebPushSubscription subscription) {
		subscriptionService.subscribe(subscription);
	}

	@PostMapping("/api/unsubscribe")
	public void unsubscribe(@RequestBody WebPushSubscription subscription) {
		subscriptionService.unsubscribe(subscription);
	}

}
