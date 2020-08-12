package com.github.heliommsfilho.imperium_cash.api.helper;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public final class NumberHelperTest {

    @Test
    public void tryInstantiate_shouldThrowIllegalStateException() {
        Assertions.assertThrows(IllegalStateException.class, NumberHelper::new);
    }

    @Test
    public void isLessThan_shouldReturnTrue() {
        Assert.assertTrue(NumberHelper.isLessThan(BigDecimal.ZERO, BigDecimal.ONE));
        Assert.assertFalse(NumberHelper.isLessThan(BigDecimal.ONE, BigDecimal.ZERO));
    }

    @Test
    public void equalToThan_shouldReturnTrue() {
        Assert.assertTrue(NumberHelper.equalTo(BigDecimal.ZERO, BigDecimal.ZERO));
    }

    @Test
    public void greaterThanThan_shouldReturnTrue() {
        Assert.assertTrue(NumberHelper.greaterThan(BigDecimal.ONE, BigDecimal.ZERO));
        Assert.assertFalse(NumberHelper.greaterThan(BigDecimal.ZERO, BigDecimal.ONE));
    }
}
