package com.coderman.codemaker.enums;

/**
 * Description:组件类型
 * date: 2021/12/23
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum CompTypeEnum {

    TOOL("tool","工具类组件"),
    //默认
    SPRING("spring","spring容器类组件"),
    DUBBO_API("dubbo-api","dubbo对外暴露api"),
    PACKAGE_PRIVATE("feign-api","spring cloud 对外暴露api"),
    ;
    private String compTag;
    private String compDesc;
    CompTypeEnum(String compTag, String compDesc){
        this.compTag = compTag;
        this.compDesc = compDesc;
    }

    public String getCompTag() {
        return compTag;
    }

    public String getCompDesc() {
        return compDesc;
    }
}
