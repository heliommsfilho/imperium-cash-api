package com.github.heliommsfilho.imperium_cash.api.helper;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@Tag("Helpers")
@DisplayName("Number Helper should")
final class NumberHelperTest {

    @Test
    @DisplayName("throw 'IllegalStateException' when trying to instantiate class")
    void tryInstantiate() {
        Assertions.assertThrows(IllegalStateException.class, NumberHelper::new);
    }

    @Test
    @DisplayName("evaluate correctly 'less than' values")
    void evaluateLessThanValues() {
        Assert.assertTrue(NumberHelper.isLessThan(BigDecimal.ZERO, BigDecimal.ONE));
        Assert.assertFalse(NumberHelper.isLessThan(BigDecimal.ONE, BigDecimal.ZERO));
    }

    @Test
    @DisplayName("evaluate correctly 'equal to' values")
    void evaluateEqualToValues() {
        Assert.assertTrue(NumberHelper.equalTo(BigDecimal.ZERO, BigDecimal.ZERO));
        Assert.assertFalse(NumberHelper.equalTo(BigDecimal.ONE, BigDecimal.ZERO));
    }

    @Test
    @DisplayName("evaluate correctly 'greater than' values")
    void evaluateLGraterThanValues() {
        Assert.assertTrue(NumberHelper.greaterThan(BigDecimal.ONE, BigDecimal.ZERO));
        Assert.assertFalse(NumberHelper.greaterThan(BigDecimal.ZERO, BigDecimal.ONE));
    }
}
