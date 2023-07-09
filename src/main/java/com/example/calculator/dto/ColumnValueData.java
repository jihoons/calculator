package com.example.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnValueData {
    private long overviewTblRowSeq;
    private BigDecimal value;
    private String colName;
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private String liveValue;
}
