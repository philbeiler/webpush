package com.springboot.webpush;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springboot.webpush.common.service.VAPIDService;

@Component
public class VAPIDServiceRunner implements CommandLineRunner {

    private static final Logger LOGGER  = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String COMMAND = "REGENERATE";
    private final VAPIDService  vapidService;

    public VAPIDServiceRunner(VAPIDService vapidService) {
        super();
        this.vapidService = vapidService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (args.length != 1 || !COMMAND.equals(args[0])) {
            LOGGER.error("Utility only accepts one arugument [{}]", COMMAND);
            System.exit(1);
        }
        LOGGER.info("Running...");
        vapidService.generate();
    }
}
