package com.example.calculator.dto;

import com.example.calculator.type.ValueType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

@Data
public class ColumnValue implements Comparable<ColumnValue> {
    private ValueType valueType;
    private BigDecimal minValue = BigDecimal.ZERO;
    private BigDecimal maxValue = BigDecimal.ZERO;
    private Set<String> listValues = Collections.emptySet();

    @Override
    public int compareTo(ColumnValue o) {
        return 0;
    }

    public boolean okCondition(UserValue userValue) {
        if (valueType == ValueType.Number) {
            return (minValue.compareTo(userValue.getDecimalValue()) <= 0 && maxValue.compareTo(userValue.getDecimalValue()) >= 0);
        } else {
            return listValues.contains(userValue.getStringValue());
        }
    }
}
