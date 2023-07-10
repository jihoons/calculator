package com.example.calculator;

import com.example.calculator.dto.ColumnValueData;
import com.example.calculator.dto.Row;
import com.example.calculator.type.ColumnType;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@ToString
public class OverviewTable {
    private long overviewTabSeq;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private final Map<Long, Row> rowMap = new LinkedHashMap<>();
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
        row.addColumn(columnType, data.getMinValue(), data.getMaxValue(), data.getLiveValue());
    }

    public BigDecimal calculate(CalculatorContext context) {
        Row row = rowMap.values().stream().filter(r -> r.okCondition(context)).findFirst().orElse(null);
        if (row == null) {
            return null;
        }
        return row.getValue();
    }

    public void setOverviewTabSeq(long overviewTabSeq) {
        this.overviewTabSeq = overviewTabSeq;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }


}
