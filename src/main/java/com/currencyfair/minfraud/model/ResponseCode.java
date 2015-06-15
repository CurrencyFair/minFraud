package com.currencyfair.minfraud.model;

/**
 * Enumeration of error and warning response codes.
 * @since 1.0.0
 */
public enum ResponseCode {
    INVALID_LICENSE_KEY,
    IP_REQUIRED,
    IP_NOT_FOUND,
    LICENSE_REQUIRED,
    COUNTRY_REQUIRED,
    MAX_REQUESTS_REACHED,
    COUNTRY_NOT_FOUND,
    CITY_NOT_FOUND,
    CITY_REQUIRED,
    POSTAL_CODE_REQUIRED,
    POSTAL_CODE_NOT_FOUND;

    /**
     * Extract the enumeration value corresponding to the given
     * <tt>code</tt>.
     * @param code The encoded representation of a <tt>ResponseCode</tt>.
     * @return The corresponding <tt>ResponseCode</tt> instance, or
     * <tt>null</tt> if <tt>code</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException If <tt>code</tt> is non-null but
     * does not correspond to a defined enumeration value.
     */
    public static ResponseCode extract(String code) {
        if (code != null) {
            return ResponseCode.valueOf(code);
        } else {
            return null;
        }
    }
}
