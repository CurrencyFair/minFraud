package com.currencyfair.minfraud.util;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ValueUtilsTest {
    @Test
    public void testExtractBigDecimalHandlesNull() throws Exception {
        Assert.assertNull(ValueUtils.extractBigDecimal(null));
    }

    @Test
    public void testExtractBigDecimalParsesDecimalValue() throws Exception {
        final String value = "878523.23";
        Assert.assertEquals(new BigDecimal(value), ValueUtils.extractBigDecimal(value));
    }

    @Test(expected = NumberFormatException.class)
    public void testExtractBigDecimalThrowsNumberFormatExceptionOnInvalidInput() throws Exception {
        ValueUtils.extractBigDecimal("NotANumber");
    }

    @Test
    public void testExtractBooleanOrNullUnderstandsYesAndNo() throws Exception {
        Assert.assertTrue(ValueUtils.extractBooleanOrNull("Yes"));
        Assert.assertFalse(ValueUtils.extractBooleanOrNull("No"));
    }

    @Test
    public void testExtractBooleanOrNullReturnsNullOnNullInput() throws Exception {
        Assert.assertNull(ValueUtils.extractBooleanOrNull(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExtractBooleanOrNullThrowsExceptionOnInvalidInput() throws Exception {
        ValueUtils.extractBooleanOrNull("True");
    }

    @Test
    public void testExtractBooleanUnderstandsYesAndNo() throws Exception {
        Assert.assertTrue(ValueUtils.extractBoolean("Yes"));
        Assert.assertFalse(ValueUtils.extractBoolean("No"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExtractBooleanThrowsExceptionOnNullInput() throws Exception {
        ValueUtils.extractBoolean(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExtractBooleanThrowsExceptionOnInvalidInput() throws Exception {
        ValueUtils.extractBoolean("False");
    }

    @Test
    public void testExtractIntParsesIntegerValues() throws Exception {
        Assert.assertEquals(Integer.valueOf(87523), ValueUtils.extractInt("87523"));
        Assert.assertEquals(Integer.valueOf(-24), ValueUtils.extractInt("-24"));
        Assert.assertEquals(Integer.valueOf(8475927), ValueUtils.extractInt("8475927"));
    }

    @Test
    public void testExtractIntReturnsNullOnNullInput() throws Exception {
        Assert.assertNull(ValueUtils.extractInt(null));
    }

    @Test(expected = NumberFormatException.class)
    public void testExtractIntThrowsExceptionOnInvalidInput() throws Exception {
        ValueUtils.extractInt("efg234");
    }

    @Test
    public void testAddDoesNotPutAnythingInTheMapForNullValue() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        ValueUtils.add(map, "key", null);
        Assert.assertTrue(map.isEmpty());
    }

    @Test
    public void testAddDoesNotPutAnythingInTheMapForNullName() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        ValueUtils.add(map, null, "value");
        Assert.assertTrue(map.isEmpty());
    }

    @Test
    public void testAddPutsToStringValueInTheMapForNonStringifyObjects() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        ValueUtils.add(map, "key", new Integer(1248757));
        Assert.assertEquals("1248757", map.get("key"));
    }

    @Test
    public void testAddPutsStringifiedValueInTheMapForStringifiableObjects() throws Exception {
        Stringifiable stringifiable = EasyMock.createMock(Stringifiable.class);
        EasyMock.expect(stringifiable.stringify()).andReturn("STRING!");
        EasyMock.replay(stringifiable);
        Map<String, String> map = new HashMap<String, String>();
        ValueUtils.add(map, "key", stringifiable);
        Assert.assertEquals("STRING!", map.get("key"));
        EasyMock.verify(stringifiable);
    }
}
