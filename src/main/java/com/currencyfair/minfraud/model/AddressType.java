package com.currencyfair.minfraud.model;

/**
 * Types of address.
 * @since 1.0.0
 */
public enum AddressType {
    /**
     * Billing address.
     */
    BILLING(null, "city", "region", "postal", "country"),
    /**
     * Shipping address.
     */
    SHIPPING("shipAddr", "shipCity", "shipRegion", "shipPostal", "shipCountry");

    private String addrParam;
    private String cityParam;
    private String regionParam;
    private String postalParam;
    private String countryParam;

    private AddressType(
            String addrParam,
            String cityParam,
            String regionParam,
            String postalParam,
            String countryParam) {
        this.addrParam = addrParam;
        this.cityParam = cityParam;
        this.regionParam = regionParam;
        this.postalParam = postalParam;
        this.countryParam = countryParam;
    }

    public String getAddrParam() {
        return addrParam;
    }

    public String getCityParam() {
        return cityParam;
    }

    public String getRegionParam() {
        return regionParam;
    }

    public String getPostalParam() {
        return postalParam;
    }

    public String getCountryParam() {
        return countryParam;
    }
}
