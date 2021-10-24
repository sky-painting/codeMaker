package com.coderman.codemaker.service;

import com.coderman.codemaker.bean.GlobalConstant;
import com.coderman.codemaker.bean.invoke.InvokeContextBean;
import com.coderman.codemaker.bean.plantuml.*;
import com.coderman.codemaker.service.invoker.MethodInvokeHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Description:
 * date: 2021/10/15
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
@Slf4j
public class ReadInvokePlantUMLFileService {

    @Autowired
    private MethodInvokeHandler methodInvokeHandler;

    /**
     * 读取plantUMl文件
     * @param plantUMLFileName
     * @return
     */
    private List<String> readPlantUMLContent(String plantUMLFileName){
        if(StringUtils.isEmpty(plantUMLFileName)){
            log.error("plantUMLFileName is empty,can't read content----------------!!!!!!!!!!");
            return null;
        }
        try {
            File file = ResourceUtils.getFile("classpath:invoke-plantuml/"+plantUMLFileName);
            return FileUtils.readLines(file,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 解析plantUML文件内容
     * @return
     */
    public void parseInvokeChainList( PlantUmlContextBean plantUmlContextBean,String plantUMLFileName){
        List<String> contentList = readPlantUMLContent(plantUMLFileName);
        List<String> elementList = new ArrayList<>();
        for(String str : contentList){
            if(str.trim().contains(GlobalConstant.INVOKE_TAG)){
                elementList.add(str.trim());
            }
        }
        for(String invokeContent : elementList){
            dealInvokeMethodConent(invokeContent,plantUmlContextBean);
        }
    }

    /**
     * 对调用方的method进行动态绘制
     * @param invokeContent
     * @param plantUmlContextBean
     */
    private void dealInvokeMethodConent(String invokeContent, PlantUmlContextBean plantUmlContextBean){
        String [] invokeArr = invokeContent.split(GlobalConstant.INVOKE_TAG);
        AbstractClassBean invokerClassBean = getTargetClass(invokeArr[0],plantUmlContextBean);
        AbstractClassBean providerClassBean = getTargetClass(invokeArr[1],plantUmlContextBean);

        if(invokerClassBean == null || providerClassBean == null){
            log.error("获取调用者类名 或者提供者类名为空,绘制动态调用失败,invokeContent = {}",invokeContent);
            return;
        }

        String invokerMethod = invokeArr[0].toLowerCase().split(invokerClassBean.getClassName().toLowerCase()+".")[1].trim()+"(";

        String providerMethod = invokeArr[1].toLowerCase().split(providerClassBean.getClassName().toLowerCase()+".")[1].trim()+"(";

        Optional<MethodBean> providerMethodBean = providerClassBean.getMethodBeanList().stream().filter(methodBean -> methodBean.getMethodName().toLowerCase().startsWith(providerMethod)).findFirst();


        Optional<MethodBean> invokerMethodBean = invokerClassBean.getMethodBeanList().stream().filter(methodBean -> methodBean.getMethodName().toLowerCase().startsWith(invokerMethod)).findFirst();
        if(!providerMethodBean.isPresent() || !invokerMethodBean.isPresent()){
            log.error("获取调用者方法名 或者提供者方法名为空,绘制动态调用失败,invokeContent = {}",invokeContent);
            return;
        }

        InvokeContextBean invokeBean = new InvokeContextBean();

        if(invokerMethodBean.isPresent()){
            MethodBean methodBean = invokerMethodBean.get();
            if(CollectionUtils.isEmpty(methodBean.getInvokeMethodList())){
                methodBean.initInvokeRowContentList();
            }
            invokeBean.setMethodBean(methodBean);
            invokeBean.setPlantUmlContextBean(plantUmlContextBean);
            invokeBean.setInvokerMethod(invokerMethod);
            invokeBean.setProviderClassName(providerClassBean.getClassName());
            invokeBean.setProviderClassMethod(providerMethodBean.get().getMethodName());
            invokeBean.setProviderClassMethodReturn(providerMethodBean.get().getReturnClass());
            invokeBean.buildInvokeScene(invokerClassBean.getClassName(),providerClassBean.getClassName());
            invokeBean.setInvokerClassBean(invokerClassBean);

            boolean result = plantUmlContextBean.addInvokeMethod(invokeBean);
            if(!result){
                return;
            }
        }
        methodInvokeHandler.dealInvokeContent(invokeBean);





        //注册被调用方的field
        methodInvokeHandler.registField(invokerClassBean, providerClassBean);
        //注册被调用方的方法返回值引用包
        methodInvokeHandler.registImportPackageByClass(invokerClassBean,plantUmlContextBean,providerMethodBean.get().getReturnClass());
        //更新plantumlcontext上下文
        refreshPlantUmlContext(invokerClassBean,plantUmlContextBean);

    }



    /**
     * 从调用描述中获取调用类或者被调用类
     * @param invokerClassContent
     * @param plantUmlContextBean
     * @return
     */
    private AbstractClassBean getTargetClass(String invokerClassContent,PlantUmlContextBean plantUmlContextBean){
        AtomicReference<AbstractClassBean> abstractClassBean = new AtomicReference<>();
        AtomicInteger size = new AtomicInteger(0);
        plantUmlContextBean.getInterfaceBeanMap().forEach((k,v)->{
            v.getMethodBeanList().forEach(methodBean -> {
                String methodInfo = k.toLowerCase()+"."+methodBean.getSimplMethodName().toLowerCase();
                if(invokerClassContent.trim().toLowerCase().endsWith(methodInfo) && methodInfo.length() > size.get()){
                    abstractClassBean.set(v);
                    size.set(methodInfo.length());
                }
            });

        });

        plantUmlContextBean.getClassBeanMap().forEach((k,v)->{
            v.getMethodBeanList().forEach(methodBean -> {
                String methodInfo = k.toLowerCase()+"."+methodBean.getSimplMethodName().toLowerCase();
                if(invokerClassContent.trim().toLowerCase().endsWith(methodInfo) && methodInfo.length() > size.get()){
                    abstractClassBean.set(v);
                    size.set(methodInfo.length());
                }
            });
        });


        if(abstractClassBean.get() != null){
            return abstractClassBean.get();
        }

        if(plantUmlContextBean.getDerivedPlantUmlContextBean() != null){
            plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().forEach((k,v)->{
                v.getMethodBeanList().forEach(methodBean -> {
                    String methodInfo = k.toLowerCase()+"."+methodBean.getSimplMethodName().toLowerCase();
                    if(invokerClassContent.trim().toLowerCase().endsWith(methodInfo) && methodInfo.length() > size.get()){
                        abstractClassBean.set(v);
                        size.set(methodInfo.length());
                    }
                });
            });

            plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k,v)->{
                v.getMethodBeanList().forEach(methodBean -> {
                    String methodInfo = k.toLowerCase()+"."+methodBean.getSimplMethodName().toLowerCase();
                    if(invokerClassContent.trim().toLowerCase().endsWith(methodInfo) && methodInfo.length() > size.get()){
                        abstractClassBean.set(v);
                        size.set(methodInfo.length());
                    }
                });
            });
        }
        if(abstractClassBean.get() != null){
            return abstractClassBean.get();
        }

        return null;

    }


    /**
     * 更新plantUMlcontext上下文
     * @param invokerClassBean
     * @param plantUmlContextBean
     */
    private void refreshPlantUmlContext(AbstractClassBean invokerClassBean,PlantUmlContextBean plantUmlContextBean){
        InterfaceBean oldInterface = plantUmlContextBean.getInterfaceBeanMap().get(invokerClassBean.getClassName());
        if(oldInterface != null){
            oldInterface.setFieldBeanList(invokerClassBean.getFieldBeanList());
            oldInterface.setMethodBeanList(invokerClassBean.getMethodBeanList());
            plantUmlContextBean.getInterfaceBeanMap().put(oldInterface.getClassName(),oldInterface);
            return;
        }
        ClassBean oldClass = plantUmlContextBean.getClassBeanMap().get(invokerClassBean.getClassName());
        if(oldClass != null){
            oldClass.setFieldBeanList(invokerClassBean.getFieldBeanList());
            oldClass.setMethodBeanList(invokerClassBean.getMethodBeanList());
            oldClass.mergeImportClass(invokerClassBean.getDynamicImportPackageList());
            plantUmlContextBean.getClassBeanMap().put(oldClass.getClassName(),oldClass);
            return;
        }


        ClassBean oldDervidClass = plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().get(invokerClassBean.getClassName());
        if(oldDervidClass != null){
            oldDervidClass.setFieldBeanList(invokerClassBean.getFieldBeanList());
            oldDervidClass.setMethodBeanList(invokerClassBean.getMethodBeanList());
            oldDervidClass.mergeImportClass(invokerClassBean.getDynamicImportPackageList());
            plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().put(oldDervidClass.getClassName(),oldDervidClass);
            return;
        }

        InterfaceBean oldDervidInterface = plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().get(invokerClassBean.getClassName());
        if(oldDervidInterface != null){
            oldDervidInterface.setFieldBeanList(invokerClassBean.getFieldBeanList());
            oldDervidInterface.setMethodBeanList(invokerClassBean.getMethodBeanList());
            plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().put(oldDervidInterface.getClassName(),oldDervidInterface);
            return;
        }

    }

}
