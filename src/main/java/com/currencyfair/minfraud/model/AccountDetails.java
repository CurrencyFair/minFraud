package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.ValueUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * Value object encapsulating the MaxMind account information details.
 * @since 1.0.0
 */
public class AccountDetails implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private Integer queriesRemaining;
    private String maxmindId;
    private String minFraudVersion;
    private ServiceLevel serviceLevel;

    /**
     * Construct a new <tt>AccountDetails</tt> and populate its fields with
     * the values extracted from <tt>values</tt>.
     * @param values The values as extracted from the response to the minFraud
     *               API call.
     * @return A newly constructed and initialised <tt>AccountDetails</tt>.
     */
    public static AccountDetails extract(Map<String, String> values) {
        AccountDetails a = new AccountDetails();
        a.queriesRemaining = ValueUtils.extractInt(values.get("queriesRemaining"));
        a.maxmindId = values.get("maxmindID");
        a.minFraudVersion = values.get("minfraud_version");
        a.serviceLevel = ServiceLevel.extract(values.get("service_level"));
        return a;
    }

    public Integer getQueriesRemaining() {
        return queriesRemaining;
    }

    public void setQueriesRemaining(Integer queriesRemaining) {
        this.queriesRemaining = queriesRemaining;
    }

    public String getMaxmindId() {
        return maxmindId;
    }

    public void setMaxmindId(String maxmindId) {
        this.maxmindId = maxmindId;
    }

    public String getMinFraudVersion() {
        return minFraudVersion;
    }

    public void setMinFraudVersion(String minFraudVersion) {
        this.minFraudVersion = minFraudVersion;
    }

    public ServiceLevel getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(ServiceLevel serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder b = new HashCodeBuilder(13, 29);
        b.append(queriesRemaining);
        b.append(maxmindId);
        b.append(minFraudVersion);
        b.append(serviceLevel);
        return b.toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof AccountDetails)) {
            return false;
        }
        AccountDetails rhs = (AccountDetails) o;
        EqualsBuilder b = new EqualsBuilder();
        b.append(queriesRemaining, rhs.queriesRemaining);
        b.append(maxmindId, rhs.maxmindId);
        b.append(minFraudVersion, rhs.minFraudVersion);
        b.append(serviceLevel, rhs.serviceLevel);
        return b.isEquals();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException x) {
            throw new IllegalStateException("Clone not supported? Dems fightin words.", x);
        }
    }
}
