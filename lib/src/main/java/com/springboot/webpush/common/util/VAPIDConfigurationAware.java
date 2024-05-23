/**
 *
 */
package com.springboot.webpush.common.util;

import com.springboot.webpush.common.configuration.VAPIDConfiguration;

/**
 *
 */
public class VAPIDConfigurationAware {
    private final VAPIDConfiguration vapidConfiguration;

    /**
     * @param vapidConfiguration
     */
    public VAPIDConfigurationAware(final VAPIDConfiguration vapidConfiguration) {
        this.vapidConfiguration = vapidConfiguration;
    }

    /**
     * @return the vapidConfiguration
     */
    public VAPIDConfiguration getVapidConfiguration() {
        return vapidConfiguration;
    }

}
