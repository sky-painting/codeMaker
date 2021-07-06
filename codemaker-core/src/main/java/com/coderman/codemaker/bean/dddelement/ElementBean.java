package com.coderman.codemaker.bean.dddelement;

import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.EnumBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;

import java.util.List;

/**
 * Description:
 * date: 2021/6/29
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public abstract class ElementBean {
    protected String packageName;
    protected List<ClassBean> classBeanList;
    protected List<InterfaceBean> interfaceBeanList;
    protected List<EnumBean> enumBeanList;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<ClassBean> getClassBeanList() {
        return classBeanList;
    }

    public void setClassBeanList(List<ClassBean> classBeanList) {
        this.classBeanList = classBeanList;
    }

    public List<InterfaceBean> getInterfaceBeanList() {
        return interfaceBeanList;
    }

    public void setInterfaceBeanList(List<InterfaceBean> interfaceBeanList) {
        this.interfaceBeanList = interfaceBeanList;
    }

    public List<EnumBean> getEnumBeanList() {
        return enumBeanList;
    }

    public void setEnumBeanList(List<EnumBean> enumBeanList) {
        this.enumBeanList = enumBeanList;
    }
}
