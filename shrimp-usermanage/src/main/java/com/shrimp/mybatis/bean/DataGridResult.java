package com.shrimp.mybatis.bean;

import lombok.Data;

import java.util.List;

/**
 * 功能说明: <br>
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-01-17<br>
 */
@Data
public class DataGridResult {
    private long total;
    private List<?> rows;

    public DataGridResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public DataGridResult() {
    }
}
