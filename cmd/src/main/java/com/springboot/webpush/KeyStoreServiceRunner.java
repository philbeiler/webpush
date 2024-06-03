package com.springboot.webpush;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springboot.webpush.common.service.KeyStoreService;

/**
 * The {@link KeyStoreServiceRunner} is the Spring Boot implementation for a command line application.
 */
@Component
public class KeyStoreServiceRunner implements CommandLineRunner {

    private static final Logger   LOGGER  = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String   COMMAND = "REGENERATE";
    private final KeyStoreService keyStoreService;

    /**
     * Constructs a new {@link KeyStoreServiceRunner} instance.
     *
     * @param keyStoreService The {@link KeyStoreService} instance.
     */
    public KeyStoreServiceRunner(final KeyStoreService keyStoreService) {
        super();
        this.keyStoreService = keyStoreService;
    }

    @Override
    public void run(final String... args) throws Exception {

        if (args.length != 1 || !COMMAND.equals(args[0])) {
            LOGGER.error("Utility only accepts one arugument [{}]", COMMAND);
            System.exit(1);
        }
        LOGGER.info("Running...");
        keyStoreService.generate();
    }
}
