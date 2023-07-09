package com.example.calculator.service;

import com.example.calculator.CalculatorContext;
import com.example.calculator.OverviewTable;
import com.example.calculator.dto.ColumnValueData;
import com.example.calculator.dto.HouseInfo;
import com.example.calculator.dto.UserInfo;
import com.example.calculator.repository.OverviewTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
@Service
public class Calculator implements CommandLineRunner {
    private final OverviewTableRepository repository;
    @Override
    public void run(String... args) throws Exception {
        List<ColumnValueData> overviewTableData = repository.getOverviewTable(1);
        OverviewTable overviewTable = new OverviewTable();
        overviewTableData.forEach(overviewTable::add);

        calculate(overviewTable);
        calculate(overviewTable);

    }

    public BigDecimal calculate(OverviewTable overviewTable) {
        long start = System.nanoTime();
        CalculatorContext context = new CalculatorContext();
        UserInfo userInfo = new UserInfo();
        userInfo.setCreditPoint(929);
        context.setUserInfo(userInfo);
        HouseInfo houseInfo = new HouseInfo();
        houseInfo.setGrade("3급지");
        houseInfo.setSize(BigDecimal.valueOf(82));
        houseInfo.setBondRank("선순위");
        context.setHouseInfo(houseInfo);

        BigDecimal ltv = overviewTable.calculate(context);
        long end = System.nanoTime();
        Duration duration = Duration.ofNanos(end - start);
        System.out.println(duration.toMillis());
        System.out.println(duration.toNanos());
        System.out.println(ltv);
        return ltv;
    }
}
