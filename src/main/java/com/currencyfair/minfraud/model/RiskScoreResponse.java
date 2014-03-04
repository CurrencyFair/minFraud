package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.ValueUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Response object containing the results of the risk assessment request.
 * @since 1.0.0
 */
public class RiskScoreResponse implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private BigDecimal riskScore;
    private GeoIpDetails geoIpDetails;
    private ProxyDetails proxyDetails;
    private EmailDetails emailDetails;
    private BankIdentityDetails bankIdentityDetails;
    private AddressPhoneDetails addressPhoneDetails;
    private AccountDetails accountDetails;
    private ResponseCode responseCode;

    /**
     * Construct a new <tt>RiskScoreResponse</tt> and populate its fields with
     * the values extracted from <tt>values</tt>.
     * @param values The values as extracted from the response to the minFraud
     *               API call.
     * @return A newly constructed and initialised <tt>RiskScoreResponse</tt>.
     */
    public static RiskScoreResponse extract(Map<String, String> values) {
        RiskScoreResponse r = new RiskScoreResponse();
        r.riskScore = ValueUtils.extractBigDecimal(values.get("riskScore"));
        r.responseCode = ResponseCode.extract(values.get("err"));
        r.geoIpDetails = GeoIpDetails.extract(values);
        r.proxyDetails = ProxyDetails.extract(values);
        r.emailDetails = EmailDetails.extract(values);
        r.bankIdentityDetails = BankIdentityDetails.extract(values);
        r.addressPhoneDetails = AddressPhoneDetails.extract(values);
        r.accountDetails = AccountDetails.extract(values);
        return r;
    }

    public BigDecimal getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(BigDecimal riskScore) {
        this.riskScore = riskScore;
    }

    public GeoIpDetails getGeoIpDetails() {
        return geoIpDetails;
    }

    public void setGeoIpDetails(GeoIpDetails geoIpDetails) {
        this.geoIpDetails = geoIpDetails;
    }

    public ProxyDetails getProxyDetails() {
        return proxyDetails;
    }

    public void setProxyDetails(ProxyDetails proxyDetails) {
        this.proxyDetails = proxyDetails;
    }

    public EmailDetails getEmailDetails() {
        return emailDetails;
    }

    public void setEmailDetails(EmailDetails emailDetails) {
        this.emailDetails = emailDetails;
    }

    public BankIdentityDetails getBankIdentityDetails() {
        return bankIdentityDetails;
    }

    public void setBankIdentityDetails(BankIdentityDetails bankIdentityDetails) {
        this.bankIdentityDetails = bankIdentityDetails;
    }

    public AddressPhoneDetails getAddressPhoneDetails() {
        return addressPhoneDetails;
    }

    public void setAddressPhoneDetails(AddressPhoneDetails addressPhoneDetails) {
        this.addressPhoneDetails = addressPhoneDetails;
    }

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder b = new HashCodeBuilder(37, 69);
        b.append(riskScore);
        b.append(geoIpDetails);
        b.append(proxyDetails);
        b.append(emailDetails);
        b.append(bankIdentityDetails);
        b.append(addressPhoneDetails);
        b.append(accountDetails);
        b.append(responseCode);
        return b.toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof RiskScoreResponse)) {
            return false;
        }
        RiskScoreResponse rhs = (RiskScoreResponse) o;
        EqualsBuilder b = new EqualsBuilder();
        b.append(riskScore, rhs.riskScore);
        b.append(geoIpDetails, rhs.geoIpDetails);
        b.append(proxyDetails, rhs.proxyDetails);
        b.append(emailDetails, rhs.emailDetails);
        b.append(bankIdentityDetails, rhs.bankIdentityDetails);
        b.append(addressPhoneDetails, rhs.addressPhoneDetails);
        b.append(accountDetails, rhs.accountDetails);
        b.append(responseCode, rhs.responseCode);
        return b.isEquals();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException x) {
            throw new IllegalStateException("Clone not supported? You lie.", x);
        }
    }
}
