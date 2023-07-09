package com.example.calculator.repository;

import com.example.calculator.dto.ColumnValueData;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class OverviewTableRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<ColumnValueData> getOverviewTable(long overviewTableSeq) {
        return jdbcTemplate.query("""
                select ot.overview_tbl_seq,
                       ot.name,
                       otr.overview_tbl_row_seq,
                       otr.value,
                       otct.col_name,
                       otct.value_type,
                       otc.min_value,
                       otc.max_value,
                       otc.overview_tbl_col_seq,
                       otclv.value list_value
                from overview_tbl ot
                inner join overview_tbl_row otr on otr.overview_tbl_seq = ot.overview_tbl_seq
                inner join overview_tbl_col otc on ot.overview_tbl_seq = otc.overview_tbl_seq and otr.overview_tbl_row_seq = otc.overview_tbl_row_seq
                inner join overview_tbl_col_type otct on otc.overview_tbl_col_type = otct.col_type_seq
                left join overview_tbl_col_list_value otclv on otc.overview_tbl_col_seq = otclv.overview_tbl_col_seq
                where ot.overview_tbl_seq = ?
                order by otr.overview_tbl_row_seq, otct.sort_order
                """, (rs, rowNum) -> {
                    ColumnValueData valueData = new ColumnValueData();
                    valueData.setOverviewTblRowSeq(rs.getLong("overview_tbl_row_seq"));
                    valueData.setValue(rs.getBigDecimal("value"));
                    valueData.setColName(rs.getString("col_name"));
                    valueData.setMinValue(rs.getBigDecimal("min_value"));
                    valueData.setMaxValue(rs.getBigDecimal("max_value"));
                    valueData.setLiveValue(rs.getString("list_value"));
                    return valueData;
                }, overviewTableSeq);
    }
}
