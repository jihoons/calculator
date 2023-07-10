package com.example.calculator.service;

import com.example.calculator.CalculatorContext;
import com.example.calculator.OverviewTable;
import com.example.calculator.dto.ColumnValueData;
import com.example.calculator.dto.HouseInfo;
import com.example.calculator.dto.UserInfo;
import com.example.calculator.repository.OverviewTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class Calculator implements CommandLineRunner {
    private final OverviewTableRepository repository;
    @Override
    public void run(String... args) throws Exception {
        loadOverviewChart();
//        OverviewTable overviewTable = getOverviewTable();

//        overviewTable = getOverviewTable();

        List<OverviewTable> availableOverviewTables = getAvailableOverviewTables();
        Map<OverviewTable, BigDecimal> successOverviewTables = new HashMap<>();
        for (OverviewTable availableOverviewTable : availableOverviewTables) {
            BigDecimal ltv = calculate(availableOverviewTable);
            if (ltv != null) {
                successOverviewTables.put(availableOverviewTable, ltv);
            }
        }

        System.out.println(successOverviewTables);

//        OverviewTable overviewTable = overviewTableMap.get(1L);
//        calculate(overviewTable);
//        calculate(overviewTable);

    }

    private List<OverviewTable> getAvailableOverviewTables() {
        List<OverviewTable> overviewTables = this.overviewTableList;
        LocalDateTime now = LocalDateTime.now();
        return overviewTables.stream().filter(ot -> ot.getStartAt().isBefore(now) && ot.getEndAt().isAfter(now)).toList();
    }

    private OverviewTable getOverviewTable() {
        long start = System.nanoTime();
        List<ColumnValueData> overviewTableData = repository.getOverviewTable(1);
        OverviewTable overviewTable = new OverviewTable();
        overviewTableData.forEach(overviewTable::add);
        long end = System.nanoTime();
        Duration duration = Duration.ofNanos(end - start);
        System.out.println(duration.toMillis());
        System.out.println(duration.toNanos());
        return overviewTable;
    }

    private List<OverviewTable> overviewTableList;

    @Scheduled(initialDelay = 0, fixedDelayString = "${app.overviewtable.refresh:3}", timeUnit = TimeUnit.MINUTES)
    public void loadOverviewChart() {
        List<ColumnValueData> overviewTableDataList = repository.getOverviewTable();
        Map<Long, OverviewTable> overviewTableMap = new HashMap<>();
        for (ColumnValueData columnValueData : overviewTableDataList) {
            OverviewTable overviewTable = overviewTableMap.computeIfAbsent(columnValueData.getOverviewTblSeq(), _seq -> {
                OverviewTable _overviewTbl = new OverviewTable();
                _overviewTbl.setOverviewTabSeq(_seq);
                _overviewTbl.setStartAt(columnValueData.getStartDate());
                _overviewTbl.setEndAt(columnValueData.getEndDate());
                return _overviewTbl;
            });
            overviewTable.add(columnValueData);
        }

        this.overviewTableList = new ArrayList<>(overviewTableMap.values());
    }

    public BigDecimal calculate(OverviewTable overviewTable) {
        long start = System.nanoTime();
        CalculatorContext context = new CalculatorContext();
        UserInfo userInfo = new UserInfo();
        userInfo.setCreditPoint(829);
        context.setUserInfo(userInfo);
        HouseInfo houseInfo = new HouseInfo();
        houseInfo.setGrade("4급지");
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
