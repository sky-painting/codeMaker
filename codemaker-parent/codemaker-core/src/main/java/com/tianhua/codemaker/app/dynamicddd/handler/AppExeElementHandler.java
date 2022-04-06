package com.tianhua.codemaker.app.dynamicddd.handler;

import com.tianhua.codemaker.service.packageimport.ImportPackageServiceImpl;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.bean.dddelement.ExecutorElementBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.bean.plantuml.MethodBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.DomainElementEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@Component(value = "appExeElementHandler")
public class AppExeElementHandler implements DomainElementHandler<ExecutorElementBean> {
    @Autowired
    private ImportPackageServiceImpl importPackageService;

    @Override
    public ExecutorElementBean getElementBeanList(PlantUmlContextBean plantUmlContextBean) {
        ExecutorElementBean executorElementBean = new ExecutorElementBean();
        List<InterfaceBean> interfaceBeanList = new ArrayList<>();
        List<ClassBean> classBeanList = new ArrayList<>();

        plantUmlContextBean.getInterfaceBeanMap().forEach((k,v)->{
            if(classFilter(v.getClassName())){
                importPackageService.setPackageName(v,"app.executor");

                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                importPackageService.dealImportClass(v,plantUmlContextBean);
                v.getMethodBeanList().forEach(methodBean -> methodBean.buildDoc());
                interfaceBeanList.add(v);
            }
        });
        plantUmlContextBean.getClassBeanMap().forEach((k,v)->{
            if(classFilter(v.getClassName())){
                importPackageService.setPackageName(v,"app.executor");
                addExeMethod(v,plantUmlContextBean);
                String className = v.getClassName().substring(0,1).toUpperCase().concat(v.getClassName().substring(1));
                v.setClassName(className);
                importPackageService.dealImportClass(v,plantUmlContextBean);
                List<MethodBean> methodBeanList = new ArrayList<>();
                v.getMethodBeanList().forEach(methodBean -> {
                    methodBean.buildDoc();
                    methodBeanList.add(methodBean.copySelf(null));
                });
                v.setMethodBeanList(methodBeanList);
                classBeanList.add(v);
            }
        });
        executorElementBean.setClassBeanList(classBeanList);
        executorElementBean.setInterfaceBeanList(interfaceBeanList);
        return executorElementBean;
    }

    /**
     * 类过滤
     * @param className
     * @return
     */
    private boolean classFilter(String className){
        String[] cmdArr = DomainElementEnum.EXECUTOR.getElement().split(",");
        for (String cmd : cmdArr){
            if (className.toLowerCase().endsWith(cmd)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 如果exe实现了抽象的exe接口，则对exe实现类进行检测
     * @param classBean
     * @param plantUmlContextBean
     */
    private void addExeMethod(ClassBean classBean, PlantUmlContextBean plantUmlContextBean){
        //如果没有实现类则直接返回
        if(StringUtils.isEmpty(classBean.getRelationClassStr())){
           return;
        }

        if(classBean.getRelationClassStr().contains("implements")){
            String implClass = classBean.getRelationClassStr().replace("implements","").trim();
            InterfaceBean interfaceBean = plantUmlContextBean.getInterfaceBeanMap().get(implClass);
            if(interfaceBean == null  || CollectionUtils.isEmpty(interfaceBean.getMethodBeanList())){
                return;
            }

            if(CollectionUtils.isEmpty(classBean.getMethodBeanList())){
                classBean.setMethodBeanList(interfaceBean.getMethodBeanList());
                return;
            }

            dealMethod(classBean,interfaceBean.getMethodBeanList());

        }else if(classBean.getRelationClassStr().contains("extends")){
            String extendsClass = classBean.getRelationClassStr().replace("extends","").trim();
            ClassBean superClassBean = plantUmlContextBean.getClassBeanMap().get(extendsClass);
            if(superClassBean == null  || CollectionUtils.isEmpty(superClassBean.getMethodBeanList())){
                return;
            }

            if(CollectionUtils.isEmpty(classBean.getMethodBeanList())){
                classBean.setMethodBeanList(superClassBean.getMethodBeanList());
                return;
            }

            dealMethod(classBean,superClassBean.getMethodBeanList());
        }
    }

    /**
     * 处理继承和实现的方法
     * @param classBean
     * @param superMethodList
     */
    private void dealMethod(ClassBean classBean, List<MethodBean> superMethodList){
        List<MethodBean> implMethodList = new ArrayList<>();
        for (MethodBean interfaceMethod : superMethodList){
            boolean implMethod = false;
            for (MethodBean classMethod : classBean.getMethodBeanList()){
                if(classMethod.getMethodName().toLowerCase().trim().equals(interfaceMethod.getMethodName().toLowerCase().trim())
                        && classMethod.getReturnClass().equals(interfaceMethod.getReturnClass())){
                    implMethod = true;
                }
            }
            if(!implMethod){
                implMethodList.add(interfaceMethod);
            }
        }
        List<MethodBean> methodBeanList = classBean.getMethodBeanList();
        methodBeanList.addAll(implMethodList);
        classBean.setMethodBeanList(methodBeanList);
    }


}
