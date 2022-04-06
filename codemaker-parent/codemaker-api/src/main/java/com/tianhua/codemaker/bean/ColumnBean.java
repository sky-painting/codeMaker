package com.tianhua.codemaker.bean;

import com.tianhua.codemaker.bean.plantuml.FieldBean;
import com.tianhua.codemaker.enums.VisibilityEnum;

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
     * 默认值
     */
    private String columnDefaultValue;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 字段特殊描述值
     */
    private String extraInfo;
    /**
     * 是否是主键（或者其他键）
     */
    private String columnKey;

    /**
     * 是否可以为空,NO,YES
     */
    private String isNullable;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 字段描述
     */
    private String columnComment;

    /**
     * 字段类型对应的javaentity的类型名
     */
    private String columnTypeName;

    /**
     * 字段对应的javaentity属性名
     */
    private String columnFieldName;

    /**
     * 字段对应的mybatis resultMap的jdbc类型
     */
    private String columnJdbcTypeName;

    /**
     * 字段对应的大写类名
     */
    private String columnUperName;

    public String getColumnUperName() {
        return columnUperName;
    }

    public void setColumnUperName(String columnUperName) {
        this.columnUperName = columnUperName;
    }

    public String getColumnJdbcTypeName() {
        return columnJdbcTypeName;
    }

    public void setColumnJdbcTypeName(String columnJdbcTypeName) {
        this.columnJdbcTypeName = columnJdbcTypeName;
    }

    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public String getColumnFieldName() {
        return columnFieldName;
    }

    public void setColumnFieldName(String columnFieldName) {
        this.columnFieldName = columnFieldName;
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

    public String getColumnDefaultValue() {
        return columnDefaultValue;
    }

    public void setColumnDefaultValue(String columnDefaultValue) {
        this.columnDefaultValue = columnDefaultValue;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    @Override
    public String toString() {
        return "ColumnBean{" +
                "tableName='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", columnDefaultValue='" + columnDefaultValue + '\'' +
                ", dataType='" + dataType + '\'' +
                ", extraInfo='" + extraInfo + '\'' +
                ", columnKey='" + columnKey + '\'' +
                ", isNullable='" + isNullable + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnComment='" + columnComment + '\'' +
                '}';
    }

    public FieldBean convert2FieldBean(){
        FieldBean fieldBean = new FieldBean();
        fieldBean.setDesc(columnComment);
        fieldBean.setVisibility(VisibilityEnum.PRIVATE.getVisibility());
        fieldBean.setDbColumnName(columnName);
        fieldBean.setFieldName(columnTypeName+" "+ columnFieldName);
        return fieldBean;
    }

}
