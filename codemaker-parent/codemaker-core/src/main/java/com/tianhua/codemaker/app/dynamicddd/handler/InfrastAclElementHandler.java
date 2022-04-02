package com.tianhua.codemaker.app.dynamicddd.handler;

import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelement.InfrastAclElementBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.bean.plantuml.MethodBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.DomainElementEnum;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Description:
 * date: 2021/6/29
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "infrastAclElementHandler")
public class InfrastAclElementHandler implements DomainElementHandler<InfrastAclElementBean> {


    @Autowired
    private ImportPackageServiceImpl importPackageService;

    @Override
    public InfrastAclElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        InfrastAclElementBean infrastAclElementBean = new InfrastAclElementBean();
        List<InterfaceBean> interfaceBeanList = new ArrayList<>();

        plantUmlContextBean.getInterfaceBeanMap().forEach((k,v)->{
            if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.ACL.getElement())){
                importPackageService.setPackageName(v,"domain.acl");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                interfaceBeanList.add(v);
            }
            else if(v.getClassName().toLowerCase().endsWith(DomainElementEnum.ADAPTER.getElement())
                    || v.getClassName().toLowerCase().endsWith(DomainElementEnum.ADAPTER_SERVICE.getElement())){
                importPackageService.setPackageName(v,"domain.adapter");
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                interfaceBeanList.add(v);
            }
        });

        interfaceBeanList.stream().forEach(v-> importPackageService.dealImportClass(v,plantUmlContextBean));

        List<ClassBean> classBeanList = new ArrayList<>();
        interfaceBeanList.stream().forEach(interfaceBean -> {
            List<MethodBean> methodBeanList = interfaceBean.getMethodBeanList();
            for (MethodBean methodBean : methodBeanList){
                //对返回值进行检测
                if (methodBean.getReturnClass().toLowerCase().contains("dto")){
                    String className = getClassName(methodBean.getReturnClass());
                    String packageName = interfaceBean.getPackageName()+"."+"res.dto";

                    registAclClassBean(className,packageName,plantUmlContextBean);
                    ClassBean newClassBean = new ClassBean();
                    newClassBean.setClassName(className);
                    newClassBean.setAuthor(interfaceBean.getAuthor());
                    newClassBean.setPackageName(packageName);
                    newClassBean.setAuthor(interfaceBean.getAuthor());

                    interfaceBean.getImportClassList().add(packageName+"."+className);
                    classBeanList.add(newClassBean);
                }
                if(methodBean.getMethodName().contains("()")){
                    continue;
                }
                String params = methodBean.getMethodName().substring(methodBean.getMethodName().indexOf("(")+1,methodBean.getMethodName().length() - 1);
                if(StringUtils.isEmpty(params.trim())){
                    continue;
                }
                String[] arr = params.split(",");
                for (String param : arr){
                    if(param.toLowerCase().contains("dto")){
                        String className = param.trim().split(" ")[0];
                        String packageName = interfaceBean.getPackageName()+"."+"req.dto";

                        registAclClassBean(className,packageName,plantUmlContextBean);
                        ClassBean newClassBean = new ClassBean();
                        newClassBean.setClassName(className);
                        newClassBean.setFieldBeanList(new ArrayList<>());
                        newClassBean.setMethodBeanList(new ArrayList<>());

                        newClassBean.setPackageName(packageName);
                        newClassBean.setAuthor(interfaceBean.getAuthor());
                        interfaceBean.getImportClassList().add(packageName+"."+className);
                        classBeanList.add(newClassBean);
                    }
                }
            }
        });

        interfaceBeanList.stream().forEach(interfaceBean -> {
            Set<String> importSet = Sets.newHashSet(interfaceBean.getImportClassList());
            interfaceBean.setImportClassList(Lists.newArrayList(importSet));
        });

        infrastAclElementBean.setInterfaceBeanList(interfaceBeanList);
        infrastAclElementBean.setClassBeanList(classBeanList);
        return infrastAclElementBean;
    }


    private String getClassName(String returnClassName){
        return returnClassName.trim().replace("<","")
                .replace(">","")
                .replace("List","")
                .replace("Map","")
                .replace("Set","");
    }

    /**
     * 根据infrast.acl包中的接口方法参数和返回值获取导出的依赖classBean,注册到plantUML上下文中
     * @param className
     * @param packageName
     * @param plantUmlContextBean
     * @return
     */
    private void registAclClassBean(String className, String packageName, PlantUmlContextBean plantUmlContextBean){
        String newClassName = className + TemplateFileEnum.ACL.getTempFileName();
        ClassBean classBean = plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().get(newClassName);
        if(classBean == null){
            classBean = new ClassBean();
            classBean.setClassName(newClassName);
            classBean.setMethodBeanList(new ArrayList<>());
            classBean.setFieldBeanList(new ArrayList<>());
        }
        classBean.setPackageName(packageName);
        plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().put(newClassName,classBean);


    }

}

