package com.springboot.webpush.common.service;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.webpush.common.api.NotificationStatus;
import com.springboot.webpush.common.api.PushMessage;
import com.springboot.webpush.common.api.PushSubscription;
import com.springboot.webpush.common.configuration.VAPIDConfiguration;
import com.springboot.webpush.common.util.VAPIDConfigurationAware;

import nl.martijndwars.webpush.Encoding;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Urgency;

@Service
public class NotificationService extends VAPIDConfigurationAware {
    private static final Logger       LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final SubscriptionService subscriptionService;
    private final PushService         pushService;
    private final ObjectMapper        objectMapper;

    public NotificationService(final SubscriptionService subscriptionService, final VAPIDService vapidService,
            final ObjectMapper objectMapper, final VAPIDConfiguration vapidConfiguration) {
        super(vapidConfiguration);
        this.subscriptionService = subscriptionService;
        this.objectMapper        = objectMapper;

        PushService pushService = null;
        try {
            final var keyStore = vapidService.getKeyStore();
            pushService = new PushService(keyStore.getPublicKey(), keyStore.getPrivateKey())
                    .setSubject(vapidConfiguration.getSubject());
        }
        catch (final GeneralSecurityException e) {
            LOGGER.error("Unable to create push service", e);
        }
        finally {
            this.pushService = pushService;
        }
    }

    public NotificationStatus notifyAll(final PushMessage message, Urgency urgency) {

        if (!subscriptionService.hasSubscriptions()) {
            return NotificationStatus.info("No current subscribers.");
        }
        if (pushService == null) {
            return NotificationStatus.error("PushService was not created properly");
        }

        var success = 0;
        var failed  = 0;
        LOGGER.info("Sending [{}]", message);
        for (final PushSubscription subscription : subscriptionService.getSubscriptions()) {
            LOGGER.info("Notifying [{}]", subscription.getEndpoint());

            try {
                // The payload here must be of the form { notification: Notification }
                // Where notification is defined here:
                // https://developer.mozilla.org/en-US/docs/Web/API/Notification

                final var content = objectMapper.writeValueAsString(message);
                LOGGER.info("Content [{}]", content);
                final var notification = new Notification(subscription.get(), content, urgency);
                final var rc           = pushService.send(notification, Encoding.AES128GCM);

                final var statusLine   = rc.getStatusLine();
                if (isOK(statusLine)) {
                    success++;
                    LOGGER.info("Http Status [{}] {}", statusLine.getStatusCode(),
                            EntityUtils.toString(rc.getEntity()));
                }
                else {
                    failed++;
                    LOGGER.warn("Http Status [{}] {}", statusLine.getStatusCode(),
                            EntityUtils.toString(rc.getEntity()));
                }
            }
            catch (IOException | GeneralSecurityException | JoseException | ExecutionException |

                    InterruptedException e) {
                LOGGER.error("Unabled to send notification", e);
            }

        }
        return NotificationStatus.of(success, failed);
    }

    private boolean isOK(final StatusLine statusLine) {
        return statusLine.getStatusCode() >= 200 && statusLine.getStatusCode() < 300;
    }
}
