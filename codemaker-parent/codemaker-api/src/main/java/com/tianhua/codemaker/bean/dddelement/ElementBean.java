package com.tianhua.codemaker.bean.dddelement;

import com.tianhua.codemaker.bean.config.FtlBean;
import com.tianhua.codemaker.bean.config.PomBean;
import com.tianhua.codemaker.bean.plantuml.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/6/29
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public abstract class ElementBean {
    protected String packageName;
    protected List<ClassBean> classBeanList;
    protected List<InterfaceBean> interfaceBeanList;
    protected List<EnumBean> enumBeanList;
    protected List<PomBean> pomBeanList;

    /**
     * 模块名称
     */
    protected String moduleName;


    /**
     * 自定义ftl模板配置bean
     */
    protected FtlBean ftlBean;



    public FtlBean getFtlBean() {
        return ftlBean;
    }

    public void setFtlBean(FtlBean ftlBean) {
        this.ftlBean = ftlBean;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<PomBean> getPomBeanList() {
        return pomBeanList;
    }

    public void setPomBeanList(List<PomBean> pomBeanList) {
        this.pomBeanList = pomBeanList;
    }

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


    /**
     * 动态调用执行之后再次更新代码元素内容
     * @param plantUmlContextBean
     * @param elementType
     */
    public ElementBean  refreshClass(PlantUmlContextBean plantUmlContextBean,String elementType){
        List<ClassBean> newClassBeanList = new ArrayList<>();
        plantUmlContextBean.getClassBeanMap().forEach((k,v)->{
            if(elementType.contains(",")){
                String [] elementArr = elementType.split(",");
                for (String str : elementArr){
                    if(v.getClassName().toLowerCase().endsWith(str)){
                        newClassBeanList.add(v);
                        break;
                    }
                }
            }else{
                if(v.getClassName().toLowerCase().endsWith(elementType)){
                    newClassBeanList.add(v);
                }
            }

        });
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() != null){
            plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k,v)->{
                if(v.getClassName().toLowerCase().endsWith(elementType)){
                    newClassBeanList.add(v);
                }
            });
        }


        for (ClassBean classBean : newClassBeanList){
            if(!classBean.getClassName().endsWith("Impl")){
                List<MethodBean> methodBeanList = new ArrayList<>();
                if(CollectionUtils.isEmpty(classBean.getMethodBeanList())){
                    continue;
                }
                for (MethodBean oldBean : classBean.getMethodBeanList()){
                    StringBuilder contentBuilder = new StringBuilder("");
                    if(!CollectionUtils.isEmpty(oldBean.getInvokeMethodList())){
                        for (String str : oldBean.getInvokeMethodList()){
                            contentBuilder.append("        "+str+";\n");
                        }
                    }
                    methodBeanList.add(oldBean.copySelf(contentBuilder.toString()));
                }
                classBean.setMethodBeanList(methodBeanList);
                continue;
            }
            String interfaceName = classBean.getClassName().replace("Impl","");
            InterfaceBean interfaceBean = plantUmlContextBean.getInterfaceBeanMap().get(interfaceName);
            if(interfaceBean == null){
                interfaceBean = plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().get(interfaceName);
            }
            if(interfaceBean == null){
                continue;
            }
            //构建方法内容
            interfaceBean.getMethodBeanList().forEach(methodBean -> methodBean.buildMethodContent());
            classBean.mergeImportClass(interfaceBean.getImportClassList());
            classBean.mergeImportClass(interfaceBean.getDynamicImportPackageList());
            classBean.setMethodBeanList(interfaceBean.getMethodBeanList());
            classBean.setFieldBeanList(interfaceBean.getFieldBeanList());
        }

        this.setClassBeanList(newClassBeanList);
        return this;
    }

    /**
     * 动态调用执行之后再次更新代码元素内容
     * @param plantUmlContextBean
     * @param elementType
     */
    public ElementBean  refreshInterface(PlantUmlContextBean plantUmlContextBean,String elementType){
        List<InterfaceBean> newInterfaceBeanList = new ArrayList<>();
        plantUmlContextBean.getInterfaceBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(elementType)){
                v.getMethodBeanList().forEach(methodBean -> methodBean.refreshMethodName());
                newInterfaceBeanList.add(v);
            }
        });
        if(plantUmlContextBean.getDerivedPlantUmlContextBean() != null){
            plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().forEach((k,v)->{
                if(v.getClassName().toLowerCase().endsWith(elementType)){
                    v.getMethodBeanList().forEach(methodBean -> methodBean.refreshMethodName());
                    newInterfaceBeanList.add(v);
                }
            });
        }


        this.setInterfaceBeanList(newInterfaceBeanList);
        return this;
    }


}
