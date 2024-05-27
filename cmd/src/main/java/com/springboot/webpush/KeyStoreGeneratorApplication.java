package com.springboot.webpush;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

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
     * @return Jackson {@link ObjectMapper} instance used for serializing beans to and from JSON.
     */
    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
