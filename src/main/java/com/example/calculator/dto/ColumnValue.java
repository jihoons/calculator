package com.example.calculator.dto;

import com.example.calculator.type.ValueType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
public class ColumnValue implements Comparable<ColumnValue> {
    private ValueType valueType;
    private BigDecimal minValue = BigDecimal.ZERO;
    private BigDecimal maxValue = BigDecimal.ZERO;
    private Set<String> listValues = null;

    @Override
    public int compareTo(ColumnValue o) {
        return 0;
    }

    public void addListValue(String value) {
        if (listValues == null)
            listValues = new HashSet<>();
        listValues.add(value);
    }

    public boolean okCondition(UserValue userValue) {
        if (valueType == ValueType.Number) {
            return (minValue.compareTo(userValue.getDecimalValue()) <= 0 && maxValue.compareTo(userValue.getDecimalValue()) >= 0);
        } else {
            if (listValues == null)
                return false;
            return listValues.contains(userValue.getStringValue());
        }
    }
}
