package com.currencyfair.minfraud.util;

/**
 * A simple factory the emits a value of a given type. This
 * interface is used to avoid injecting static values into an implementation,
 * instead allowing the retrieval of a particular value at runtime.
 */
public interface ValueFactory<T> {
    /**
     * Get the value from this factory.
     * @return The value.
     */
    T get();
}
