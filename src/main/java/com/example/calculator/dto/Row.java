package com.example.calculator.dto;

import com.example.calculator.CalculatorContext;
import com.example.calculator.type.ColumnType;
import com.example.calculator.type.ValueType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Row {
    private long overviewTblRowSeq;
    private BigDecimal value = BigDecimal.ZERO;
    private Map<ColumnType, Column> columnMap = new LinkedHashMap<>();

    public void addColumn(ColumnType columnType, BigDecimal minValue, BigDecimal maxValue, String listValue) {
        if (columnType == null)
            return;

        Column column = columnMap.get(columnType);
        if (column == null) {
            column = createColumn(columnType, minValue, maxValue, listValue);
        } else if (columnType.getValueType() == ValueType.List) {
            column.getColumnValue().addListValue(listValue);
        } else {
            throw new RuntimeException("같은 조건이 숫자형에서는 있을 수 없습니다.");
        }

        columnMap.put(columnType, column);
    }

    private Column createColumn (ColumnType columnType, BigDecimal minValue, BigDecimal maxValue, String listValue) {
        ColumnValue value = new ColumnValue();
        value.setValueType(columnType.getValueType());
        if (columnType.getValueType() == ValueType.Number) {
            value.setMinValue(minValue);
            value.setMaxValue(maxValue);
        } else {
            if (listValue != null) {
                value.addListValue(listValue);
            }
        }
        Column column = new Column();
        column.setColumnType(columnType);
        column.setColumnValue(value);

        return column;
    }

    public boolean okCondition(CalculatorContext context) {
        for (Column column : columnMap.values()) {
            if (!column.okCondition(context))
                return false;
        }
        return true;
    }
}
