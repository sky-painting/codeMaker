package com.coderman.codemaker.enums.dynamic;

/**
 * Description:
 * date: 2021/11/24
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum DecorateTypeEnum {
    CLASS_ANNOTATION("CLASS_ANNOTATION","类上加注解"),
    CLASS_IMPL_INTERFACE("CLASS_IMPL","类加上实现接口"),

    METHOD_ANNOTATION("METHOD_ANNOTATION","方法上加注解"),
    METHOD_RETURN_CLASS("METHOD_RETURN_CLASS","方法返回值类包装"),
    METHOD_RETURN_PAGEDTO_CLASS("METHOD_RETURN_PAGEDTO_CLASS","方法分页类包装"),

    METHOD_RETURN_PAGEVO_CLASS("METHOD_RETURN_PAGEVO_CLASS","方法分页类包装"),

    ;

    private String code;
    private String desc;
    DecorateTypeEnum(String code, String desc){
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
