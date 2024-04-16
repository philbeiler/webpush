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

	@PostMapping("/subscribe")
	public void subscribe(@RequestBody WebPushSubscription subscription) {
		subscriptionService.subscribe(subscription);
	}

	@PostMapping("/unsubscribe")
	public void unsubscribe(@RequestBody WebPushSubscription subscription) {
		subscriptionService.unsubscribe(subscription);
	}

	@Deprecated
	@PostMapping("/api/key")
	WebPushSubscription update(@RequestBody final WebPushSubscription pushKey) {
		subscriptionService.subscribe(pushKey);
		return pushKey;
	}
}
