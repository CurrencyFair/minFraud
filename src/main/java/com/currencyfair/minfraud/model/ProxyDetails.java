package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.ValueUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Value object encapsulating the proxy detection results.
 * @since 1.0.0
 */
public class ProxyDetails implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private Boolean anonymousProxy;
    private BigDecimal proxyScore;
    private Boolean transparentProxy;
    private Boolean corporateProxy;

    /**
     * Construct a new <tt>ProxyDetails</tt> and populate its fields with
     * the values extracted from <tt>values</tt>.
     * @param values The values as extracted from the response to the minFraud
     *               API call.
     * @return A newly constructed and initialised <tt>ProxyDetails</tt>.
     */
    public static ProxyDetails extract(Map<String, String> values) {
        ProxyDetails p = new ProxyDetails();
        p.anonymousProxy = ValueUtils.extractBooleanOrNull(values.get("anonymousProxy"));
        p.proxyScore = ValueUtils.extractBigDecimal(values.get("proxyScore"));
        p.transparentProxy = ValueUtils.extractBooleanOrNull(values.get("isTransProxy"));
        p.corporateProxy = ValueUtils.extractBooleanOrNull(values.get("ip_corporateProxy"));
        return p;
    }

    public Boolean isAnonymousProxy() {
        return anonymousProxy;
    }

    public void setAnonymousProxy(Boolean anonymousProxy) {
        this.anonymousProxy = anonymousProxy;
    }

    public BigDecimal getProxyScore() {
        return proxyScore;
    }

    public void setProxyScore(BigDecimal proxyScore) {
        this.proxyScore = proxyScore;
    }

    public Boolean getTransparentProxy() {
        return transparentProxy;
    }

    public void setTransparentProxy(Boolean transparentProxy) {
        this.transparentProxy = transparentProxy;
    }

    public Boolean isCorporateProxy() {
        return corporateProxy;
    }

    public void setCorporateProxy(Boolean corporateProxy) {
        this.corporateProxy = corporateProxy;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder b = new HashCodeBuilder(11, 67);
        b.append(anonymousProxy);
        b.append(proxyScore);
        b.append(transparentProxy);
        b.append(corporateProxy);
        return b.toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof ProxyDetails)) {
            return false;
        }
        ProxyDetails rhs = (ProxyDetails) o;
        EqualsBuilder b = new EqualsBuilder();
        b.append(anonymousProxy, rhs.anonymousProxy);
        b.append(proxyScore, rhs.proxyScore);
        b.append(transparentProxy, rhs.transparentProxy);
        b.append(corporateProxy, rhs.corporateProxy);
        return b.isEquals();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException x) {
            throw new IllegalStateException("Clone not supported? :-(", x);
        }
    }
}
