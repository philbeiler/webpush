package com.springboot.webpush.controller.model;

import java.util.Collection;

/**
 * Simple bean to describe an enumeration for the UI.
 */
public class Enumeration {
    private final String             canonicalName;
    private final String             simpleName;
    private final Collection<String> values;

    /**
     * Constructs a new {@link Enumeration} instance, to describe a specific Java Enum.
     *
     * @param clazz  The Java Class for the enumeration.
     * @param values The collection of enum values associated with the enumeration.
     */
    public Enumeration(final Class<?> clazz, final Collection<String> values) {
        this.canonicalName = clazz.getCanonicalName();
        this.simpleName    = clazz.getSimpleName();
        this.values        = values;
    }

    /**
     * @return the canonicalName
     */
    public String getCanonicalName() {
        return canonicalName;
    }

    /**
     * @return the name
     */
    public String getSimpleName() {
        return simpleName;
    }

    /**
     * @return the values
     */
    public Collection<String> getValues() {
        return values;
    }

}
