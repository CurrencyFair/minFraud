package com.currencyfair.minfraud.util;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.net.URI;

/**
 * Implementation of the {@link HttpMethodFactory} interface for creating
 * HTTP request instances.
 * @since 1.0.0
 */
public class HttpMethodFactoryImpl implements HttpMethodFactory {
    @Override
    public HttpGet createGet(URI uri) {
        return new HttpGet(uri);
    }

    @Override
    public HttpPost createPost(URI uri) {
        return new HttpPost(uri);
    }
}
