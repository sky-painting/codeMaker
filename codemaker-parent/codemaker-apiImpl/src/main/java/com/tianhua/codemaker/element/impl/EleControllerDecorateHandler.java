package com.tianhua.codemaker.element.impl;

import com.tianhua.codemaker.bean.plantuml.*;
import com.tianhua.codemaker.constant.ApiConstant;
import com.tianhua.codemaker.constant.ArchConstant;
import com.tianhua.codemaker.constant.ArchSpringConstant;
import com.tianhua.codemaker.element.ElementDecorateHandler;
import com.tianhua.codemaker.enums.ModuleEnum;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.tianhua.codemaker.enums.dynamic.DecorateTypeEnum;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Description: controller代码元素包装
 * date: 2021/11/26
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "eleControllerDecorateHandler")
public class EleControllerDecorateHandler implements ElementDecorateHandler {
    @Override
    public void decorateElement(Map<String, AbstractClassBean> classCompMap, PlantUmlContextBean plantUmlContextBean) {
        AbstractClassBean resultDataDtoClassBean = classCompMap.get(ApiConstant.CLASS_API_RESULT_DATA_DTO);
        AbstractClassBean pageVOClassBean = classCompMap.get(ApiConstant.CLASS_API_PAGE_VO);
        AbstractClassBean restControllerBean = classCompMap.get(ArchConstant.ANNO_SPRING_RestController);
        AbstractClassBean requestBodyBean = classCompMap.get(ArchSpringConstant.ANNO_SPRING_REQUEST_BODY);
        AbstractClassBean requestMappingBean = classCompMap.get(ArchSpringConstant.ANNO_SPRING_REQUEST_MAPPING);
        AbstractClassBean requestParamBean = classCompMap.get(ArchSpringConstant.ANNO_SPRING_REQUEST_PARAM);

        if (resultDataDtoClassBean != null) {
            decorateController(resultDataDtoClassBean, plantUmlContextBean, DecorateTypeEnum.METHOD_RETURN_CLASS);
        }

        if (pageVOClassBean != null) {
            decorateController(pageVOClassBean, plantUmlContextBean, DecorateTypeEnum.METHOD_RETURN_PAGEVO_CLASS);
        }

        if (restControllerBean != null) {
            decorateController(restControllerBean, plantUmlContextBean, DecorateTypeEnum.CLASS_ANNOTATION);
        }

        if(requestBodyBean != null){
            decorateController(requestBodyBean, plantUmlContextBean, DecorateTypeEnum.METHOD_ANNOTATION);
        }

        if(requestMappingBean != null){
            decorateController(requestMappingBean, plantUmlContextBean, DecorateTypeEnum.METHOD_ANNOTATION);
        }

        if(requestParamBean != null){
            decorateController(requestParamBean, plantUmlContextBean, DecorateTypeEnum.METHOD_ANNOTATION);
        }

        //如果applicationType=springcloud则对controller进行实现接口包装
        if (plantUmlContextBean.getApplicationType().equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            decorateControllerImpl(plantUmlContextBean);
        }

    }

    /**
     * 包装controller
     *
     * @param abstractClassBean
     * @param plantUmlContextBean
     */
    private void decorateController(AbstractClassBean abstractClassBean, PlantUmlContextBean plantUmlContextBean, DecorateTypeEnum decorateTypeEnum) {
        if (abstractClassBean == null) {
            return;
        }
        plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k, v) -> {
            if (k.toLowerCase().endsWith(TemplateFileEnum.CONTROLLER.getTempFileName())) {
                //包装方法返回值
                if (decorateTypeEnum.getCode() == DecorateTypeEnum.METHOD_RETURN_CLASS.getCode()) {
                    v.addImportClass(abstractClassBean.getPackageName());
                    v.getMethodBeanList().forEach(methodBean -> methodBean.wrapperResultBody(ApiConstant.CLASS_API_RESULT_DATA_DTO));
                }

                //包装方法分页对象
                if (decorateTypeEnum.getCode() == DecorateTypeEnum.METHOD_RETURN_PAGEVO_CLASS.getCode()) {
                    Optional<MethodBean> methodBeanOptional = v.getMethodBeanList().stream().filter(methodBean -> methodBean.getMethodName().toLowerCase().contains(ApiConstant.CLASS_API_PAGE_VO.toLowerCase())).findFirst();
                    if (methodBeanOptional.isPresent()) {
                        v.addImportClass(abstractClassBean.getPackageName());
                    }
                }
                
                //包装类上的注解
                if (decorateTypeEnum.getCode() == DecorateTypeEnum.CLASS_ANNOTATION.getCode()) {
                    v.setAnnotation("@" + abstractClassBean.getClassName());
                    v.addImportClass(abstractClassBean.getPackageName());
                }

                //包装方法注解
                if(decorateTypeEnum.getCode() == DecorateTypeEnum.METHOD_ANNOTATION.getCode()){
                    v.addImportClass(abstractClassBean.getPackageName());
                }
            }
        });
    }

    /**
     * 包装controllerImpl
     *
     * @param plantUmlContextBean
     */
    private void decorateControllerImpl(PlantUmlContextBean plantUmlContextBean) {

        plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k, v) -> {
            if (k.toLowerCase().endsWith(TemplateFileEnum.CONTROLLER.getTempFileName())) {
                String feignInterfaceName = k.replace("Controller", "Feign");
                InterfaceBean feignInterface = plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().get(feignInterfaceName);
                if (feignInterface != null) {
                    v.addImportClass(feignInterface.getPackageName() + "." + feignInterface.getClassName());
                    v.setRelationClassStr(" implements " + feignInterface.getClassName());
                }
            }
        });
    }


}
