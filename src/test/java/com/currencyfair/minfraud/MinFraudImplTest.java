package com.currencyfair.minfraud;

import com.currencyfair.minfraud.model.RiskScoreRequest;
import com.currencyfair.minfraud.model.RiskScoreResponse;
import com.currencyfair.minfraud.util.HttpMethodFactory;
import com.currencyfair.minfraud.util.SimpleValueFactory;
import com.currencyfair.minfraud.util.ValueFactory;
import junit.framework.Assert;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.easymock.EasyMock;
import org.easymock.IExpectationSetters;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;

public class MinFraudImplTest {
    private static final ValueFactory<URI> ENDPOINT =
            new SimpleValueFactory<>(URI.create("http://localhost/minFraud"));
    private static final String VALID_RESPONSE =
            "riskScore=23.2;countryMatch=Yes;countryCode=IE;highRiskCountry=No;distance=48;freeMail=No";
    private MinFraudImpl minFraudImpl;

    @Before
    public void init() throws Exception {
        minFraudImpl = new MinFraudImpl();
    }

    @Test
    public void testRiskScoreResponseIsExtractedFromValidHttpResponse() throws Exception {
        StringEntity responseEntity = new StringEntity(VALID_RESPONSE, ContentType.TEXT_PLAIN);
        HttpMethodFactory methodFactory = expectCreateHttpPost();
        CloseableHttpResponse httpResponse = EasyMock.createMock(CloseableHttpResponse.class);
        expectHttpResponse(httpResponse, 200, responseEntity);
        expectClose(httpResponse, null);
        HttpClient client = expectHttpInvocation(httpResponse);
        EasyMock.replay(httpResponse);
        minFraudImpl.setHttpClient(client);
        minFraudImpl.setMethodFactory(methodFactory);
        minFraudImpl.setEndpoint(ENDPOINT);
        RiskScoreResponse response = minFraudImpl.calculateRisk(newRiskScoreRequest());
        Assert.assertNotNull(response);
        Assert.assertEquals(new BigDecimal("23.2"), response.getRiskScore());
        Assert.assertTrue(response.getGeoIpDetails().isCountryMatch());
        Assert.assertFalse(response.getGeoIpDetails().isHighRiskCountry());
        Assert.assertEquals("IE", response.getGeoIpDetails().getIpCountryCode());
        Assert.assertEquals(Integer.valueOf(48), response.getGeoIpDetails().getDistance());
        EasyMock.verify(httpResponse);
    }

    @Test(expected = IOException.class)
    public void testExceptionIsThrownWhenResponseCodeIsNot200() throws Exception {
        HttpMethodFactory methodFactory = expectCreateHttpPost();
        CloseableHttpResponse httpResponse = EasyMock.createMock(CloseableHttpResponse.class);
        expectHttpResponse(httpResponse, 403, null);
        expectClose(httpResponse, null);
        HttpClient client = expectHttpInvocation(httpResponse);
        EasyMock.replay(httpResponse);
        try {
            minFraudImpl.setHttpClient(client);
            minFraudImpl.setMethodFactory(methodFactory);
            minFraudImpl.setEndpoint(ENDPOINT);
            minFraudImpl.calculateRisk(newRiskScoreRequest());
        } catch (IOException x) {
            // Make sure close() is still invoked..
            EasyMock.verify(httpResponse);
            throw x;
        }
    }

    @Test
    public void testNonCloseableHttpResponseIsSupported() throws Exception {
        StringEntity responseEntity = new StringEntity(VALID_RESPONSE, ContentType.TEXT_PLAIN);
        HttpMethodFactory methodFactory = expectCreateHttpPost();
        HttpResponse httpResponse = EasyMock.createMock(HttpResponse.class);
        expectHttpResponse(httpResponse, 200, responseEntity);
        HttpClient client = expectHttpInvocation(httpResponse);
        EasyMock.replay(httpResponse);
        minFraudImpl.setHttpClient(client);
        minFraudImpl.setMethodFactory(methodFactory);
        minFraudImpl.setEndpoint(ENDPOINT);
        RiskScoreResponse response = minFraudImpl.calculateRisk(newRiskScoreRequest());
        Assert.assertNotNull(response);
        Assert.assertEquals(new BigDecimal("23.2"), response.getRiskScore());
        Assert.assertTrue(response.getGeoIpDetails().isCountryMatch());
        Assert.assertFalse(response.getGeoIpDetails().isHighRiskCountry());
        Assert.assertEquals("IE", response.getGeoIpDetails().getIpCountryCode());
        Assert.assertEquals(Integer.valueOf(48), response.getGeoIpDetails().getDistance());
        EasyMock.verify(httpResponse);
    }

