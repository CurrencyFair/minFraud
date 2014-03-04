CurrencyFair minFraud API
=========================

This project contains our Java library for interacting with the
[MaxMind minFraud API](http://dev.maxmind.com/minfraud/).

We have tried to make it easy for a Java application to interact with the API,
allowing your (and our!) application to use clean, concise method calls to
send and receive data with the minFraud service.

Prerequisites
-------------
In order to use this library, you will need at least:
 * Java SE 7
   * See the Buildfile for dependency information, or reference the
     [section on Maven configuration](#maven-dependency-configuration)
 * A [minFraud license key](https://www.maxmind.com/en/my_license_key)

To build the library from source, you will need:
 * [Buildr](http://buildr.apache.org/)

Quick Start
-----------
Interaction with minFraud is accomplished using an instance of the
`com.currencyfair.minfraud.MinFraud` interface. An application will construct
a `RiskScoreRequest` instance, pass it to the `MinFraud` implementation and
receive a `RiskScoreResponse` instance.

### Obtaining a `MinFraud` Instance.

This library provides a single implementation of the `MinFraud` interface,
`com.currencyfair.minfraud.MinFraudImpl`. Applications are free to construct
instances of this class themselves; it follows standard JavaBean semantics and
so should be easily usable across many dependency management containers.

For simple applications, the library also provides a `MinFraudBuilder` class
which can be used to obtain a `MinFraud` instance.

```java
public static void main(String[] args) throws Exception {
    MinFraud minFraud = MinFraudBuilder.newInstance()
            .licenseKey(myLicenseKey)
            .build();

}
```

This code block shows the minimum amount of code necessary to obtain a
MinFraud instance - you must provide at least a valid license key in order to
make API requests to minFraud. There are other methods available on the
builder instance allowing further customisation of the generated `MinFraud`
instance, see the Javadoc for full details.

### Creating a Risk Request

Now that you have a `MinFraud` instance, it is time to put a
`RiskScoreRequest` together. This library defines a number of value objects
that map to the values communicated with the minFraud API.

To obtain a `RiskScoreRequest` with its child value objects initialised (but
empty), use the `newInstance` method:

```java
RiskScoreRequest req = RiskScoreRequest.newInstance();
```

You can now populate the request with any values you want to pass over to the
minFraud API:

```java
req.setIpAddress("10.0.0.1");
req.getBillingAddress().setCity("Dublin");
req.getBillingAddress().setRegion("County Dublin");
req.getBillingAddress().setPostalCode("6");
req.getBillingAddress().setCountry("IE");
req.getUserData().setDomain("example.com");
req.getUserData().setEmail("test@example.com");
req.getBrowserIdentity().setSessionId(httpRequest.getSession().getId());
req.getBrowserIdentity().setUserAgent(httpRequest.getHeader("User-Agent"));
req.getBrowserIdentity().setAcceptLanguage(httpRequest.getHeader("Accept-Language"));
```

Now that your request is populated, it is time to invoke the API and get the
`RiskScoreResponse`:

```java
RiskScoreResponse resp = minFraud.calculateRisk(req);
BigDecimal riskScore = resp.getRiskScore();
```

That&apos;s it! You now have your risk score. The `RiskScoreResponse` object also
contains all the details that the minFraud API returns in its response,
easily accessible via Java value objects.

```java
if (resp.getProxyDetails().isAnonymousProxy()) {
    // User is behind an anonymous proxy
}
if (resp.getEmailDetails().isFreeMail()) {
    // User's email address is a free account
}
switch (resp.getBankIdentityDetails().getBinMatch()) {
case YES:
    // Bank identity matched
    break;
case NO:
    // Bank identity didn't match
    break;
case NOT_FOUND:
    // Bank identity was not found
    break;
case NA:
    // Request did not include a bank identity number
    break;
}
```

Maven Dependency Configuration
------------------------------
If you are using Maven in your application's build, here's the dependency
configuration you'll need for your POM:

```xml
<dependencies>
    <dependency>
        <groupId>com.currencyfair</groupId>
        <artifactId>minFraud</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
    <!-- Don't forget an SLF4J implementation. -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>1.7.5</version>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.1</version>
    </dependency>
    <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpclient</artifactId>
        <version>4.3.2</version>
    </dependency>
    <dependency>
        <groupId>org.easymock</groupId>
        <artifactId>easymock</artifactId>
        <version>3.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```
