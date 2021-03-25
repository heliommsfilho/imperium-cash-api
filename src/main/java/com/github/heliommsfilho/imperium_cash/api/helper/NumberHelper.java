package com.github.heliommsfilho.imperium_cash.api.helper;

import java.math.BigDecimal;

public final class NumberHelper {

    NumberHelper() {
        throw new IllegalStateException("NumberHelper is static and should not be instantiated");
    }

    public static final BigDecimal A_HUNDRED = new BigDecimal("100.00");

    public static boolean isLessThan(BigDecimal n1, BigDecimal n2) {
        return n1.compareTo(n2) < 0;
    }

    public static boolean equalTo(BigDecimal n1, BigDecimal n2) {
        return n1.compareTo(n2) == 0;
    }

    public static boolean greaterThan(BigDecimal n1, BigDecimal n2) {
        return n1.compareTo(n2) > 0;
    }
}
