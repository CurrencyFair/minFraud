package com.currencyfair.minfraud.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

/**
 */
public class RiskScoreRequestTest {
    @Test
    public void testToParamsOnCompleteEmptyRiskScoreRequest() throws Exception {
        RiskScoreRequest request = RiskScoreRequest.newInstance();
        Map<String, String> params = request.toParams();
        Assert.assertTrue(params.isEmpty());
    }

    @Test
    public void testToParamsOnFullyPopulatedRiskScoreRequest() throws Exception {
        RiskScoreRequest request = RiskScoreRequest.newInstance();
        request.setIpAddress("127.0.0.1");
        request.setLicenseKey("LicenseKey");
        request.setAvsResult(Character.valueOf('P'));
        request.setCvvResult(Character.valueOf('E'));
        request.setServiceLevel(ServiceLevel.PREMIUM);
        request.setForwardedIp("127.1.1.1");
        request.getBillingAddress().setCity("Dublin");
        request.getBillingAddress().setRegion("Dublin");
        request.getBillingAddress().setCountry("Ireland");
        request.getBillingAddress().setPostalCode("Dublin 22");
        request.getShippingAddress().setStreetAddress("1 Test Street");
        request.getShippingAddress().setCity("Rathmines");
        request.getShippingAddress().setRegion("Dublin");
        request.getShippingAddress().setPostalCode("Dublin 12");
        request.getShippingAddress().setCountry("IE");
        request.getUserData().setDomain("example.com");
        request.getUserData().setCustPhone("+35311234567");
        request.getUserData().setEmail("test@example.com");
        request.getUserData().setUsername("testuser");
        request.getUserData().setPassword("testpassword");
        request.getBankIdentity().setNumber("413222");
        request.getBankIdentity().setName("Allied Irish Bank");
        request.getBankIdentity().setPhone("+35317654321");
        request.getBrowserIdentity().setSessionId("abcdefg");
        request.getBrowserIdentity().setUserAgent("Mozilla/Firefox");
        request.getBrowserIdentity().setAcceptLanguage("English");
        request.getTransactionInfo().setTxnId("83745");
        request.getTransactionInfo().setOrderAmount(new BigDecimal("234.23"));
        request.getTransactionInfo().setOrderCurrency(Currency.getInstance("GBP"));
        request.getTransactionInfo().setShopId("MyShop01");
        request.getTransactionInfo().setTxnType(TransactionType.GOOGLE);
        Map<String, String> params = request.toParams();
        Assert.assertEquals("127.0.0.1", params.get("i"));
        Assert.assertEquals("LicenseKey", params.get("license_key"));
        Assert.assertEquals("P", params.get("avs_result"));
        Assert.assertEquals("E", params.get("cvv_result"));
        Assert.assertEquals("premium", params.get("requested_type"));
        Assert.assertEquals("127.1.1.1", params.get("forwardedIP"));
        Assert.assertEquals("Dublin", params.get("city"));
        Assert.assertEquals("Dublin", params.get("region"));
        Assert.assertEquals("Dublin 22", params.get("postal"));
        Assert.assertEquals("Ireland", params.get("country"));
        Assert.assertEquals("1 Test Street", params.get("shipAddr"));
        Assert.assertEquals("Rathmines", params.get("shipCity"));
        Assert.assertEquals("Dublin", params.get("shipRegion"));
        Assert.assertEquals("Dublin 12", params.get("shipPostal"));
        Assert.assertEquals("IE", params.get("shipCountry"));
        Assert.assertEquals("example.com", params.get("domain"));
        Assert.assertEquals("+35311234567", params.get("custPhone"));
        Assert.assertEquals("55502f40dc8b7c769880b10874abc9d0", params.get("emailMD5"));
        Assert.assertEquals("5d9c68c6c50ed3d02a2fcf54f63993b6", params.get("usernameMD5"));
        Assert.assertEquals("e16b2ab8d12314bf4efbd6203906ea6c", params.get("passwordMD5"));
        Assert.assertEquals("413222", params.get("bin"));
        Assert.assertEquals("Allied Irish Bank", params.get("binName"));
        Assert.assertEquals("+35317654321", params.get("binPhone"));
        Assert.assertEquals("abcdefg", params.get("sessionID"));
        Assert.assertEquals("Mozilla/Firefox", params.get("user_agent"));
        Assert.assertEquals("English", params.get("accept_language"));
        Assert.assertEquals("83745", params.get("txnID"));
        Assert.assertEquals("234.23", params.get("order_amount"));
        Assert.assertEquals("GBP", params.get("order_currency"));
        Assert.assertEquals("MyShop01", params.get("shopID"));
        Assert.assertEquals("google", params.get("txn_type"));
    }
}
