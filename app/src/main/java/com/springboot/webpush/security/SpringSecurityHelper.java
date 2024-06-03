package com.springboot.webpush.security;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Small helper class to grab the user name from the {@link SecurityContextHolder}. Will be valid when Spring Security
 * is enabled.
 */
public final class SpringSecurityHelper {
    /**
     * Returns the currently authenticated (logged in) user from Spring Security.
     *
     * @return The authenticated user's name.
     */
    public static String get() {
        final var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            return "Unknown";
        }
        return authentication.getName();

    }

}
