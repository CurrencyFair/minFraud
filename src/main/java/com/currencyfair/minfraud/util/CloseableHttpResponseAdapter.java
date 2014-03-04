package com.currencyfair.minfraud.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

/**
 * Adapter class to allow a CloseableHttpResponse to be used in a
 * try-with-resources block.
 * @since 1.0.0
 */
public class CloseableHttpResponseAdapter implements AutoCloseable {
    private HttpResponse httpResponse;

    public CloseableHttpResponseAdapter(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    @Override
    public void close() throws IOException {
        if (httpResponse instanceof CloseableHttpResponse) {
            ((CloseableHttpResponse) httpResponse).close();
        }
    }
}
