package com.coderman.codemaker.component.decorate;

import com.coderman.codemaker.api.ICompDecorateService;
import com.coderman.codemaker.bean.component.ComponentContextBean;
import com.coderman.codemaker.bean.plantuml.*;
import com.coderman.codemaker.constant.ArchConstant;
import com.coderman.codemaker.element.ElementDecorateHandler;
import com.coderman.codemaker.factory.SpringClassBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:这里提供对springboot,springmvc,dubbo框架的代码元素包装包括接口协议的包装
 * 是codemaker本身默认提供的包装，如果是自研框架依然可以定制适配
 *
 * date: 2021/11/23
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "defaultCompDecorateService")
public class DefaultCompDecorateServiceImpl implements ICompDecorateService {

    @Resource(name = "eleControllerDecorateHandler")
    private ElementDecorateHandler eleControllerDecorateHandler;

    @Resource(name = "eleFacadeDecorateHandler")
    private ElementDecorateHandler eleFacadeDecorateHandler;

    @Resource(name = "eleFeignDecorateHandler")
    private ElementDecorateHandler eleFeignDecorateHandler;

    @Autowired
    private SpringClassBeanFactory springClassBeanFactory;

    @Override
    public void decorateComp(ComponentContextBean componentContextBean, PlantUmlContextBean plantUmlContextBean) {
        //只包装框架需要的注解class等信息
        Map<String,AbstractClassBean> controllerClassBeanMap = springClassBeanFactory.buildSpringArchBeanMap(componentContextBean);

        eleControllerDecorateHandler.decorateElement(controllerClassBeanMap, plantUmlContextBean);

        AnnotationBean dubboServiceBean = componentContextBean.getTargetAnnotation(ArchConstant.ANNO_DUBBO_SERVICE);

        Map<String,AbstractClassBean> facadeClassBeanMap = new HashMap<>();
        facadeClassBeanMap.put(ArchConstant.ANNO_DUBBO_SERVICE, dubboServiceBean);
        eleFacadeDecorateHandler.decorateElement(facadeClassBeanMap, plantUmlContextBean);

        eleFeignDecorateHandler.decorateElement(controllerClassBeanMap, plantUmlContextBean);
    }

}
