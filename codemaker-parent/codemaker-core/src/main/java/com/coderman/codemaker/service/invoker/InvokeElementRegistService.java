package com.coderman.codemaker.service.invoker;

import com.coderman.codemaker.api.ICompRegistService;
import com.coderman.codemaker.bean.component.ComponentContextBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.config.AppServiceConfig;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private ICompRegistService iCompRegistService;

    /**
     * 注册独立类和工具
     * @param plantUmlContextBean
     */
    public void registDefaultClass(PlantUmlContextBean plantUmlContextBean){
        ComponentContextBean componentContextBean = iCompRegistService.registSingleClass();


        plantUmlContextBean.getCompContextBeanMap().put("singleClass",componentContextBean);


    }

    /**
     * 注册事件发布器
     * @param plantUmlContextBean
     */
    /*private void registAppEventPublisher(PlantUmlContextBean plantUmlContextBean){
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
*/
    /**
     * 注册bo类对象
     * @param plantUmlContextBean
     */
    /*public void registPageBO(PlantUmlContextBean plantUmlContextBean){
        ClassBean classBean = new ClassBean();
        classBean.setClassDesc("分页bo对象");
        classBean.setClassName("PageBO");
        classBean.setDerived(false);
        classBean.setPackageName("com.coderman.utils.commonbo");

        plantUmlContextBean.addClassBean(classBean);
    }*/



}
