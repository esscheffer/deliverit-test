package com.example.erikscheffer.deliverittestbackend.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtils {
    public static BigDecimal percentage(BigDecimal base, BigDecimal percentage) {
        return base.multiply(percentage).divide(new BigDecimal(100), 2, RoundingMode.DOWN);
    }
}
