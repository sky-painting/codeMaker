package com.coderman.codemaker.bean.plantuml;

import com.coderman.codemaker.bean.invoke.InvokeContextBean;
import com.google.common.collect.Sets;

import java.util.*;

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

    /**
     * 动态调用时序图文件
     */
    private List<String> dynamicInvokeFileList = new ArrayList<>();

    /**
     * 记录解析调用时序中调用方的类和方法名
     * key:调用方的类名.方法名
     * value:被调用方的类名.方法名
     */
    private Map<String,Set<String>> dynamicInvokeChainMap = new HashMap<>();

    public Map<String, Set<String>> getDynamicInvokeChainMap() {
        return dynamicInvokeChainMap;
    }

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

    public List<String> getDynamicInvokeFileList() {
        return dynamicInvokeFileList;
    }

    /**
     * 解析多个调用时序图文件名称
     * @param plantUMLFileName
     */
    public void addDynamicInvokeFile(String plantUMLFileName){
        if(plantUMLFileName.contains(",")){
            String [] arr = plantUMLFileName.split(",");
            for (String fileName : arr){
                dynamicInvokeFileList.add(fileName.trim());
            }
            return;
        }
        dynamicInvokeFileList.add(plantUMLFileName);
    }

    /**
     * 记录调用时序图中的调用方信息
     * @param invokeContextBean
     * @return 是否保存成功
     */
    public boolean addInvokeMethod(InvokeContextBean invokeContextBean){

        String key = invokeContextBean.getInvokerClassBean().getClassName()+"."+invokeContextBean.getInvokerMethodBean().getMethodName();

        Set<String> providerMethodSet = this.dynamicInvokeChainMap.get(key);
        if(providerMethodSet == null){
            providerMethodSet = Sets.newHashSet();
        }
        String value = invokeContextBean.getProviderClassName()+invokeContextBean.getProviderClassMethod();

        if(providerMethodSet.contains(value)){
            return false;
        }
        providerMethodSet.add(value);

        this.getDynamicInvokeChainMap().put(key,providerMethodSet);

        return true;
    }


}
