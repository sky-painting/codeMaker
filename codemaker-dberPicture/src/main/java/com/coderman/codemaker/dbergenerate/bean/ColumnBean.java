package com.coderman.codemaker.dbergenerate.bean;

/**
 * Created on 2018-7-19.
 *
 * @author: coderman
 * @version: V1.0
 * @Desc: 表字段描述
 */
public class ColumnBean {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 字段名
     */
    private String columnName;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 是否是主键（或者其他键）
     */
    private String columnKey;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 字段描述
     */
    private String columnComment;

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
}
