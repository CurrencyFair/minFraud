package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.ValueUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * Value object representing user data. This object provides accessor methods
 * to get the hashed versions of the values in the fields as required by the
 * MinFraud API.
 * @since 1.0.0
 */
public class UserData implements Serializable, Cloneable, ParamSource {
    private static final long serialVersionUID = 1L;

    private String domain;
    private String custPhone;
    private String email;
    private String username;
    private String password;

    @Override
    public void putParams(Map<String, String> params) {
        ValueUtils.add(params, "domain", domain);
        ValueUtils.add(params, "custPhone", custPhone);
        ValueUtils.add(params, "emailMD5", getEmailHash());
        ValueUtils.add(params, "usernameMD5", getUsernameHash());
        ValueUtils.add(params, "passwordMD5", getPasswordHash());
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getEmailHash() {
        return hashIt(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsernameHash() {
        return hashIt(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return hashIt(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder b = new HashCodeBuilder(23, 97);
        b.append(domain);
        b.append(custPhone);
        b.append(email);
        b.append(username);
        b.append(password);
        return b.toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof UserData)) {
            return false;
        }
        UserData u = (UserData) o;
        EqualsBuilder b = new EqualsBuilder();
        b.append(domain, u.domain);
        b.append(custPhone, u.custPhone);
        b.append(email, u.email);
        b.append(username, u.username);
        b.append(password, u.password);
        return b.isEquals();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException x) {
            throw new IllegalStateException("Clone not supported? I think not, sir!", x);
        }
    }

    private String hashIt(String s) {
        if (s != null) {
            return DigestUtils.md5Hex(s.toLowerCase());
        } else {
            return null;
        }
    }
}
