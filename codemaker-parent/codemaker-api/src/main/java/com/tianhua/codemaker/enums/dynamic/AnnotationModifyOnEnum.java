package com.tianhua.codemaker.enums.dynamic;

/**
 * Description:动态注解需要修饰的类型
 * date: 2021/10/16
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum AnnotationModifyOnEnum {
    THIS("this", "当前类"),
    CHILD("child", "子类"),
    ;
    private String code;
    private String desc;


    AnnotationModifyOnEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }




}