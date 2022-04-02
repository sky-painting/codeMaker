package com.tianhua.codemaker.component.regist;

import com.tianhua.codemaker.api.ICompRegistService;
import com.tianhua.codemaker.bean.GlobalConstant;
import com.tianhua.codemaker.bean.component.ComponentConfigBean;
import com.tianhua.codemaker.bean.component.ComponentContextBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.bean.plantuml.MethodBean;
import com.tianhua.codemaker.component.CompPropReadService;
import com.tianhua.codemaker.enums.ClassEnum;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/12/24
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "compSingleClassRegistServiceImpl")
public class CompSingleClassRegistServiceImpl implements ICompRegistService {
    @Autowired
    private ResourceLoader resourceLoader;


    @Autowired
    private CompPropReadService compPropReadService;


    @Override
    public ComponentContextBean registSingleClass() {
        String path = GlobalConstant.COMP_COMPONENT_SINGLE_CLASS_PATH;
        Resource resource = resourceLoader.getResource("classpath:"+path);

        try {
            File singleClassCompFile = resource.getFile();
            ComponentContextBean componentContextBean = new ComponentContextBean();
            List<ClassBean> classBeanList = new ArrayList<>();
            List<InterfaceBean> interfaceBeanList = new ArrayList<>();

            for (File file : singleClassCompFile.listFiles()){
                ComponentConfigBean componentConfigBean = compPropReadService.buildFromProp(path+"/"+file.getName());
                if(componentConfigBean == null){
                    continue;
                }
                if(ClassEnum.CLASS.getClassType().equals(componentConfigBean.getClassType())){
                    classBeanList.add(buildClassBean(componentConfigBean, file.getName().split("\\.")[0]));
                }
                else if(ClassEnum.INTERFACE.getClassType().equals(componentConfigBean.getClassType())){
                    interfaceBeanList.add(buildInterfaceBean(componentConfigBean, file.getName().split("\\.")[0]));
                }
            }
            componentContextBean.setClassBeanList(classBeanList);
            componentContextBean.setInterfaceBeanList(interfaceBeanList);
            return componentContextBean;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private ClassBean buildClassBean(ComponentConfigBean componentConfigBean,String className){
        ClassBean classBean = new ClassBean();
        classBean.setPackageName(componentConfigBean.getPackageName());
        classBean.setClassName(className);
        classBean.setCompName(GlobalConstant.SINGLE_CLASS_COMMON);
        classBean.setMethodBeanList(buildMethodBeanList(componentConfigBean.getMethodMap().get(className)));

        return classBean;
    }


    private InterfaceBean buildInterfaceBean(ComponentConfigBean componentConfigBean,String className){
        InterfaceBean interfaceBean = new InterfaceBean();
        interfaceBean.setPackageName(componentConfigBean.getPackageName());
        interfaceBean.setClassName(className);
        interfaceBean.setCompName(GlobalConstant.SINGLE_CLASS_COMMON);

        interfaceBean.setMethodBeanList(buildMethodBeanList(componentConfigBean.getMethodMap().get(className)));
        return interfaceBean;
    }

    /**
     * 构建方法列表
     * @param methodStrList
     * @return
     */
    private List<MethodBean> buildMethodBeanList(List<String> methodStrList){
        if(CollectionUtils.isEmpty(methodStrList)){
            return Lists.newArrayList();
        }
        List<MethodBean> methodBeanList = new ArrayList<>();
        for (String method : methodStrList){
            MethodBean methodBean = new MethodBean();
            if(method.contains("void")){
                methodBean.setReturnClass("void");
            }else {
                String returnClass = method.replace("static","").trim().split(" ")[0];
                methodBean.setReturnClass(returnClass);
            }
            methodBean.setStatic(method.contains("static"));
            String [] methodArr = method.split(" ");
            for (String str : methodArr){
                if(str.contains("(")){
                    String methodName = str.split("\\(")[0];
                    methodBean.setMethodName(methodName+method.substring(method.indexOf("(")));
                    methodBean.buildParamArr();
                }
            }
            methodBeanList.add(methodBean);
        }
        return methodBeanList;
    }


}
