package com.example.calculator;

import com.example.calculator.CalculatorContext;
import com.example.calculator.dto.ColumnValueData;
import com.example.calculator.dto.HouseInfo;
import com.example.calculator.dto.Row;
import com.example.calculator.dto.UserInfo;
import com.example.calculator.type.ColumnType;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
public class OverviewTable {
    private Map<Long, Row> rowMap = new HashMap<>();
    public void add(ColumnValueData data) {
        ColumnType columnType = ColumnType.of(data.getColName());
        if (columnType == null)
            return;

        Row row = rowMap.computeIfAbsent(data.getOverviewTblRowSeq(), _seq -> {
            Row _row = new Row();
            _row.setValue(data.getValue());
            _row.setOverviewTblRowSeq(_seq);
            return _row;
        });
        row.addColumn(data.getColName(), data.getMinValue(), data.getMaxValue(), data.getLiveValue());
    }

    public BigDecimal calculate(CalculatorContext context) {
        Row row = rowMap.values().stream().filter(r -> r.okCondition(context)).findFirst().orElse(null);
        if (row == null) {
            return null;
        }
        return row.getValue();
    }
}
