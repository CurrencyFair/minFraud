package com.currencyfair.minfraud.util;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

/**
 * Utility methods for safely extracting response values which may be null.
 * @since 1.0.0
 */
public class ValueUtils {
    /**
     * Private constructor, this is just a static util object.
     */
    private ValueUtils() {
    }

    /**
     * Extract a {@link BigDecimal} instance. If <tt>value</tt> is
     * <tt>null</tt>, then <tt>null</tt> will be returned.
     * @param value The value to parse as a BigDecimal.
     * @return The value as a BigDecimal, or <tt>null</tt> if <tt>value</tt>
     * is <tt>null</tt>.
     * @throws NumberFormatException If <tt>value</tt> is not a valid decimal
     * number.
     * @see BigDecimal#BigDecimal(String)
     */
    public static BigDecimal extractBigDecimal(String value) {
        if (value != null) {
            return new BigDecimal(value);
        } else {
            return null;
        }
    }

    /**
     * Extract a boolean value. Expects a string value of either
     * "Yes" or "No".
     * @param value The string value to parse as a boolean.
     * @return The parsed boolean value.
     * @throws IllegalArgumentException If <tt>value</tt> is <tt>null</tt> or
     * is neither "Yes" or "No".
     */
    public static boolean extractBoolean(String value) {
        Boolean bool = extractBooleanOrNull(value);
        if (bool != null) {
            return bool.booleanValue();
        } else {
            throw new IllegalArgumentException("Cannot parse boolean from null");
        }
    }

    /**
     * Extract a boolean value. Expects a string value of either
     * "Yes" or "No". If <tt>value</tt> is <tt>null</tt> then <tt>null</tt>
     * will be returned.
     * @param value The string value to parse as a boolean.
     * @return The parsed boolean value, or <tt>null</tt>.
     * @throws IllegalArgumentException If <tt>value</tt> is not either
     * "Yes" or "No".
     */
    public static Boolean extractBooleanOrNull(String value) {
        if (value == null) {
            return null;
        }
        if ("Yes".equals(value)) {
            return Boolean.TRUE;
        } else if ("No".equals(value)) {
            return Boolean.FALSE;
        } else {
            throw new IllegalArgumentException("Invalid boolean value: " + value);
        }
    }

    /**
     * Extract an integer value.
     * @param value The value to parse as an integer.
     * @return Value parsed as an integer, or <tt>null</tt> if <tt>value</tt>
     * is <tt>null</tt>.
     */
    public static Integer extractInt(String value) {
        if (value == null) {
            return null;
        }
        return Integer.parseInt(value);
    }

    /**
     * Safely add a named parameter to a map.
     * @param params The map to add parameters to.
     * @param name The name of the parameter to add. If <tt>null</tt>, nothing
     *             will be added.
     * @param value The value to add. If <tt>null</tt>, no parameter will be
     *              placed in the map. Otherwise, the string representation
     *              of the value will be added.
     * @return The <tt>params</tt> instance will be returned, allowing call
     * chaining.
     */
    public static Map<String, String> add(Map<String, String> params, String name, Object value) {
        if (name != null && value != null) {
            String strValue;
            if (value instanceof Stringifiable) {
                strValue = ((Stringifiable) value).stringify();
            } else {
                strValue = value.toString();
            }
            params.put(name, strValue);
        }
        return params;
    }

    /**
     * Strip all entries from <tt>params</tt> whose value is either
     * <tt>null</tt> or the empty string.
     * @param params The map to strip empty values from.
     * @return Returns the <tt>params</tt> map itself.
     */
    public static Map<String, String> removeEmptyValues(Map<String, String> params) {
        for (Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry<String, String> entry = iter.next();
            if (null == entry.getValue() || "".equals(entry.getValue())) {
                iter.remove();
            }
        }
        return params;
    }
}
