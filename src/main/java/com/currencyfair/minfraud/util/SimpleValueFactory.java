package com.currencyfair.minfraud.util;

/**
 * A simple {@link ValueFactory} implementation that wraps and returns
 * a static value.
 * @param <T> The type of the object returned by this factory.
 * @since 1.0.0
 */
public class SimpleValueFactory<T> implements ValueFactory<T> {
    private T object;

    public SimpleValueFactory(T object) {
        this.object = object;
    }

    @Override
    public T get() {
        return object;
    }
}
