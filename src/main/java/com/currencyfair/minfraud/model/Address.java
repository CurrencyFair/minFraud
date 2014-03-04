package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.ValueUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * Value object encapsulating MinFraud's definition of an address.
 * @since 1.0.0
 */
public class Address implements Serializable, Cloneable, ParamSource {
    private static final long serialVersionUID = 1L;

    private AddressType addressType;
    private String streetAddress;
    private String city;
    private String region;
    private String postalCode;
    private String country;

    public Address(AddressType addressType) {
        this.addressType = addressType;
    }

    @Override
    public void putParams(Map<String, String> params) {
        ValueUtils.add(params, addressType.getAddrParam(), streetAddress);
        ValueUtils.add(params, addressType.getCityParam(), city);
        ValueUtils.add(params, addressType.getRegionParam(), region);
        ValueUtils.add(params, addressType.getPostalParam(), postalCode);
        ValueUtils.add(params, addressType.getCountryParam(), country);
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException x) {
            throw new IllegalStateException("Clone not supported? I *do* support it.", x);
        }
    }

    @Override
    public int hashCode() {
        HashCodeBuilder b = new HashCodeBuilder(937454753, 372877);
        b.append(streetAddress);
        b.append(city);
        b.append(region);
        b.append(postalCode);
        b.append(country);
        return b.toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        Address a = (Address) o;
        EqualsBuilder b = new EqualsBuilder();
        b.append(streetAddress, a.streetAddress);
        b.append(city, a.city);
        b.append(region, a.region);
        b.append(postalCode, a.postalCode);
        b.append(country, a.country);
        return b.isEquals();
    }
}
