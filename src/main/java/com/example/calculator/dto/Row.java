package com.example.calculator.dto;

import com.example.calculator.CalculatorContext;
import com.example.calculator.type.ColumnType;
import com.example.calculator.type.ValueType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Row {
    private long overviewTblRowSeq;
    private BigDecimal value = BigDecimal.ZERO;
    private List<Column> columns = new ArrayList<>();

    public void addColumn(String columnName, BigDecimal minValue, BigDecimal maxValue, String listValue) {
        ColumnType columnType = ColumnType.of(columnName);
        if (columnType == null)
            return;

        ColumnValue value = new ColumnValue();
        value.setValueType(columnType.getValueType());
        if (columnType.getValueType() == ValueType.Number) {
            value.setMinValue(minValue);
            value.setMaxValue(maxValue);
        } else {
            if (listValue == null) {
                value.setListValues(Collections.emptySet());
            } else {
                value.setListValues(Arrays.stream(listValue.split(",")).collect(Collectors.toSet()));
            }
        }
        Column column = new Column();
        column.setColumnType(columnType);
        column.setColumnValue(value);

        columns.add(column);
    }

    public boolean okCondition(CalculatorContext context) {
        for (Column column : columns) {
            if (!column.okCondition(context))
                return false;
        }
        return true;
    }
}
