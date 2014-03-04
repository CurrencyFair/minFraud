package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.ValueUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * Value object encapsulating Bank Identity Number-related fields.
 * @since 1.0.0
 */
public class BankIdentity implements Serializable, Cloneable, ParamSource {
    private static final long serialVersionUID = 1L;

    private String number;
    private String name;
    private String phone;

    @Override
    public void putParams(Map<String, String> params) {
        ValueUtils.add(params, "bin", number);
        ValueUtils.add(params, "binName", name);
        ValueUtils.add(params, "binPhone", phone);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder b = new HashCodeBuilder(93, 87);
        b.append(number);
        b.append(name);
        b.append(phone);
        return b.toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof BankIdentity)) {
            return false;
        }
        BankIdentity id = (BankIdentity) o;
        EqualsBuilder b = new EqualsBuilder();
        b.append(number, id.number);
        b.append(name, id.name);
        b.append(phone, id.phone);
        return b.isEquals();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException x) {
            throw new IllegalStateException("Clone not supported? False!", x);
        }
    }
}
