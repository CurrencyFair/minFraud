package com.currencyfair.minfraud;

import com.currencyfair.minfraud.model.RiskScoreRequest;
import com.currencyfair.minfraud.model.RiskScoreResponse;
import com.currencyfair.minfraud.util.CloseableHttpResponseAdapter;
import com.currencyfair.minfraud.util.HttpMethodFactory;
import com.currencyfair.minfraud.util.ValueFactory;
import com.currencyfair.minfraud.util.ValueUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Concrete implementation of the {@link MinFraud} interface for communicating
 * with the minFraud API.
 */
public class MinFraudImpl implements MinFraud {
    private static final Logger LOG = LoggerFactory.getLogger(MinFraudImpl.class);

    private ValueFactory<String> licenseKey;
    private ValueFactory<URI> endpoint;
    private HttpClient httpClient;
    private HttpMethodFactory methodFactory;

    @Override
    public RiskScoreResponse calculateRisk(RiskScoreRequest request) throws IOException {
        HttpPost req = methodFactory.createPost(endpoint.get());
        List<NameValuePair> params = createParams(request);
        LOG.trace("Request parameters: {}", params);
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        req.setEntity(entity);
        HttpResponse resp = httpClient.execute(req);
        try (CloseableHttpResponseAdapter adapter = new CloseableHttpResponseAdapter(resp)) {
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                String responseValues = EntityUtils.toString(resp.getEntity(), Consts.ISO_8859_1);
                LOG.trace("minFraud response: {}", responseValues);
                RiskScoreResponse riskScore = RiskScoreResponse.extract(parseValueMap(responseValues));
                riskScore.setRawResponse(responseValues);
                return riskScore;
            } else {
                throw new IOException("Unexpected response code from remote: " + statusCode);
            }
        }
    }

    public void setLicenseKey(ValueFactory<String> licenseKey) {
        this.licenseKey = licenseKey;
    }

    public void setEndpoint(ValueFactory<URI> endpoint) {
        this.endpoint = endpoint;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setMethodFactory(HttpMethodFactory methodFactory) {
        this.methodFactory = methodFactory;
    }

    private List<NameValuePair> createParams(RiskScoreRequest request) {
        boolean addLicense = true;
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : request.toParams().entrySet()) {
            if ("license_key".equals(entry.getKey())) {
                addLicense = false;
            }
            pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        if (addLicense && licenseKey != null) {
            pairs.add(new BasicNameValuePair("license_key", licenseKey.get()));
        }
        return pairs;
    }

    private Map<String, String> parseValueMap(String responseValues) {
        Map<String, String> pairs = new LinkedHashMap<>();
        Scanner scanner = new Scanner(responseValues).useDelimiter("[;=]");
        try {
            while (scanner.hasNext()) {
                pairs.put(scanner.next(), scanner.next());
            }
        } catch (NoSuchElementException x) {
            LOG.warn("Unbalanced response received from remote: {}", responseValues);
        }
        return ValueUtils.removeEmptyValues(pairs);
    }
}
