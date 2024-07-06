package com.springboot.webpush.common.api;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link NotificationStatus} is the response metrics returned to summarize the communication with the remote push
 * services.
 */
public final class NotificationStatus {
    private static final Logger    LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final boolean          error;
    private final int              successCount;
    private final int              failedCount;
    private final Optional<String> message;

    /**
     * Constructs a new instance, and determines the success/failure based on the provided counts.
     *
     * @param success The number of successful interactions.
     * @param failed  The number of failed interactions.
     * @return A new {@link NotificationStatus} instance.
     */
    public static NotificationStatus of(final int success, final int failed) {
        var              error   = false;
        Optional<String> message = Optional.empty();

        if (success == 0) {
            error   = true;
            message = Optional.of("No successful messages sent");
        }
        else if (failed > 0) {
            message = Optional.of("No successful messages sent");
        }
        return new NotificationStatus(error, success, failed, message);
    }

    /**
     * Convenience method to generate an informational (non-error) {@link NotificationStatus} instance that there we no
     * messages to send.
     *
     * @param message The message to be returned as the status.
     * @return A new {@link NotificationStatus} instance.
     */
    public static NotificationStatus info(final String message) {
        LOGGER.info(message);
        return new NotificationStatus(false, 0, 0, Optional.of(message));
    }

    /**
     * Convenience method to generate a {@link NotificationStatus} instance to signal an error.
     *
     * @param message The message to be returned as the error status.
     * @return A new {@link NotificationStatus} instance.
     */

    public static NotificationStatus error(final String message) {
        LOGGER.info(message);
        return new NotificationStatus(true, 0, 0, Optional.of(message));
    }

    /**
     * Private constructor.
     *
     * @param error        Was an error encountered, TRUE or FALSE.
     * @param successCount Number of successfully called push message server interactions.
     * @param failedCount  The number of failed push message server interactions.
     * @param message      The optionally provided status message.
     */
    private NotificationStatus(final boolean error,
                               final int successCount,
                               final int failedCount,
                               final Optional<String> message) {
        this.error        = error;
        this.successCount = successCount;
        this.failedCount  = failedCount;
        this.message      = message;
    }

    @Override
    public String toString() {
        return "NotificationStatus [successCount=" + successCount + ", " + (message != null ? "message=" + message : "")
                + "]";
    }

    /**
     * @return The error indicator, TRUE indicated there was an error, FALSE implies no errors were encountered.
     */
    public boolean isError() {
        return error;
    }

    /**
     * @return The number of successful web push server interactions.
     */
    public int getSuccessCount() {
        return successCount;
    }

    /**
     * @return The number of failed web push server interactions.
     */
    public int getFailedCount() {
        return failedCount;
    }

    /**
     * @return The associated {@link NotificationStatus} message, optionally returned.
     */
    public Optional<String> getMessage() {
        return message;
    }

}
