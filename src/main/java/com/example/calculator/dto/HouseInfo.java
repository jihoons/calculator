package com.example.calculator.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HouseInfo {
    private BigDecimal size;
    private String grade;
    private String bondRank;
}
