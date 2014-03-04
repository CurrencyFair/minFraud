package com.currencyfair.minfraud.model;

import com.currencyfair.minfraud.util.ValueUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TimeZone;

/**
 * Value object encapsulating the result of the Geographic IP
 * location checks.
 * @since 1.0.0
 */
public class GeoIpDetails implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private boolean countryMatch;
    private boolean highRiskCountry;
    private Integer distance;
    private Integer ipAccuracyRadius;
    private String ipCity;
    private String ipRegionCode;
    private String ipRegionName;
    private String ipPostalCode;
    private Integer ipMetroCode;
    private String ipAreaCode;
    private String ipCountryCode;
    private String ipCountryName;
    private String ipContinentCode;
    private BigDecimal ipLatitude;
    private BigDecimal ipLongitude;
    private TimeZone ipTimeZone;
    private String ipAsNum;
    private UserType ipUserType;
    private NetSpeed ipNetSpeed;
    private String ipDomain;
    private String ipIsp;
    private String ipOrg;
    private BigDecimal ipCityConfidence;
    private BigDecimal ipRegionConfidence;
    private BigDecimal ipPostalCodeConfidence;
    private BigDecimal ipCountryConfidence;

    /**
     * Construct a new <tt>GeoIpDetails</tt> and populate its fields with
     * the values extracted from <tt>values</tt>.
     * @param values The values as extracted from the response to the minFraud
     *               API call.
     * @return A newly constructed and initialised <tt>GeoIpDetails</tt>.
     */
    public static GeoIpDetails extract(Map<String, String> values) {
        GeoIpDetails d = new GeoIpDetails();
        d.countryMatch = ValueUtils.extractBoolean(values.get("countryMatch"));
        d.highRiskCountry = ValueUtils.extractBoolean(values.get("highRiskCountry"));
        d.distance = ValueUtils.extractInt(values.get("distance"));
        d.ipAccuracyRadius = ValueUtils.extractInt(values.get("ip_accuracyRadius"));
        d.ipCity = values.get("ip_city");
        d.ipRegionCode = values.get("ip_region");
        d.ipRegionName = values.get("ip_regionName");
        d.ipPostalCode = values.get("ip_postalCode");
        d.ipMetroCode = ValueUtils.extractInt(values.get("ip_metroCode"));
        d.ipAreaCode = values.get("ip_areaCode");
        d.ipCountryCode = values.get("countryCode");
        d.ipCountryName = values.get("ip_countryName");
        d.ipContinentCode = values.get("ip_continentCode");
        d.ipLatitude = ValueUtils.extractBigDecimal(values.get("ip_latitude"));
        d.ipLongitude = ValueUtils.extractBigDecimal(values.get("ip_longitude"));
        String tz = values.get("ip_timeZone");
        if (tz != null) {
            d.ipTimeZone = TimeZone.getTimeZone(tz);
        }
        d.ipAsNum = values.get("ip_asnum");
        d.ipUserType = UserType.extract(values.get("ip_userType"));
        d.ipNetSpeed = NetSpeed.extract(values.get("ip_netSpeedCell"));
        d.ipDomain = values.get("ip_domain");
        d.ipIsp = values.get("ip_isp");
        d.ipOrg = values.get("ip_org");
        d.ipCityConfidence = ValueUtils.extractBigDecimal(values.get("ip_cityConf"));
        d.ipRegionConfidence = ValueUtils.extractBigDecimal(values.get("ip_regionConf"));
        d.ipPostalCodeConfidence = ValueUtils.extractBigDecimal(values.get("ip_postalConf"));
        d.ipCountryConfidence = ValueUtils.extractBigDecimal(values.get("ip_countryConf"));
        return d;
    }

    public boolean isCountryMatch() {
        return countryMatch;
    }

    public void setCountryMatch(boolean countryMatch) {
        this.countryMatch = countryMatch;
    }

    public boolean isHighRiskCountry() {
        return highRiskCountry;
    }

    public void setHighRiskCountry(boolean highRiskCountry) {
        this.highRiskCountry = highRiskCountry;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getIpAccuracyRadius() {
        return ipAccuracyRadius;
    }

    public void setIpAccuracyRadius(Integer ipAccuracyRadius) {
        this.ipAccuracyRadius = ipAccuracyRadius;
    }

    public String getIpCity() {
        return ipCity;
    }

    public void setIpCity(String ipCity) {
        this.ipCity = ipCity;
    }

    public String getIpRegionCode() {
        return ipRegionCode;
    }

    public void setIpRegionCode(String ipRegionCode) {
        this.ipRegionCode = ipRegionCode;
    }

    public String getIpRegionName() {
        return ipRegionName;
    }

    public void setIpRegionName(String ipRegionName) {
        this.ipRegionName = ipRegionName;
    }

    public String getIpPostalCode() {
        return ipPostalCode;
    }

    public void setIpPostalCode(String ipPostalCode) {
        this.ipPostalCode = ipPostalCode;
    }

    public Integer getIpMetroCode() {
        return ipMetroCode;
    }

    public void setIpMetroCode(Integer ipMetroCode) {
        this.ipMetroCode = ipMetroCode;
    }

    public String getIpAreaCode() {
        return ipAreaCode;
    }

    public void setIpAreaCode(String ipAreaCode) {
        this.ipAreaCode = ipAreaCode;
    }

    public String getIpCountryCode() {
        return ipCountryCode;
    }

    public void setIpCountryCode(String ipCountryCode) {
        this.ipCountryCode = ipCountryCode;
    }

    public String getIpCountryName() {
        return ipCountryName;
    }

    public void setIpCountryName(String ipCountryName) {
        this.ipCountryName = ipCountryName;
    }

    public String getIpContinentCode() {
        return ipContinentCode;
    }

    public void setIpContinentCode(String ipContinentCode) {
        this.ipContinentCode = ipContinentCode;
    }

    public BigDecimal getIpLatitude() {
        return ipLatitude;
    }

    public void setIpLatitude(BigDecimal ipLatitude) {
        this.ipLatitude = ipLatitude;
    }

    public BigDecimal getIpLongitude() {
        return ipLongitude;
    }

    public void setIpLongitude(BigDecimal ipLongitude) {
        this.ipLongitude = ipLongitude;
    }

    public TimeZone getIpTimeZone() {
        return ipTimeZone;
    }

    public void setIpTimeZone(TimeZone ipTimeZone) {
        this.ipTimeZone = ipTimeZone;
    }

    public String getIpAsNum() {
        return ipAsNum;
    }

    public void setIpAsNum(String ipAsNum) {
        this.ipAsNum = ipAsNum;
    }

    public UserType getIpUserType() {
        return ipUserType;
    }

    public void setIpUserType(UserType ipUserType) {
        this.ipUserType = ipUserType;
    }

    public NetSpeed getIpNetSpeed() {
        return ipNetSpeed;
    }

    public void setIpNetSpeed(NetSpeed ipNetSpeed) {
        this.ipNetSpeed = ipNetSpeed;
    }

    public String getIpDomain() {
        return ipDomain;
    }

    public void setIpDomain(String ipDomain) {
        this.ipDomain = ipDomain;
    }

    public String getIpIsp() {
        return ipIsp;
    }

    public void setIpIsp(String ipIsp) {
        this.ipIsp = ipIsp;
    }

    public String getIpOrg() {
        return ipOrg;
    }

    public void setIpOrg(String ipOrg) {
        this.ipOrg = ipOrg;
    }

    public BigDecimal getIpCityConfidence() {
        return ipCityConfidence;
    }

    public void setIpCityConfidence(BigDecimal ipCityConfidence) {
        this.ipCityConfidence = ipCityConfidence;
    }

    public BigDecimal getIpRegionConfidence() {
        return ipRegionConfidence;
    }

    public void setIpRegionConfidence(BigDecimal ipRegionConfidence) {
        this.ipRegionConfidence = ipRegionConfidence;
    }

    public BigDecimal getIpPostalCodeConfidence() {
        return ipPostalCodeConfidence;
    }

    public void setIpPostalCodeConfidence(BigDecimal ipPostalCodeConfidence) {
        this.ipPostalCodeConfidence = ipPostalCodeConfidence;
    }

    public BigDecimal getIpCountryConfidence() {
        return ipCountryConfidence;
    }

    public void setIpCountryConfidence(BigDecimal ipCountryConfidence) {
        this.ipCountryConfidence = ipCountryConfidence;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder b = new HashCodeBuilder(101, 21);
        b.append(countryMatch);
        b.append(highRiskCountry);
        b.append(distance);
        b.append(ipAccuracyRadius);
        b.append(ipCity);
        b.append(ipRegionCode);
        b.append(ipRegionName);
        b.append(ipPostalCode);
        b.append(ipMetroCode);
        b.append(ipAreaCode);
        b.append(ipCountryCode);
        b.append(ipCountryName);
        b.append(ipContinentCode);
        b.append(ipLatitude);
        b.append(ipLongitude);
        b.append(ipTimeZone);
        b.append(ipAsNum);
        b.append(ipUserType);
        b.append(ipNetSpeed);
        b.append(ipDomain);
        b.append(ipIsp);
        b.append(ipOrg);
        b.append(ipCityConfidence);
        b.append(ipRegionConfidence);
        b.append(ipPostalCodeConfidence);
        b.append(ipCountryConfidence);
        return b.toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof GeoIpDetails)) {
            return false;
        }
        GeoIpDetails rhs = (GeoIpDetails) o;
        EqualsBuilder b = new EqualsBuilder();
        b.append(countryMatch, rhs.countryMatch);
        b.append(highRiskCountry, rhs.highRiskCountry);
        b.append(distance, rhs.distance);
        b.append(ipAccuracyRadius, rhs.ipAccuracyRadius);
        b.append(ipCity, rhs.ipCity);
        b.append(ipRegionCode, rhs.ipRegionCode);
        b.append(ipRegionName, rhs.ipRegionName);
        b.append(ipPostalCode, rhs.ipPostalCode);
        b.append(ipMetroCode, rhs.ipMetroCode);
        b.append(ipAreaCode, rhs.ipAreaCode);
        b.append(ipCountryCode, rhs.ipCountryCode);
        b.append(ipCountryName, rhs.ipCountryName);
        b.append(ipContinentCode, rhs.ipContinentCode);
        b.append(ipLatitude, rhs.ipLatitude);
        b.append(ipLongitude, rhs.ipLongitude);
        b.append(ipTimeZone, rhs.ipTimeZone);
        b.append(ipAsNum, rhs.ipAsNum);
        b.append(ipUserType, rhs.ipUserType);
        b.append(ipNetSpeed, rhs.ipNetSpeed);
        b.append(ipDomain, rhs.ipDomain);
        b.append(ipIsp, rhs.ipIsp);
        b.append(ipOrg, rhs.ipOrg);
        b.append(ipCityConfidence, rhs.ipCityConfidence);
        b.append(ipRegionConfidence, rhs.ipRegionConfidence);
        b.append(ipPostalCodeConfidence, rhs.ipPostalCodeConfidence);
        b.append(ipCountryConfidence, rhs.ipCountryConfidence);
        return b.isEquals();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException x) {
            throw new IllegalStateException("Clone not supported? But..but..", x);
        }
    }
}
