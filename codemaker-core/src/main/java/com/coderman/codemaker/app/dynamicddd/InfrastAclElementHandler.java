package com.coderman.codemaker.app.dynamicddd;

import com.alibaba.fastjson.JSON;
import com.coderman.codemaker.app.ImportPackageService;
import com.coderman.codemaker.bean.dddelement.InfrastAclElementBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.config.ProjectTemplateDynamicDDDConfig;
import com.coderman.codemaker.enums.DomainElementEnum;
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
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "infrastAclElementHandler")
public class InfrastAclElementHandler implements DomainElementHandler<InfrastAclElementBean> {

    @Autowired
    private ProjectTemplateDynamicDDDConfig projectTemplateDynamicDDDConfig;
    @Autowired
    private ImportPackageService importPackageService;

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
                    ClassBean classBean = new ClassBean();
                    classBean.setClassName(className);
                    String packageName = interfaceBean.getPackageName()+"."+"res.dto";
                    classBean.setPackageName(packageName);
                    classBean.setAuthor(interfaceBean.getAuthor());
                    interfaceBean.getImportClassList().add(packageName+"."+className);
                    classBeanList.add(classBean);
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
                        ClassBean classBean = new ClassBean();
                        classBean.setClassName(className);
                        String packageName = interfaceBean.getPackageName()+"."+"req.dto";
                        classBean.setPackageName(packageName);
                        classBean.setAuthor(interfaceBean.getAuthor());
                        interfaceBean.getImportClassList().add(packageName+"."+className);
                        classBeanList.add(classBean);
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
        System.out.println("infrastAclElementBean ="+ JSON.toJSONString(infrastAclElementBean));
        return infrastAclElementBean;
    }


    private String getClassName(String returnClassName){
        return returnClassName.trim().replace("<","")
                .replace(">","")
                .replace("List","")
                .replace("Map","")
                .replace("Set","");
    }
}

