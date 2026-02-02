package com.springboot.webpush;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tools.jackson.databind.json.JsonMapper;

/**
 * The {@link KeyStoreGeneratorApplication} creates new keystores (public/private) that are used to encrypt the
 * Notification push messages.
 */
@SpringBootApplication
public class KeyStoreGeneratorApplication {
    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        Security.addProvider(new BouncyCastleProvider());
        SpringApplication.run(KeyStoreGeneratorApplication.class, args);
    }

    /**
     *
     * @return Jackson {@link JsonMapper} instance used for serializing beans to and from JSON.
     */
    @Bean
    JsonMapper objectMapper() {
        return new JsonMapper();
    }
}
