package com.coderman.codemaker.enums;

/**
 * Description:
 * date: 2021/6/28
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum  ClassEnum {
    ENTITY("entity"),
    CLASS("class"),
    INTERFACE("interface"),
    ENUM("enum"),
    ;
    private String classType;
    ClassEnum(String classType){
        this.classType = classType;
    }

    public String getClassType() {
        return classType;
    }
}
