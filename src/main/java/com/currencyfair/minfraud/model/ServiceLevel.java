package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.Stringifiable;

/**
 * Enumeration of service levels.
 * @since 1.0.0
 */
public enum ServiceLevel implements Stringifiable {
    /**
     * Standard minFraud service.
     */
    STANDARD,
    /**
     * Premium minFraud service.
     */
    PREMIUM;

    /**
     * Obtain the <tt>ServiceLevel</tt> instance for the given
     * <tt>encodeValue</tt>.
     * @param encodedValue The encoded value to obtain the
     *                     <tt>ServiceLevel</tt> for.
     * @return The matched <tt>ServiceLevel</tt>, or <tt>null</tt> if
     * <tt>encodedValue</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException If <tt>encodedValue</tt> is non-null
     * but does not match a defined <tt>ServiceLevel</tt>.
     */
    public static ServiceLevel extract(String encodedValue) {
        if (encodedValue == null) {
            return null;
        }
        for (ServiceLevel serviceLevel : ServiceLevel.values()) {
            if (serviceLevel.name().equalsIgnoreCase(encodedValue)) {
                return serviceLevel;
            }
        }
        throw new IllegalArgumentException("Invalid service level: " + encodedValue);
    }

    @Override
    public String stringify() {
        return name().toLowerCase();
    }
}
