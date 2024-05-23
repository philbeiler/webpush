package com.springboot.webpush.common.configuration;

import java.io.File;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix = "vapid")
public class VAPIDConfiguration {
    private static final Logger LOGGER     = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String STORE_NAME = "PushKeyStore.json";
    private File                storage;
    private String              subject;

    /**
     * @param storageDirectory
     * @param subject
     */
    public VAPIDConfiguration(@Value("${storage-directory:/tmp}") final String storageDirectory,
            @Value("${subject:mailto:admin@domain.com}") final String subject) {
        this.subject = subject;
        this.storage = new File(storageDirectory, STORE_NAME);

    }

    @PostConstruct
    void log() {
        LOGGER.info("Storage [{}]", storage);
        LOGGER.info("Subject [{}]", subject);
    }

    /**
     * @return the storageDirectory
     */
    public File getStorage() {
        return storage;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param storage the storage to set
     */
    public void setStorage(final File storage) {
        this.storage = storage;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(final String subject) {
        this.subject = subject;
    }

}
