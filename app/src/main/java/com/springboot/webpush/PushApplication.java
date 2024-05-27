package com.springboot.webpush;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * The {@link PushApplication} is a Spring Boot application which implements a web push notification service.
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PushApplication {
    /**
     * Main method.
     *
     * @param args The command line arguments used to start the process.
     */
    public static void main(final String[] args) {
        Security.addProvider(new BouncyCastleProvider());
        SpringApplication.run(PushApplication.class, args);
    }
}
