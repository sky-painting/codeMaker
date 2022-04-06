package com.tianhua.codemaker.bean.plantuml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 枚举类
 * date: 2021/6/28
 *
 * @author shenshuai
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


    public Map<String,Object> buildVarMap(){
        Map<String, Object> varMap = new HashMap<>();
        varMap.put("class",this);
        varMap.put("fields",this.getFieldBeanList());
        varMap.put("methods",this.getMethodBeanList());
        varMap.put("enums",this.getEnumValueList());
        varMap.put("methods",this.getMethodBeanList());
        varMap.put("bodys",this.getConstructMethodBodyList());
        return varMap;
    }
    public EnumBean copySelf(){
        EnumBean enumBean = new EnumBean();
        enumBean.setFieldBeanList(this.getFieldBeanList());
        enumBean.setPlantUMLPackage(this.getPlantUMLPackage());
        enumBean.setAuthor(this.getAuthor());
        enumBean.setEnumValueList(this.getEnumValueList());
        enumBean.setConstructMethodBodyList(this.getConstructMethodBodyList());
        enumBean.setClassDesc(this.getClassDesc());
        enumBean.setClassName(this.getClassName());
        enumBean.setMethodBeanList(this.getMethodBeanList());
        enumBean.setImportClassList(this.getImportClassList());
        enumBean.setConstructParamStr(this.getConstructParamStr());
        enumBean.setPackageName(this.getPackageName());
        return enumBean;
    }
}
