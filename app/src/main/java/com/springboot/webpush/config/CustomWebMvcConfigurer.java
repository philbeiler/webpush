package com.springboot.webpush.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The {@link CustomWebMvcConfigurer} is used to configure the web controller (CORS, security, etc.).
 */
@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {
    /**
     * Adjust the CORS mappings.
     */
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }
}
