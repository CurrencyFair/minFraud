package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.ValueUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * Value object encapsulating the results of bank identification checks.
 * @since 1.0.0
 */
public class BankIdentityDetails implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private MatchType binMatch;
    private String country;
    private MatchType nameMatch;
    private String name;
    private MatchType phoneMatch;
    private String phone;
    private Boolean prepaid;

    /**
     * Construct a new <tt>BankIdentityDetails</tt> and populate its fields with
     * the values extracted from <tt>values</tt>.
     * @param values The values as extracted from the response to the minFraud
     *               API call.
     * @return A newly constructed and initialised <tt>BankIdentityDetails</tt>.
     */
    public static BankIdentityDetails extract(Map<String, String> values) {
        BankIdentityDetails b = new BankIdentityDetails();
        b.binMatch = MatchType.extract(values.get("binMatch"));
        b.country = values.get("binCountry");
        b.nameMatch = MatchType.extract(values.get("binNameMatch"));
        b.name = values.get("binName");
        b.phoneMatch = MatchType.extract(values.get("binPhoneMatch"));
        b.phone = values.get("binPhone");
        b.prepaid = ValueUtils.extractBooleanOrNull(values.get("prepaid"));
        return b;
    }

    public MatchType getBinMatch() {
        return binMatch;
    }

    public void setBinMatch(MatchType binMatch) {
        this.binMatch = binMatch;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public MatchType getNameMatch() {
        return nameMatch;
    }

    public void setNameMatch(MatchType nameMatch) {
        this.nameMatch = nameMatch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MatchType getPhoneMatch() {
        return phoneMatch;
    }

    public void setPhoneMatch(MatchType phoneMatch) {
        this.phoneMatch = phoneMatch;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(Boolean prepaid) {
        this.prepaid = prepaid;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder b = new HashCodeBuilder(83, 51);
        b.append(binMatch);
        b.append(country);
        b.append(nameMatch);
        b.append(name);
        b.append(phoneMatch);
        b.append(phone);
        b.append(prepaid);
        return b.toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof BankIdentityDetails)) {
            return false;
        }
        BankIdentityDetails rhs = (BankIdentityDetails) o;
        EqualsBuilder b = new EqualsBuilder();
        b.append(binMatch, rhs.binMatch);
        b.append(country, rhs.country);
        b.append(nameMatch, rhs.nameMatch);
        b.append(name, rhs.name);
        b.append(phoneMatch, rhs.phoneMatch);
        b.append(phone, rhs.phone);
        b.append(prepaid, rhs.prepaid);
        return b.isEquals();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException x) {
            throw new IllegalStateException("Clone not supported? <blub>", x);
        }
    }
}
