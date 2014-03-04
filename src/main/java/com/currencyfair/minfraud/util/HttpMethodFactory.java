package com.currencyfair.minfraud.util;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.net.URI;

/**
 * Interface for factory objects that create HTTP methods such as
 * {@link HttpGet} and {@link HttpPost}.
 * @since 1.0.0
 */
public interface HttpMethodFactory {
    /**
     * Create a new {@link HttpGet} instance.
     * @param uri The URI of the request.
     * @return A newly created <tt>HttpGet</tt> instance.
     */
    HttpGet createGet(URI uri);

    /**
     * Create a new {@link HttpPost} instance.
     * @param uri The URI of the request.
     * @return A newly created <tt>HttpPost</tt> instance.
     */
    HttpPost createPost(URI uri);
}
