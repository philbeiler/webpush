package com.springboot.webpush.common.api;

import com.springboot.webpush.common.service.NotificationService;

/**
 * Interface to externalize the {@link NotificationService} configuration requirements.
 */
public interface NotificationServiceConfiguration {

    /**
     * Return the email address of external administrator.
     *
     * @return The emailAddress of external administrator.
     */
    String getAdminEmailAddress();

}
