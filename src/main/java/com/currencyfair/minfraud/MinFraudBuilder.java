package com.currencyfair.minfraud;

import com.currencyfair.minfraud.util.HttpMethodFactory;
import com.currencyfair.minfraud.util.HttpMethodFactoryImpl;
import com.currencyfair.minfraud.util.SimpleValueFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;

/**
 * Builder class for configuring and creating a {@link MinFraud}
 * implementation.
 * @since 1.0.0
 */
public class MinFraudBuilder {

    private HttpClient httpClient;
    private HttpMethodFactory methodFactory;
    private URI uri;
    private String licenseKey;

    /**
     * Get a new builder instance.
     * @return A new builder instance.
     */
    public static MinFraudBuilder newInstance() {
        return new MinFraudBuilder();
    }

    /**
     * Construct and configure the {@link MinFraud} implementation and return
     * it.
     * @return A newly built and configured MinFraud implementation.
     */
    public MinFraud build() {
        MinFraudImpl minFraud = new MinFraudImpl();
        if (httpClient != null) {
            minFraud.setHttpClient(httpClient);
        } else {
            minFraud.setHttpClient(HttpClients.createDefault());
        }
        if (methodFactory != null) {
            minFraud.setMethodFactory(methodFactory);
        } else {
            minFraud.setMethodFactory(new HttpMethodFactoryImpl());
        }
        if (uri != null) {
            minFraud.setEndpoint(new SimpleValueFactory<URI>(uri));
        } else {
            minFraud.setEndpoint(new SimpleValueFactory<URI>(MinFraud.DEFAULT_URL));
        }
        minFraud.setLicenseKey(new SimpleValueFactory<String>(licenseKey));
        return minFraud;
    }

    /**
     * Set the {@link HttpClient} to use for issuing requests to the
     * remote endpoint.
     * @param httpClient The HttpClient to use for requests.
     * @return This <tt>MinFraudBuilder</tt> instance.
     */
    public MinFraudBuilder with(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    /**
     * Set the URI of the endpoint HTTP requests will be issued to.
     * @param endpoint The URI of the endpoint to issue requests to.
     * @return This <tt>MinFraudBuilder</tt> instance.
     */
    public MinFraudBuilder with(URI endpoint) {
        this.uri = endpoint;
        return this;
    }

    /**
     * Specify the license key to use for any requests that do not explicitly
     * specify one.
     * @param licenseKey The default license key to add to requests.
     * @return This <tt>MinFraudBuilder</tt> instance.
     */
    public MinFraudBuilder licenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
        return this;
    }

    /**
     * Set the {@link HttpMethodFactory} instance to use for building
     * {@link org.apache.http.client.methods.HttpUriRequest} instances.
     * @param methodFactory The <tt>HttpMethodFactory</tt> to use.
     * @return This <tt>MinFraudBuilder</tt> instance.
     */
    public MinFraudBuilder with(HttpMethodFactory methodFactory) {
        this.methodFactory = methodFactory;
        return this;
    }
}
