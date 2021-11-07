package com.coderman.codemaker.service.plantuml;

import com.coderman.codemaker.bean.GlobalConstant;
import com.coderman.codemaker.bean.invoke.InvokeContextBean;
import com.coderman.codemaker.bean.plantuml.*;
import com.coderman.codemaker.service.invoker.MethodFactoryService;
import com.coderman.codemaker.service.invoker.MethodInvokeService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
public class ReadInvokeSequencePlantDocService {

    @Autowired
    private MethodInvokeService methodInvokeHandler;

    @Autowired
    private ReadPlantUMLDocService readPlantUMLDocService;

    @Autowired
    private MethodFactoryService methodFactoryService;

    /**
     * 解析plantUML文件内容
     *
     * @return
     */
    public void parseInvokeChainList(PlantUmlContextBean plantUmlContextBean, String plantUMLFileName) {
        List<String> contentList = readPlantUMLDocService.readInvokeSequencePlantDoc(plantUMLFileName);
        List<String> elementList = new ArrayList<>();
        for (String str : contentList) {
            if (str.trim().contains(GlobalConstant.INVOKE_TAG)) {
                elementList.add(str.trim());
            }
        }
        for (String invokeContent : elementList) {
            dealInvokeMethodConent(invokeContent, plantUmlContextBean);
        }
    }

    /**
     * 对调用方的method进行动态绘制
     *
     * @param invokeContent
     * @param plantUmlContextBean
     */
    private void dealInvokeMethodConent(String invokeContent, PlantUmlContextBean plantUmlContextBean) {
        String[] invokeArr = invokeContent.split(GlobalConstant.INVOKE_TAG);
        AbstractClassBean invokerClassBean = getTargetClass(invokeArr[0], plantUmlContextBean);
        AbstractClassBean providerClassBean = getTargetClass(invokeArr[1], plantUmlContextBean);

        if (invokerClassBean == null || providerClassBean == null) {
            log.error("获取调用者类名 或者提供者类名为空,绘制动态调用失败,invokeContent = {}", invokeContent);
            return;
        }

        if(org.apache.commons.collections4.CollectionUtils.isEmpty(invokerClassBean.getDynamicImportPackageList())){
            invokerClassBean.setDynamicImportPackageList(Lists.newArrayList());
        }

        String invokerMethodStr = invokeArr[0].split(invokerClassBean.getClassName() + ".")[1].trim();

        String providerMethodStr = invokeArr[1].split(providerClassBean.getClassName() + ".")[1].trim();


        //如果被调用方的方法不在plantuml领域文档中，则根据方法名智能推导出一个方法走下面的流程
        MethodBean providerMethodBean = methodFactoryService.buildDynamicMethod(plantUmlContextBean,providerClassBean,providerMethodStr);
        MethodBean invokerMethodBean = invokerClassBean.getOrAddMethodBean(invokerMethodStr);
        InvokeContextBean invokeBean = new InvokeContextBean();
        invokeBean.setInvokerMethodBean(invokerMethodBean);
        invokeBean.setPlantUmlContextBean(plantUmlContextBean);
        invokeBean.setInvokerMethod(invokerMethodStr);
        invokeBean.setProviderClassName(providerClassBean.getClassName());
        invokeBean.setProviderClassMethod(providerMethodBean);
        invokeBean.buildInvokeScene(invokerClassBean.getClassName(), providerClassBean.getClassName());
        invokeBean.setInvokerClassBean(invokerClassBean);

        boolean result = plantUmlContextBean.addInvokeMethod(invokeBean);
        if (!result) {
            return;
        }

        //处理动态调用内容
        methodInvokeHandler.dealInvokeContent(invokeBean);

        //注册被调用方的field
        methodInvokeHandler.registField(invokerClassBean, providerClassBean);
        //注册被调用方的方法返回值引用包
        methodInvokeHandler.registImportPackageByClass(invokerClassBean, plantUmlContextBean, providerMethodBean.getReturnClass());
        //更新plantumlcontext上下文
        refreshPlantUmlContext(invokerClassBean, plantUmlContextBean);

    }


