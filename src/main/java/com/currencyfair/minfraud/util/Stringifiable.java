package com.currencyfair.minfraud.util;

/**
 * Interface for objects that can be represented as a String.
 * Yes, <tt>Object.toString()</tt> does exist, but we need a little more
 * control over string representations of, for example, enumerations we
 * have defined representing values serialized by the minFraud API.
 */
public interface Stringifiable {
    /**
     * Stringify this object.
     * @return The string representation of this object.
     */
    String stringify();
}
