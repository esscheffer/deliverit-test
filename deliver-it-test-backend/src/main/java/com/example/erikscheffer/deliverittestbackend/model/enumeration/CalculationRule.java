package com.example.erikscheffer.deliverittestbackend.model.enumeration;

public enum CalculationRule {
    NO_RULE, UP_TO_3_DAYS, OVER_3_DAYS, OVER_5_DAYS;

    public static CalculationRule getByDaysCount(int days) {
        if (days > 5) {
            return OVER_5_DAYS;
        } else if (days > 3) {
            return OVER_3_DAYS;
        } else if (days > 0) {
            return UP_TO_3_DAYS;
        } else {
            return NO_RULE;
        }
    }
}
