package com.example.calculator.dto;

import com.example.calculator.CalculatorContext;
import com.example.calculator.type.ColumnType;
import lombok.Data;

@Data
public class Column {
    private ColumnType columnType;
    private ColumnValue columnValue;

    public boolean okCondition(CalculatorContext context) {
        UserValue userValue = extractValueForCompare(context);
        return columnValue.okCondition(userValue);
    }

    private UserValue extractValueForCompare(CalculatorContext context) {
        return columnType.getExtractor().extract(context);
    }
}
