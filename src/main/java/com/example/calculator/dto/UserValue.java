package com.example.calculator.dto;

import com.example.calculator.type.ValueType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserValue {
    private ValueType valueType;
    private BigDecimal decimalValue;
    private String stringValue;

    public UserValue(BigDecimal value) {
        valueType = ValueType.Number;
        this.decimalValue = value;
    }

    public UserValue(String value) {
        valueType = ValueType.List;
        this.stringValue = value;
    }
}
