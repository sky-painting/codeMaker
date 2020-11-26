package com.coderman.codemaker.dbops.bean;

/**
 * description: ColumnOpsBean <br>
 * date: 2020/8/9 13:37 <br>
 * author: coderman <br>
 * version: 1.0 <br>\
 * 数据字段变更运维对象
 */
public class ColumnOpsBean {
    /**
     * 变更类型-alter
     */
    private String opsName;
    /**
     * 变更的表名
     */
    private String tableName;
    /**
     * 变更的字段内容
     */
    private String opsColumn;

    public String getOpsName() {
        return opsName;
    }

    public void setOpsName(String opsName) {
        this.opsName = opsName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOpsColumn() {
        return opsColumn;
    }

    public void setOpsColumn(String opsColumn) {
        this.opsColumn = opsColumn;
    }
}
