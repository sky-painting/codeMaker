package com.coderman.codemaker.service.invoker;

import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.config.AppServiceConfig;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * Description:预先读取plantUML调用时序图文档，以及补充其他内置的工具类
 * 如BaseEvent,AppEventPublisher类等
 * date: 2021/10/25
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class InvokeElementRegistService {

    @Autowired
    private AppServiceConfig appServiceConfig;

    /**
     * 注册内置的工具类对象或者辅助对象
     * @param plantUmlContextBean
     */
    public void registDefaultClass(PlantUmlContextBean plantUmlContextBean){
        registBaseEvent(plantUmlContextBean);
        registAppEventPublisher(plantUmlContextBean);
        registPageBO(plantUmlContextBean);
    }

    /**
     * 注册事件基类
     * @param plantUmlContextBean
     */
    private void registBaseEvent(PlantUmlContextBean plantUmlContextBean){
        ClassBean classBean = new ClassBean();
        classBean.setClassDesc("事件基类");
        classBean.setClassName("BaseEvent");
        classBean.setDerived(false);
        classBean.setPackageName(appServiceConfig.getPackage()+".domain.event");
        classBean.setPlantUMLPackage("domain.event");
        classBean.setMethodBeanList(Lists.newArrayList());
        plantUmlContextBean.addClassBean(classBean);
    }

    /**
     * 注册事件发布器
     * @param plantUmlContextBean
     */
    private void registAppEventPublisher(PlantUmlContextBean plantUmlContextBean){
        ClassBean classBean = new ClassBean();
        classBean.setClassDesc("事件发布器");
        classBean.setClassName("AppEventPublisher");
        classBean.setDerived(false);
        classBean.setPackageName(appServiceConfig.getPackage()+".utils");
        classBean.setPlantUMLPackage("utils");
        MethodBean methodBean = new MethodBean();
        methodBean.setReturnBody("void");
        methodBean.setMethodName("publish(T t)");
        methodBean.setStatic(false);
        methodBean.setVisibility("public");
        methodBean.setDesc("发布事件入口");
        methodBean.setClassName("AppEventPublisher");
        classBean.setMethodBeanList(Lists.newArrayList(methodBean));

        plantUmlContextBean.addClassBean(classBean);

    }

    /**
     * 注册bo类对象
     * @param plantUmlContextBean
     */
    public void registPageBO(PlantUmlContextBean plantUmlContextBean){
        ClassBean classBean = new ClassBean();
        classBean.setClassDesc("分页bo对象");
        classBean.setClassName("PageBO");
        classBean.setDerived(false);
        classBean.setPackageName("com.coderman.utils.commonbo");






        plantUmlContextBean.addClassBean(classBean);
    }



}
