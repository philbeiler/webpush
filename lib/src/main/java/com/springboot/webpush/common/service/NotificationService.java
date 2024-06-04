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
import com.springboot.webpush.common.configuration.WebPushConfiguration;

import nl.martijndwars.webpush.Encoding;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Urgency;

/**
 * The {@link NotificationService} is responsible for sending a
 * {@link PushMessage} to all of the current application subscribers.
 */
@Service
public class NotificationService {
    private static final int          HTTP_REDIRECT_CODES = 300;
    private static final int          HTTP_SUCCESS_CODES  = 200;
    private static final Logger       LOGGER              = LoggerFactory
            .getLogger(MethodHandles.lookup().lookupClass());

    private final SubscriptionService subscriptionService;
    private final PushService         pushService;
    private final ObjectMapper        objectMapper;

    /**
     * Constructs a new instance of the {@link NotificationService}.
     *
     * @param subscriptionService  The {@link SubscriptionService} instance
     * @param keystoreService      The {@link KeyStoreService} instance
     * @param objectMapper         The {@link ObjectMapper} instance
     * @param webPushConfiguration The {@link WebPushConfiguration} instance
     */
    public NotificationService(final SubscriptionService subscriptionService, final KeyStoreService keystoreService,
            final ObjectMapper objectMapper, final WebPushConfiguration webPushConfiguration) {
        super();
        this.subscriptionService = subscriptionService;
        this.objectMapper        = objectMapper;

        PushService aPushService = null;
        try {
            final var keyStore = keystoreService.getKeyStore();
            aPushService = new PushService(keyStore.getPublicKey(), keyStore.getPrivateKey())
                    .setSubject(webPushConfiguration.getEmailAddress());
        }
        catch (final GeneralSecurityException e) {
            LOGGER.error("Unable to create push service", e);
        }
        finally {
            this.pushService = aPushService;
        }
    }

    /**
     * This method will send the {@link PushMessage} to all of the current
     * subscribers, with the provided {@link Urgency}.
     *
     * @param message The {@link PushMessage} to send
     * @param urgency The {@link Urgency} of the message
     * @return A {@link NotificationStatus} instance, success or fail metrics
     */
    public NotificationStatus notifyAll(final PushMessage message, final Urgency urgency) {

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

    /**
     * Primary method to determine the status of the message sent to the
     * notification push service.
     *
     * @param statusLine The {@link StatusLine} instance (returned from the HTTP
     *                   call)
     * @return TRUE if the message was delivered successfully, otherwise FALSE
     */
    private boolean isOK(final StatusLine statusLine) {
        return statusLine.getStatusCode() >= HTTP_SUCCESS_CODES && statusLine.getStatusCode() < HTTP_REDIRECT_CODES;
    }
}
