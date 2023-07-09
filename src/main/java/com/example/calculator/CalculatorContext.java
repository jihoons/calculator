package com.example.calculator;

import com.example.calculator.dto.HouseInfo;
import com.example.calculator.dto.UserInfo;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CalculatorContext {
    private UserInfo userInfo;
    private HouseInfo houseInfo;
    private BigDecimal ltv;
}
