package com.example.calculator;

import com.example.calculator.dto.ColumnValueData;
import com.example.calculator.dto.HouseInfo;
import com.example.calculator.dto.UserInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class CalculatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication.class, args);
    }
}
