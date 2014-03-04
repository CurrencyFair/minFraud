package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.ValueUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * Value object encapsulating the results of address and phone number checks.
 * @since 1.0.0
 */
public class AddressPhoneDetails implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private MatchType phoneInBillingLocation;
    private MatchType shipForward;
    private Boolean billingCityPostalMatch;
    private Boolean shipCityPostalMatch;

    /**
     * Construct a new <tt>AddressPhoneDetails</tt> and populate its fields
     * with the values extracted from <tt>values</tt>.
     * @param values The values as extracted from the response to the minFraud
     *               API call.
     * @return A newly constructed and initialised
     * <tt>AddressPhoneDetails</tt>.
     */
    public static AddressPhoneDetails extract(Map<String, String> values) {
        AddressPhoneDetails a = new AddressPhoneDetails();
        a.phoneInBillingLocation = MatchType.extract(values.get("custPhoneInBillingLoc"));
        a.shipForward = MatchType.extract(values.get("shipForward"));
        a.billingCityPostalMatch = ValueUtils.extractBooleanOrNull(values.get("cityPostalMatch"));
        a.shipCityPostalMatch = ValueUtils.extractBooleanOrNull(values.get("shipCityPostalMatch"));
        return a;
    }

    public MatchType getPhoneInBillingLocation() {
        return phoneInBillingLocation;
    }

    public void setPhoneInBillingLocation(MatchType phoneInBillingLocation) {
        this.phoneInBillingLocation = phoneInBillingLocation;
    }

    public MatchType getShipForward() {
        return shipForward;
    }

    public void setShipForward(MatchType shipForward) {
        this.shipForward = shipForward;
    }

    public Boolean getBillingCityPostalMatch() {
        return billingCityPostalMatch;
    }

    public void setBillingCityPostalMatch(Boolean billingCityPostalMatch) {
        this.billingCityPostalMatch = billingCityPostalMatch;
    }

    public Boolean getShipCityPostalMatch() {
        return shipCityPostalMatch;
    }

    public void setShipCityPostalMatch(Boolean shipCityPostalMatch) {
        this.shipCityPostalMatch = shipCityPostalMatch;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder b = new HashCodeBuilder(231, 19);
        b.append(phoneInBillingLocation);
        b.append(shipForward);
        b.append(billingCityPostalMatch);
        b.append(shipCityPostalMatch);
        return b.toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof AddressPhoneDetails)) {
            return false;
        }
        AddressPhoneDetails rhs = (AddressPhoneDetails) o;
        EqualsBuilder b = new EqualsBuilder();
        b.append(phoneInBillingLocation, rhs.phoneInBillingLocation);
        b.append(shipForward, rhs.shipForward);
        b.append(billingCityPostalMatch, rhs.billingCityPostalMatch);
        b.append(shipCityPostalMatch, rhs.shipCityPostalMatch);
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
