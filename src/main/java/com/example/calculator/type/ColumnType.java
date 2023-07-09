package com.example.calculator.type;

import com.example.calculator.CalculatorContext;
import com.example.calculator.dto.ColumnValue;
import com.example.calculator.dto.UserValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum ColumnType {
    CreditPoint("credit_point", ValueType.Number, context -> new UserValue(BigDecimal.valueOf(context.getUserInfo().getCreditPoint()))),
    Ltv("ltv", ValueType.Number, context -> new UserValue(context.getLtv())),
    Grade("grade", ValueType.List, context -> new UserValue(context.getHouseInfo().getGrade())),
    BondRank("bond_rank", ValueType.List, context -> new UserValue(context.getHouseInfo().getBondRank())),
    Size("size", ValueType.Number, context -> new UserValue(context.getHouseInfo().getSize()));

    private final String name;
    private final ValueType valueType;
    private final UserValueExtractor extractor;
    private final static Map<String, ColumnType> typeMap;
    static {
        typeMap = new HashMap<>();
        Arrays.stream(values()).forEach(s -> typeMap.put(s.getName().toLowerCase(), s));
    }

    public static ColumnType of(String name) {
        if (name == null)
            return null;
        return typeMap.get(name.toLowerCase());
    }

    @FunctionalInterface
    public interface UserValueExtractor {
        UserValue extract(CalculatorContext context);
    }
}
