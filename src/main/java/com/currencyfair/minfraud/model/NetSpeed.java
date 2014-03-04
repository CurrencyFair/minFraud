package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.Stringifiable;

/**
 * Enumeration of network speeds.
 * @see GeoIpDetails
 * @since 1.0.0
 */
public enum NetSpeed implements Stringifiable {
    DIALUP("Dialup"),
    CABLE_DSL("Cable/DSL"),
    CORPORATE("Corporate"),
    CELLULAR("Cellular");

    private String encodedValue;

    private NetSpeed(String encodedValue) {
        this.encodedValue = encodedValue;
    }

    /**
     * Obtain a <tt>NetSpeed</tt> value for the given <tt>encodedValue</tt>.
     * @param encodedValue The encoded value to retrieve the <tt>NetSpeed</tt>
     *                     for.
     * @return The located <tt>NetSpeed</tt> or <tt>null</tt> if
     * <tt>encodedValue</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException If <tt>encodedValue</tt> is non-null but
     * does not correspond to a defined <tt>NetSpeed</tt>.
     */
    public static NetSpeed extract(String encodedValue) {
        if (encodedValue == null) {
            return null;
        }
        for (NetSpeed netSpeed : NetSpeed.values()) {
            if (netSpeed.getEncodedValue().equals(encodedValue)) {
                return netSpeed;
            }
        }
        throw new IllegalArgumentException("Invalid NetSpeed: " + encodedValue);
    }

    public String getEncodedValue() {
        return encodedValue;
    }

    @Override
    public String stringify() {
        return encodedValue;
    }
}