    /**
     * 从调用描述中获取调用类或者被调用类
     *
     * @param invokerClassContent
     * @param plantUmlContextBean
     * @return
     */
    private AbstractClassBean getTargetClass(String invokerClassContent, PlantUmlContextBean plantUmlContextBean) {
        AtomicReference<AbstractClassBean> abstractClassBean = new AtomicReference<>();
        AtomicInteger size = new AtomicInteger(0);

        String [] contentArr = invokerClassContent.split("\\.");

        plantUmlContextBean.getInterfaceBeanMap().forEach((k, v) -> {
            if(v.checkMatchClass(contentArr) && v.getClassName().length() > size.get()){
                abstractClassBean.set(v);
                size.set(v.getClassName().length());
            }
        });

        plantUmlContextBean.getClassBeanMap().forEach((k, v) -> {
            if(v.checkMatchClass(contentArr) && v.getClassName().length() > size.get()){
                abstractClassBean.set(v);
                size.set(v.getClassName().length());
            }
        });


        if (abstractClassBean.get() != null) {
            return abstractClassBean.get();
        }

        if (plantUmlContextBean.getDerivedPlantUmlContextBean() != null) {
            plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().forEach((k, v) -> {
                if(v.checkMatchClass(contentArr) && v.getClassName().length() > size.get()){
                    abstractClassBean.set(v);
                    size.set(v.getClassName().length());
                }
            });

            plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k, v) -> {
                if(v.checkMatchClass(contentArr) && v.getClassName().length() > size.get()){
                    abstractClassBean.set(v);
                    size.set(v.getClassName().length());
                }
            });
        }
        if (abstractClassBean.get() != null) {
            return abstractClassBean.get();
        }

        return null;

    }




    /**
     * 更新plantUMlcontext上下文
     *
     * @param invokerClassBean
     * @param plantUmlContextBean
     */
    private void refreshPlantUmlContext(AbstractClassBean invokerClassBean, PlantUmlContextBean plantUmlContextBean) {
        InterfaceBean oldInterface = plantUmlContextBean.getInterfaceBeanMap().get(invokerClassBean.getClassName());
        if (oldInterface != null) {
            oldInterface.setFieldBeanList(invokerClassBean.getFieldBeanList());
            oldInterface.setMethodBeanList(invokerClassBean.getMethodBeanList());
            plantUmlContextBean.getInterfaceBeanMap().put(oldInterface.getClassName(), oldInterface);
            return;
        }
        ClassBean oldClass = plantUmlContextBean.getClassBeanMap().get(invokerClassBean.getClassName());
        if (oldClass != null) {
            oldClass.setFieldBeanList(invokerClassBean.getFieldBeanList());
            oldClass.setMethodBeanList(invokerClassBean.getMethodBeanList());
            oldClass.mergeImportClass(invokerClassBean.getDynamicImportPackageList());
            plantUmlContextBean.getClassBeanMap().put(oldClass.getClassName(), oldClass);
            return;
        }


        ClassBean oldDervidClass = plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().get(invokerClassBean.getClassName());
        if (oldDervidClass != null) {
            oldDervidClass.setFieldBeanList(invokerClassBean.getFieldBeanList());
            oldDervidClass.setMethodBeanList(invokerClassBean.getMethodBeanList());
            oldDervidClass.mergeImportClass(invokerClassBean.getDynamicImportPackageList());
            plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().put(oldDervidClass.getClassName(), oldDervidClass);
            return;
        }

        InterfaceBean oldDervidInterface = plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().get(invokerClassBean.getClassName());
        if (oldDervidInterface != null) {
            oldDervidInterface.setFieldBeanList(invokerClassBean.getFieldBeanList());
            oldDervidInterface.setMethodBeanList(invokerClassBean.getMethodBeanList());
            plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().put(oldDervidInterface.getClassName(), oldDervidInterface);
            return;
        }

    }

}
