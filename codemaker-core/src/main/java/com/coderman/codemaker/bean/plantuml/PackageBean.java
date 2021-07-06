package com.coderman.codemaker.bean.plantuml;

import java.util.List;

/**
 * Description:
 * date: 2021/7/2
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class PackageBean {
    private String packageName;

    private List<EnumBean> enumBeanList;
    private List<InterfaceBean> interfaceBeanList;
    private List<ClassBean> classBeanList;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<EnumBean> getEnumBeanList() {
        return enumBeanList;
    }

    public void setEnumBeanList(List<EnumBean> enumBeanList) {
        this.enumBeanList = enumBeanList;
    }

    public List<InterfaceBean> getInterfaceBeanList() {
        return interfaceBeanList;
    }

    public void setInterfaceBeanList(List<InterfaceBean> interfaceBeanList) {
        this.interfaceBeanList = interfaceBeanList;
    }

    public List<ClassBean> getClassBeanList() {
        return classBeanList;
    }

    public void setClassBeanList(List<ClassBean> classBeanList) {
        this.classBeanList = classBeanList;
    }
}
