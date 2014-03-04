package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.Stringifiable;

/**
 * Enumeration representing the 4-state match result of yes, no, not found or
 * not applicable.
 * @since 1.0.0
 */
public enum MatchType implements Stringifiable {
    YES("Yes", true),
    NO("No", false),
    NOT_FOUND("NotFound", false),
    NA("NA", false);

    private String encodedValue;
    private boolean matched;

    private MatchType(String encodedValue, boolean matched) {
        this.encodedValue = encodedValue;
        this.matched = matched;
    }

    /**
     * Return the <tt>MatchType</tt> corresponding to <tt>encodedValue</tt>.
     * @param encodedValue The encoded value to obtain the <tt>MatchType</tt>
     *                     for.
     * @return The corresponding <tt>MatchType</tt> or <tt>null</tt> if
     * <tt>encodedValue</tt> is null.
     * @throws IllegalArgumentException If <tt>encodedValue</tt> is non-null
     * but does not correspond to a defined <tt>MatchType</tt>.
     */
    public static MatchType extract(String encodedValue) {
        if (encodedValue == null) {
            return null;
        }
        for (MatchType matchType : MatchType.values()) {
            if (matchType.getEncodedValue().equals(encodedValue)) {
                return matchType;
            }
        }
        throw new IllegalArgumentException("Invalid match type: " + encodedValue);
    }

    /**
     * Get the string representation of this match type as it
     * would appear on the wire.
     * @return The encode representation of this match type.
     */
    public String getEncodedValue() {
        return encodedValue;
    }

    /**
     * Determine if this <tt>MatchType</tt> represents a positive match.
     * @return <tt>true</tt> if this match type represents a positive
     * match, <tt>false</tt> otherwise.
     */
    public boolean isMatched() {
        return matched;
    }

    @Override
    public String stringify() {
        return encodedValue;
    }
}
