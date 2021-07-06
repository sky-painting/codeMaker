package com.coderman.codemaker.bean.plantuml;

import java.util.List;

/**
 * Description: 枚举类
 * date: 2021/6/28
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class EnumBean extends AbstractClassBean {

    /**
     * 枚举值
     */
    private List<String> enumValueList;


    /**
     * 构造方法参数
     */
    private String constructParamStr;
    /**
     * 构造方法体初始化设置
     */
    private List<String> constructMethodBodyList;

    public String getConstructParamStr() {
        return constructParamStr;
    }

    public void setConstructParamStr(String constructParamStr) {
        this.constructParamStr = constructParamStr;
    }

    public List<String> getConstructMethodBodyList() {
        return constructMethodBodyList;
    }

    public void setConstructMethodBodyList(List<String> constructMethodBodyList) {
        this.constructMethodBodyList = constructMethodBodyList;
    }

    public List<String> getEnumValueList() {
        return enumValueList;
    }

    public void setEnumValueList(List<String> enumValueList) {
        this.enumValueList = enumValueList;
    }

}
