package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.Stringifiable;

/**
 * Enumeration of allowed user types.
 * @since 1.0.0
 */
public enum UserType implements Stringifiable {
    BUSINESS("business"),
    CAFE("cafe"),
    CELLULAR("cellular"),
    COLLEGE("college"),
    CDN("contentDeliveryNetwork"),
    GOVERNMENT("government"),
    HOSTING("hosting"),
    LIBRARY("library"),
    MILITARY("military"),
    RESIDENTIAL("residential"),
    ROUTER("router"),
    SCHOOL("school"),
    SEARCH_ENGINE("searchEngineSpider"),
    TRAVELLER("traveler");

    private String encodedValue;

    private UserType(String encodedValue) {
        this.encodedValue = encodedValue;
    }

    /**
     * Obtain a <tt>UserType</tt> value for the given <tt>encodedValue</tt>.
     * @param encodedValue The encoded value to retrieve the <tt>UserType</tt>
     *                     for.
     * @return The located <tt>UserType</tt> or <tt>null</tt> if
     * <tt>encodedValue</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException If <tt>encodedValue</tt> is non-null but
     * does not correspond to a defined <tt>UserType</tt>.
     */
    public static UserType extract(String encodedValue) {
        if (encodedValue == null) {
            return null;
        }
        for (UserType userType : UserType.values()) {
            if (userType.getEncodedValue().equals(encodedValue)) {
                return userType;
            }
        }
        throw new IllegalArgumentException("Invalid UserType: " + encodedValue);
    }

    public String getEncodedValue() {
        return encodedValue;
    }

    @Override
    public String stringify() {
        return encodedValue;
    }
}
