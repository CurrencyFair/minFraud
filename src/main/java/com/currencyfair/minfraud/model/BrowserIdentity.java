package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.ValueUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * Value object encapsulating browser identification, used in transaction
 * linking.
 * @since 1.0.0
 */
public class BrowserIdentity implements Serializable, Cloneable, ParamSource {
    private static final long serialVersionUID = 1L;

    private String sessionId;
    private String userAgent;
    private String acceptLanguage;

    @Override
    public void putParams(Map<String, String> params) {
        ValueUtils.add(params, "sessionID", sessionId);
        ValueUtils.add(params, "user_agent", userAgent);
        ValueUtils.add(params, "accept_language", acceptLanguage);
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder b = new HashCodeBuilder(371, 43);
        b.append(sessionId);
        b.append(userAgent);
        b.append(acceptLanguage);
        return b.toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof BrowserIdentity)) {
            return false;
        }
        BrowserIdentity rhs = (BrowserIdentity) o;
        EqualsBuilder b = new EqualsBuilder();
        b.append(sessionId, rhs.sessionId);
        b.append(userAgent, rhs.userAgent);
        b.append(acceptLanguage, rhs.acceptLanguage);
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
