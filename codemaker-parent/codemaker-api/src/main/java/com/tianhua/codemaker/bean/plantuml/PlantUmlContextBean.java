package com.tianhua.codemaker.bean.plantuml;

import com.tianhua.codemaker.bean.component.ComponentContextBean;
import com.tianhua.codemaker.bean.config.FtlBean;
import com.tianhua.codemaker.bean.config.PomBean;
import com.tianhua.codemaker.bean.invoke.InvokeContextBean;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Description:
 * date: 2021/6/28
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */

public class PlantUmlContextBean {

    private Map<String, ClassBean> classBeanMap = new HashMap<>();

    private Map<String, EnumBean> enumBeanMap = new HashMap<>();

    private Map<String, InterfaceBean> interfaceBeanMap = new HashMap<>();

    /**
     * pom相关配置
     */
    public Map<String, PomBean> pomBeanMap = new HashMap<>();

    /**
     * 派生类上下文
     */
    private PlantUmlContextBean derivedPlantUmlContextBean;

    /**
     * 扫描到的依赖组件
     */
    private Map<String, ComponentContextBean> compContextBeanMap = new HashMap<>();


    /**
     * app应用名称
     */
    private String appName;

    /**
     * app应用类型
     */
    private String applicationType;

    /**
     * 动态调用时序图文件
     */
    private List<String> dynamicInvokeFileList = new ArrayList<>();

    /**
     * 记录解析调用时序中调用方的类和方法名
     * key:调用方的类名.方法名
     * value:被调用方的类名.方法名
     */
    private Map<String, Set<String>> dynamicInvokeChainMap = new HashMap<>();


    /**
     * 自定义代码元素配置bean对象，在对应elementHandler做路由标示
     */
    private FtlBean ftlBean;

    public FtlBean getFtlBean() {
        return ftlBean;
    }

    public void setFtlBean(FtlBean ftlBean) {
        this.ftlBean = ftlBean;
    }

    public Map<String, Set<String>> getDynamicInvokeChainMap() {
        return dynamicInvokeChainMap;
    }

    public PlantUmlContextBean getDerivedPlantUmlContextBean() {
        return derivedPlantUmlContextBean;
    }

    public void setDerivedPlantUmlContextBean(PlantUmlContextBean derivedPlantUmlContextBean) {
        this.derivedPlantUmlContextBean = derivedPlantUmlContextBean;
    }

    public Map<String, PomBean> getPomBeanMap() {
        return pomBeanMap;
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

    public void addClassBean(ClassBean classBean) {
        this.classBeanMap.put(classBean.getClassName(), classBean);
    }

    public void addInterfaceBean(InterfaceBean interfaceBean) {

        this.interfaceBeanMap.put(interfaceBean.getClassName(), interfaceBean);
    }

    public void addPomBean(PomBean pomBean) {
        if(StringUtils.isEmpty(pomBean.getModuleCode())){
            return;
        }
        this.pomBeanMap.put(pomBean.getModuleCode(), pomBean);
    }

    public void addEnumBean(EnumBean enumBean) {
        this.enumBeanMap.put(enumBean.getClassName(), enumBean);
    }


    public List<String> getDynamicInvokeFileList() {
        return dynamicInvokeFileList;
    }

    /**
     * 解析多个调用时序图文件名称
     *
     * @param plantUMLFileName
     */
    public void addDynamicInvokeFile(String plantUMLFileName) {
        if (plantUMLFileName.contains(",")) {
            String[] arr = plantUMLFileName.split(",");
            for (String fileName : arr) {
                dynamicInvokeFileList.add(fileName.trim());
            }
            return;
        }
        dynamicInvokeFileList.add(plantUMLFileName);
    }

    /**
     * 记录调用时序图中的调用方信息
     *
     * @param invokeContextBean
     * @return 是否保存成功
     */
    public boolean addInvokeMethod(InvokeContextBean invokeContextBean) {

        String key = invokeContextBean.getInvokerClassBean().getClassName() + "." + invokeContextBean.getInvokerMethodBean().getMethodName();

        Set<String> providerMethodSet = this.dynamicInvokeChainMap.get(key);
        if (providerMethodSet == null) {
            providerMethodSet = Sets.newHashSet();
        }
        String value = invokeContextBean.getProviderClassName() + invokeContextBean.getProviderClassMethod();

        if (providerMethodSet.contains(value)) {
            return false;
        }
        providerMethodSet.add(value);

        this.getDynamicInvokeChainMap().put(key, providerMethodSet);

        return true;
    }


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public Map<String, ComponentContextBean> getCompContextBeanMap() {
        return compContextBeanMap;
    }

    public void addCompContextBean(String key, ComponentContextBean componentContextBean) {
        this.compContextBeanMap.put(key, componentContextBean);
    }


    public void addCompContextBeanBatch(Map<String, ComponentContextBean> componentContextBeanMap) {
        this.compContextBeanMap.putAll(componentContextBeanMap);
    }

}
