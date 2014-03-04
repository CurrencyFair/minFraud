package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.ValueUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Request object encapsulating the fields required to obtain a risk score
 * assessement from MinFraud. See <a href="http://dev.maxmind.com/minfraud/">
 *     http://dev.maxmind.com/minfraud/</a>.
 *
 * @since 1.0.0
 */
public class RiskScoreRequest implements Serializable, Cloneable, ParamSource {
    private static final long serialVersionUID = 1L;

    private String ipAddress;
    private String licenseKey;
    private Address billingAddress;
    private Address shippingAddress;
    private UserData userData;
    private BankIdentity bankIdentity;
    private BrowserIdentity browserIdentity;
    private TransactionInfo transactionInfo;
    private Character avsResult;
    private Character cvvResult;
    private ServiceLevel serviceLevel;
    private String forwardedIp;

    /**
     * Utility method for creating new <tt>RiskScoreRequest</tt> instances.
     * Executing this method will also initialise all child objects. This
     * is more convenient for callers than having to constantly check if
     * child objects are <tt>null</tt> or not.
     * @return A newly initialised <tt>RiskScoreRequest</tt>.
     */
    public static RiskScoreRequest newInstance() {
        RiskScoreRequest r = new RiskScoreRequest();
        r.billingAddress = new Address(AddressType.BILLING);
        r.shippingAddress = new Address(AddressType.SHIPPING);
        r.userData = new UserData();
        r.bankIdentity = new BankIdentity();
        r.browserIdentity = new BrowserIdentity();
        r.transactionInfo = new TransactionInfo();
        return r;
    }

    public Map<String, String> toParams() {
        Map<String, String> params = new LinkedHashMap<>();
        putParams(params);
        billingAddress.putParams(params);
        shippingAddress.putParams(params);
        userData.putParams(params);
        bankIdentity.putParams(params);
        browserIdentity.putParams(params);
        transactionInfo.putParams(params);
        return params;
    }

    @Override
    public void putParams(Map<String, String> params) {
        ValueUtils.add(params, "i", ipAddress);
        ValueUtils.add(params, "license_key", licenseKey);
        ValueUtils.add(params, "avs_result", avsResult);
        ValueUtils.add(params, "cvv_result", cvvResult);
        ValueUtils.add(params, "requested_type", serviceLevel);
        ValueUtils.add(params, "forwardedIP", forwardedIp);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public BankIdentity getBankIdentity() {
        return bankIdentity;
    }

    public void setBankIdentity(BankIdentity bankIdentity) {
        this.bankIdentity = bankIdentity;
    }

    public BrowserIdentity getBrowserIdentity() {
        return browserIdentity;
    }

    public void setBrowserIdentity(BrowserIdentity browserIdentity) {
        this.browserIdentity = browserIdentity;
    }

    public TransactionInfo getTransactionInfo() {
        return transactionInfo;
    }

    public void setTransactionInfo(TransactionInfo transactionInfo) {
        this.transactionInfo = transactionInfo;
    }

    public Character getAvsResult() {
        return avsResult;
    }

    public void setAvsResult(Character avsResult) {
        this.avsResult = avsResult;
    }

    public Character getCvvResult() {
        return cvvResult;
    }

    public void setCvvResult(Character cvvResult) {
        this.cvvResult = cvvResult;
    }

    public ServiceLevel getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(ServiceLevel serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public String getForwardedIp() {
        return forwardedIp;
    }

    public void setForwardedIp(String forwardedIp) {
        this.forwardedIp = forwardedIp;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder b = new HashCodeBuilder(45, 7);
        b.append(ipAddress);
        b.append(licenseKey);
        b.append(billingAddress);
        b.append(shippingAddress);
        b.append(userData);
        b.append(bankIdentity);
        b.append(browserIdentity);
        b.append(transactionInfo);
        b.append(avsResult);
        b.append(cvvResult);
        b.append(serviceLevel);
        b.append(forwardedIp);
        return b.toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof RiskScoreRequest)) {
            return false;
        }
        RiskScoreRequest rhs = (RiskScoreRequest) o;
        EqualsBuilder b = new EqualsBuilder();
        b.append(ipAddress, rhs.ipAddress);
        b.append(licenseKey, rhs.licenseKey);
        b.append(billingAddress, rhs.billingAddress);
        b.append(shippingAddress, rhs.shippingAddress);
        b.append(userData, rhs.userData);
        b.append(bankIdentity, rhs.bankIdentity);
        b.append(browserIdentity, rhs.browserIdentity);
        b.append(transactionInfo, rhs.transactionInfo);
        b.append(avsResult, rhs.avsResult);
        b.append(cvvResult, rhs.cvvResult);
        b.append(serviceLevel, rhs.serviceLevel);
        b.append(forwardedIp, rhs.forwardedIp);
        return b.isEquals();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException x) {
            throw new IllegalStateException("Clone not supported? Pfft.", x);
        }
    }
}
