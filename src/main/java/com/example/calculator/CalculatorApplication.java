package com.example.calculator;

import com.example.calculator.dto.ColumnValueData;
import com.example.calculator.dto.HouseInfo;
import com.example.calculator.dto.UserInfo;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class CalculatorApplication {

    public static void main(String[] args) {
        OverviewTable table = new OverviewTable();
        table.add(new ColumnValueData(1, new BigDecimal("0.85"), "credit_point", new BigDecimal("919.0000"), new BigDecimal("1000.0000"), null));
        table.add(new ColumnValueData(1, new BigDecimal("0.85"), "size", new BigDecimal("10.0000"), new BigDecimal("85.0000"), null));
        table.add(new ColumnValueData(1, new BigDecimal("0.85"), "grade", null, null, "1급지"));
        table.add(new ColumnValueData(1, new BigDecimal("0.85"), "bond_rank", null, null, "선순위"));

        table.add(new ColumnValueData(2, new BigDecimal("0.80"), "credit_point", new BigDecimal("919.0000"), new BigDecimal("1000.0000"), null));
        table.add(new ColumnValueData(2, new BigDecimal("0.80"), "size", new BigDecimal("10.0000"), new BigDecimal("85.0000"), null));
        table.add(new ColumnValueData(2, new BigDecimal("0.80"), "grade", null, null, "2급지"));
        table.add(new ColumnValueData(2, new BigDecimal("0.80"), "bond_rank", null, null, "선순위"));

        table.add(new ColumnValueData(3, new BigDecimal("0.75"), "credit_point", new BigDecimal("919.0000"), new BigDecimal("1000.0000"), null));
        table.add(new ColumnValueData(3, new BigDecimal("0.75"), "size", new BigDecimal("10.0000"), new BigDecimal("85.0000"), null));
        table.add(new ColumnValueData(3, new BigDecimal("0.75"), "grade", null, null, "3급지"));
        table.add(new ColumnValueData(3, new BigDecimal("0.75"), "grade", null, null, "4급지"));
        table.add(new ColumnValueData(3, new BigDecimal("0.75"), "bond_rank", null, null, "선순위"));

        System.out.println(table.getRowMap());
        System.out.println(System.currentTimeMillis());
        CalculatorContext context = new CalculatorContext();
        UserInfo userInfo = new UserInfo();
        userInfo.setCreditPoint(929);
        context.setUserInfo(userInfo);
        HouseInfo houseInfo = new HouseInfo();
        houseInfo.setGrade("3급지");
        houseInfo.setSize(BigDecimal.valueOf(82));
        houseInfo.setBondRank("선순위");
        context.setHouseInfo(houseInfo);

        BigDecimal ltv = table.calculate(context);
        System.out.println(System.currentTimeMillis());
        System.out.println(ltv);
    }

}
