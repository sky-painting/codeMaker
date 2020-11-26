package com.coderman.codemaker.bean;

/**
 * Created  on 2018-7-19.
 *
 * @author: fanchunshuai
 * @version: V1.0
 * @Desc:表信息描述
 */
public class TableBean {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表描述
     */
    private String tableComment;


    /**
     * 使用的存储引擎
     */
    private String engineName;

    /**
     * 字符集
     */
    private String tableCollation;

    /**
     * 表记录数
     */
    private Long tableRows;

    /**
     * 表名对应的驼峰式命名
     */
    private String humpTableName;

    /**
     * 表名对应的类名
     */
    private String humpClassName;
    /**
     * 列名列表，字符串逗号分割
     */
    private String columnNameList;

    /**
     * 插入sql value替换字符串
     */
    private String insertColumnNameList;

    /**
     * 插入sql value替换字符串
     */
    private String insertColumnNames;
    /**
     * 修改sql value替换字符串
     */
    private String updateColumnNameList;

    public String getInsertColumnNames() {
        return insertColumnNames;
    }

    public void setInsertColumnNames(String insertColumnNames) {
        this.insertColumnNames = insertColumnNames;
    }

    public String getUpdateColumnNameList() {
        return updateColumnNameList;
    }

    public void setUpdateColumnNameList(String updateColumnNameList) {
        this.updateColumnNameList = updateColumnNameList;
    }

    public String getInsertColumnNameList() {
        return insertColumnNameList;
    }

    public void setInsertColumnNameList(String insertColumnNameList) {
        this.insertColumnNameList = insertColumnNameList;
    }

    public String getColumnNameList() {
        return columnNameList;
    }

    public void setColumnNameList(String columnNameList) {
        this.columnNameList = columnNameList;
    }

    public String getHumpClassName() {
        return humpClassName;
    }

    public void setHumpClassName(String humpClassName) {
        this.humpClassName = humpClassName;
    }

    public String getHumpTableName() {
        return humpTableName;
    }

    public void setHumpTableName(String humpTableName) {
        this.humpTableName = humpTableName;
    }

    public Long getTableRows() {
        return tableRows;
    }

    public void setTableRows(Long tableRows) {
        this.tableRows = tableRows;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getEngineName() {
        return engineName;
    }

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    public String getTableCollation() {
        return tableCollation;
    }

    public void setTableCollation(String tableCollation) {
        this.tableCollation = tableCollation;
    }
}
