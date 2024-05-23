package com.springboot.webpush.common.api;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationStatus {
    private static final Logger    LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final boolean          error;
    private final int              successCount;
    private final int              failedCount;
    private final Optional<String> message;

    public static NotificationStatus of(int success, int failed) {
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

    public static NotificationStatus info(String message) {
        LOGGER.info(message);
        return new NotificationStatus(false, 0, 0, Optional.of(message));
    }

    public static NotificationStatus error(String message) {
        LOGGER.info(message);
        return new NotificationStatus(true, 0, 0, Optional.of(message));
    }

    /**
     *
     * @param error
     * @param successCount
     * @param failedCount
     * @param message
     */
    private NotificationStatus(boolean error, int successCount, int failedCount, Optional<String> message) {
        super();
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
     * @return the error
     */
    public boolean isError() {
        return error;
    }

    /**
     * @return the successCount
     */
    public int getSuccessCount() {
        return successCount;
    }

    /**
     * @return the failedCount
     */
    public int getFailedCount() {
        return failedCount;
    }

    /**
     * @return the message
     */
    public Optional<String> getMessage() {
        return message;
    }

}
