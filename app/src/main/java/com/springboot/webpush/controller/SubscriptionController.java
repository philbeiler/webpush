package com.springboot.webpush.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.webpush.controller.model.WebPushSubscription;
import com.springboot.webpush.service.NotificationService;
import com.springboot.webpush.service.SubscriptionService;
import java.util.Base64;
import java.security.PublicKey;

@RestController
public class SubscriptionController {

	private SubscriptionService subscriptionService;
	private NotificationService notificationService;

	public SubscriptionController(SubscriptionService subscriptionService, NotificationService notificationService) {
		super();
		this.subscriptionService = subscriptionService;
		this.notificationService = notificationService;
	}

	@PostMapping("/api/subscribe")
	public void subscribe(@RequestBody WebPushSubscription subscription) {
		subscriptionService.subscribe(subscription);
	}

	@PostMapping("/api/unsubscribe")
	public void unsubscribe(@RequestBody WebPushSubscription subscription) {
		subscriptionService.unsubscribe(subscription);
	}

	@GetMapping("/api/vapid-public")
	public String vapidPublic() {
		/*
		 * PublicKey publicKey = this.notificationService.getPublicKey();
		 * System.out.println(publicKey);
		 * byte[] encodedPublicKey = publicKey.getEncoded();
		 * String b64PublicKey = Base64.getEncoder().encodeToString(encodedPublicKey);
		 * return b64PublicKey;
		 */
		return "BJ3kKgvZR34Wt-dJMG9L7Lh03ISRlWueIgg-Vp5V5oYsDPr47_szfNsxxLQrghPtuN3XaI6A3FVZVZuVa7LZOFY";
	}

}
