package com.coderman.codemaker.dberparse.bean;

/**
 * Description:
 * date: 2021/8/12
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 * 表字段模型
 */
public class EntityFieldBean {
    /**
     * 字段名称
     */
    private String columnName;
    /**
     * 字段数据类型包括大小
     */
    private String columnType;
    /**
     * 字段描述
     */
    private String columnDesc;

    /**
     * 是否可为空
     */
    private Boolean nullable;

    /**
     * 字段默认值
     */
    private String defaultValue;

    /**
     * 是否是主键
     */
    private Boolean isPkey;

    /**
     * 是否是联合索引
     */
    private Boolean isUKey;

    public Boolean getUKey() {
        return isUKey;
    }

    public void setUKey(Boolean UKey) {
        isUKey = UKey;
    }

    public Boolean getPkey() {
        return isPkey;
    }

    public void setPkey(Boolean pkey) {
        isPkey = pkey;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnDesc() {
        return columnDesc;
    }

    public void setColumnDesc(String columnDesc) {
        this.columnDesc = columnDesc;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
