package com.coderman.codemaker.bean.apidoc;

/**
 * Description:
 * date: 2021/11/22
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ApiParamBean {
    /**
     * 属性名称
     */
    private String fieldName;

    /**
     * 属性类型
     */
    private String fieldType;

    /**
     * 属性描述
     */
    private String fieldDesc;

    /**
     * 是否可为空
     */
    private String nullable;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldDesc() {
        return fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }
}
