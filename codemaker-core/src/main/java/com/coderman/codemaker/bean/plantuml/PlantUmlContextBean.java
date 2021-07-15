package com.coderman.codemaker.bean.plantuml;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * date: 2021/6/28
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */

public class PlantUmlContextBean {

    private Map<String,ClassBean> classBeanMap = new HashMap<>();

    private Map<String,EnumBean> enumBeanMap = new HashMap<>();

    private Map<String, InterfaceBean> interfaceBeanMap = new HashMap<>();

    private Map<String,PackageBean> packageBeanMap = new HashMap<>();

    /**
     * 派生类上下文
     */
    private PlantUmlContextBean derivedPlantUmlContextBean;

    public PlantUmlContextBean getDerivedPlantUmlContextBean() {
        return derivedPlantUmlContextBean;
    }

    public void setDerivedPlantUmlContextBean(PlantUmlContextBean derivedPlantUmlContextBean) {
        this.derivedPlantUmlContextBean = derivedPlantUmlContextBean;
    }

    public Map<String, ClassBean> getClassBeanMap() {
        return classBeanMap;
    }

    public Map<String, EnumBean> getEnumBeanMap() {
        return enumBeanMap;
    }

    public Map<String, InterfaceBean> getInterfaceBeanMap() {
        return interfaceBeanMap;
    }

    public void addClassBean(ClassBean classBean){
        this.classBeanMap.put(classBean.getClassName(),classBean);
    }

    public void addInterfaceBean(InterfaceBean interfaceBean){
        this.interfaceBeanMap.put(interfaceBean.getClassName(),interfaceBean);
    }

    public void addEnumBean(EnumBean enumBean){
        this.enumBeanMap.put(enumBean.getClassName(),enumBean);
    }

    public Map<String, PackageBean> getPackageBeanMap() {
        return packageBeanMap;
    }

    public void addPacakge(PackageBean packageBean){
        this.packageBeanMap.put(packageBean.getPackageName(),packageBean);
    }
}
