package com.coderman.codemaker.enums;

/**
 * Description:领域派生类枚举
 * date: 2021/6/29
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum DomainDerivedElementEnum {
    DTO("dto","数据传输对象"),
    FACADE("facade","api门面接口"),
    FACADE_IMPL("facadeimpl","api门面接口实现"),
    DTOBO_CONVERT("convert","dto-bo相互转换mapstruct接口"),
    DOBO_CONVERT("converter","do-bo相互转换mapstruct接口"),
    VOBO_CONVERT("convertervobo","vo-bo相互转换mapstruct接口"),
    VO("vo","视图传输对象"),
    CONTROLLER("controller","控制器"),
    ;
    private String element;
    private String desc;
    DomainDerivedElementEnum(String element, String desc){
        this.element = element;
        this.desc = desc;
    }


    public String getElement() {
        return element;
    }

    public String getDesc() {
        return desc;
    }
}
