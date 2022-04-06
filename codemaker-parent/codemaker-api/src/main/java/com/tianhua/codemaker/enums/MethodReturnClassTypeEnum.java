package com.tianhua.codemaker.enums;

/**
 * Description:方法返回类型
 * date: 2021/10/29
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum MethodReturnClassTypeEnum {
    PAGE("page","page包装对象"),
    LIST("list","list包装对象"),
    SET("set","set集合对象"),
    ONE("one","one单条对象"),
    VOID("void","void空对象"),
    OTHER("other","其他返回对象"),
            ;
    private String code;
    private String desc;
    MethodReturnClassTypeEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }


    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据返回类型判断返回什么类型的对象
     * @param returnClassStr
     * @return
     */
    public static String getReturnType(String returnClassStr){

        if(returnClassStr.contains(MethodReturnClassTypeEnum.VOID.getCode())){
            return MethodReturnClassTypeEnum.VOID.getCode();
        }

        if(returnClassStr.toLowerCase().contains(MethodReturnClassTypeEnum.PAGE.getCode())){
            return MethodReturnClassTypeEnum.PAGE.getCode();
        }

        if(returnClassStr.toLowerCase().contains(MethodReturnClassTypeEnum.LIST.getCode())){
            return MethodReturnClassTypeEnum.LIST.getCode();
        }


        if(returnClassStr.toLowerCase().contains(MethodReturnClassTypeEnum.SET.getCode())){
            return MethodReturnClassTypeEnum.SET.getCode();
        }

        if(returnClassStr.toLowerCase().contains(TemplateFileEnum.VO.getTempFileName())
                || returnClassStr.toLowerCase().contains(TemplateFileEnum.DTO.getTempFileName())){
            return MethodReturnClassTypeEnum.VOID.getCode();

        }

        return MethodReturnClassTypeEnum.OTHER.getCode();

    }

}