    @Test(expected = IllegalStateException.class)
    public void testExceptionOnCloseDoesNotHideOriginalException() throws Exception {
        HttpMethodFactory methodFactory = expectCreateHttpPost();
        CloseableHttpResponse httpResponse = EasyMock.createMock(CloseableHttpResponse.class);
        EasyMock.expect(httpResponse.getStatusLine()).andThrow(new IllegalStateException("Expect this!"));
        expectClose(httpResponse, new IOException("Suppress this!"));
        HttpClient client = EasyMock.createMock(HttpClient.class);
        EasyMock.expect(client.execute(EasyMock.isA(HttpUriRequest.class))).andReturn(httpResponse);
        EasyMock.replay(client, httpResponse);
        minFraudImpl.setHttpClient(client);
        minFraudImpl.setMethodFactory(methodFactory);
        minFraudImpl.setEndpoint(ENDPOINT);
        minFraudImpl.calculateRisk(newRiskScoreRequest());
    }

    @Test(expected = IOException.class)
    public void testExceptionOnClosePropagatesWhenThereIsNoOtherException() throws Exception {
        StringEntity responseEntity = new StringEntity(VALID_RESPONSE, ContentType.TEXT_PLAIN);
        HttpMethodFactory methodFactory = expectCreateHttpPost();
        CloseableHttpResponse httpResponse = EasyMock.createMock(CloseableHttpResponse.class);
        expectHttpResponse(httpResponse, 200, responseEntity);
        expectClose(httpResponse, new IOException("Expect this!"));
        HttpClient client = expectHttpInvocation(httpResponse);
        EasyMock.replay(httpResponse);
        minFraudImpl.setHttpClient(client);
        minFraudImpl.setMethodFactory(methodFactory);
        minFraudImpl.setEndpoint(ENDPOINT);
        minFraudImpl.calculateRisk(newRiskScoreRequest());
    }

    private RiskScoreRequest newRiskScoreRequest() {
        RiskScoreRequest req = RiskScoreRequest.newInstance();
        req.setLicenseKey("LicenseKey");
        req.setIpAddress("127.0.0.1");
        req.getBillingAddress().setCountry("Ireland");
        req.getBillingAddress().setCity("Dublin");
        req.getBillingAddress().setRegion("Dublin");
        req.getBillingAddress().setPostalCode("12");
        return req;
    }

    private HttpMethodFactory expectCreateHttpPost() throws Exception {
        HttpPost post = new HttpPost();
        HttpMethodFactory methodFactory = EasyMock.createMock(HttpMethodFactory.class);
        EasyMock.expect(methodFactory.createPost(EasyMock.isA(URI.class))).andReturn(post);
        EasyMock.replay(methodFactory);
        return methodFactory;
    }

    private HttpClient expectHttpInvocation(HttpResponse httpResponse) throws Exception {
        HttpClient client = EasyMock.createMock(HttpClient.class);
        EasyMock.expect(client.execute(EasyMock.isA(HttpPost.class))).andReturn(httpResponse);
        EasyMock.replay(client);
        return client;
    }

    private <T extends HttpResponse> T expectHttpResponse(T httpResponse, int responseCode, HttpEntity responseEntity) throws Exception {
        StatusLine statusLine = EasyMock.createMock(StatusLine.class);
        EasyMock.expect(statusLine.getStatusCode()).andReturn(responseCode);
        EasyMock.expect(httpResponse.getStatusLine()).andReturn(statusLine);
        if (responseEntity != null) {
            EasyMock.expect(httpResponse.getEntity()).andReturn(responseEntity);
        }
        EasyMock.replay(statusLine);
        return httpResponse;
    }

    private CloseableHttpResponse expectClose(CloseableHttpResponse httpResponse, IOException throwOnClose) throws Exception {
        httpResponse.close();
        IExpectationSetters<?> expection = EasyMock.expectLastCall();
        if (throwOnClose != null) {
            expection.andThrow(throwOnClose);
        }
        return httpResponse;
    }
}
