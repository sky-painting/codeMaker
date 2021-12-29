package com.coderman.codemaker.factory;

import com.coderman.codemaker.bean.component.ComponentContextBean;
import com.coderman.codemaker.bean.plantuml.AbstractClassBean;
import com.coderman.codemaker.bean.plantuml.AnnotationBean;
import com.coderman.codemaker.constant.ArchConstant;
import com.coderman.codemaker.constant.ArchSpringConstant;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * date: 2021/12/13
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class SpringClassBeanFactory {

    /**
     * 包装spring框架需要的所有注解
     * @param componentContextBean
     * @return
     */
    public Map<String, AbstractClassBean> buildSpringArchBeanMap(ComponentContextBean componentContextBean){
        Map<String,AbstractClassBean> controllerClassBeanMap = new HashMap<>();

        AnnotationBean restControllerBean = componentContextBean.getTargetAnnotation(ArchSpringConstant.ANNO_SPRING_REST_CONTROLLER);
        AnnotationBean getMappingBean = componentContextBean.getTargetAnnotation(ArchSpringConstant.ANNO_SPRING_GET_MAPPING);
        AnnotationBean postMappingBean = componentContextBean.getTargetAnnotation(ArchSpringConstant.ANNO_SPRING_POST_MAPPING);
        AnnotationBean requestBodyBean = componentContextBean.getTargetAnnotation(ArchSpringConstant.ANNO_SPRING_REQUEST_BODY);
        AnnotationBean requestMappingBean = componentContextBean.getTargetAnnotation(ArchSpringConstant.ANNO_SPRING_REQUEST_MAPPING);
        AnnotationBean requestParamBean = componentContextBean.getTargetAnnotation(ArchSpringConstant.ANNO_SPRING_REQUEST_PARAM);
        AnnotationBean feignClientBean = componentContextBean.getTargetAnnotation(ArchSpringConstant.ANNO_FEIGN_CLIENT);

        controllerClassBeanMap.put(ArchSpringConstant.ANNO_SPRING_REST_CONTROLLER, restControllerBean);
        controllerClassBeanMap.put(ArchSpringConstant.ANNO_SPRING_GET_MAPPING, getMappingBean);
        controllerClassBeanMap.put(ArchSpringConstant.ANNO_SPRING_POST_MAPPING, postMappingBean);
        controllerClassBeanMap.put(ArchSpringConstant.ANNO_SPRING_REQUEST_BODY, requestBodyBean);
        controllerClassBeanMap.put(ArchSpringConstant.ANNO_SPRING_REQUEST_MAPPING, requestMappingBean);
        controllerClassBeanMap.put(ArchSpringConstant.ANNO_SPRING_REQUEST_PARAM, requestParamBean);
        controllerClassBeanMap.put(ArchSpringConstant.ANNO_FEIGN_CLIENT, feignClientBean);


        return  controllerClassBeanMap;
    }
}
