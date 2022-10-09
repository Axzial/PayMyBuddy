package fr.axzial.paymybuddy.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class MathUtils {

    public boolean isGreaterOrEqualThan(BigDecimal a, BigDecimal b) {
        int x = a.compareTo(b);
        return x >= 0;
    }

}
