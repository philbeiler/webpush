package com.springboot.webpush.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.springboot.webpush.common.api.types.OnActionClickOperation;
import com.springboot.webpush.controller.model.Enumeration;

import nl.martijndwars.webpush.Urgency;

/**
 * The {@link EnumerationService} manages (collects) all of the enumerations used by the external API.
 */
@Service
public class EnumerationService {

    private final Collection<Enumeration> enumerations = new ArrayList<>();

    /**
     * Constructs a new {@link EnumerationService}.
     */
    public EnumerationService() {
        enumerations.add(new Enumeration(Urgency.class,
                Stream.of(Urgency.values()).map(Urgency::name).collect(Collectors.toList())));
        enumerations.add(new Enumeration(OnActionClickOperation.class, Stream.of(OnActionClickOperation.values())
                .map(OnActionClickOperation::name).collect(Collectors.toList())));
    }

    /**
     * @return the enumerations
     */
    public Collection<Enumeration> getEnumerations() {
        return enumerations;
    }
}
