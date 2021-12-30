package com.coderman.codemaker.element.impl;

import com.coderman.codemaker.bean.plantuml.AbstractClassBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.constant.ApiConstant;
import com.coderman.codemaker.constant.ArchConstant;
import com.coderman.codemaker.constant.ArchSpringConstant;
import com.coderman.codemaker.element.ElementDecorateHandler;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.enums.dynamic.DecorateTypeEnum;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Description: controller代码元素包装
 * date: 2021/11/26
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "eleFeignDecorateHandler")
public class EleFeignDecorateHandler implements ElementDecorateHandler {
    @Override
    public void decorateElement(Map<String, AbstractClassBean> classCompMap, PlantUmlContextBean plantUmlContextBean) {
        AbstractClassBean resultDataDtoClassBean = classCompMap.get(ApiConstant.CLASS_API_RESULT_DATA_DTO);
        AbstractClassBean pageDTOClassBean = classCompMap.get(ApiConstant.CLASS_API_PAGE_DTO);
        AbstractClassBean feignClientBean = classCompMap.get(ArchSpringConstant.ANNO_FEIGN_CLIENT);
        AbstractClassBean requestBodyBean = classCompMap.get(ArchSpringConstant.ANNO_SPRING_REQUEST_BODY);
        AbstractClassBean requestMappingBean = classCompMap.get(ArchSpringConstant.ANNO_SPRING_REQUEST_MAPPING);
        AbstractClassBean requestParamBean = classCompMap.get(ArchSpringConstant.ANNO_SPRING_REQUEST_PARAM);


        if(resultDataDtoClassBean != null){
            decorateFeign(resultDataDtoClassBean, plantUmlContextBean, DecorateTypeEnum.METHOD_RETURN_CLASS);
        }
        if(pageDTOClassBean != null){
            decorateFeign(pageDTOClassBean, plantUmlContextBean, DecorateTypeEnum.METHOD_RETURN_PAGEDTO_CLASS);
        }

        if(feignClientBean != null){
            decorateFeign(feignClientBean, plantUmlContextBean, DecorateTypeEnum.CLASS_ANNOTATION);
        }

        if(requestBodyBean != null){
            decorateFeign(requestBodyBean, plantUmlContextBean, DecorateTypeEnum.METHOD_ANNOTATION);
        }

        if(requestMappingBean != null){
            decorateFeign(requestMappingBean, plantUmlContextBean, DecorateTypeEnum.METHOD_ANNOTATION);
        }

        if(requestParamBean != null){
            decorateFeign(requestParamBean, plantUmlContextBean, DecorateTypeEnum.METHOD_ANNOTATION);
        }



    }

    /**
     * 包装facade
     * @param abstractClassBean
     * @param plantUmlContextBean
     */
    private void decorateFeign(AbstractClassBean abstractClassBean, PlantUmlContextBean plantUmlContextBean, DecorateTypeEnum decorateTypeEnum) {
        if (abstractClassBean == null) {
            return;
        }
        plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().forEach((k, v) -> {
            if (k.toLowerCase().endsWith(TemplateFileEnum.FEIGN.getTempFileName().toLowerCase())) {
                //包装方法返回值
                if(decorateTypeEnum.getCode() == DecorateTypeEnum.METHOD_RETURN_CLASS.getCode()){
                    v.addImportClass(abstractClassBean.getPackageName());
                    v.getMethodBeanList().forEach(methodBean -> methodBean.wrapperResultBody(ApiConstant.CLASS_API_RESULT_DATA_DTO));
                }

                //导入方法分页对象
                if(decorateTypeEnum.getCode() == DecorateTypeEnum.METHOD_RETURN_PAGEDTO_CLASS.getCode()){
                    Optional<MethodBean> methodBeanOptional = v.getMethodBeanList().stream().filter(methodBean -> methodBean.getMethodName().toLowerCase().contains(ApiConstant.CLASS_API_PAGE_DTO.toLowerCase())).findFirst();
                    if(methodBeanOptional.isPresent()){
                        v.addImportClass(abstractClassBean.getPackageName());
                    }
                }


                if(decorateTypeEnum.getCode() == DecorateTypeEnum.CLASS_ANNOTATION.getCode()){
                    //包装注解
                    v.setAnnotation("@" + abstractClassBean.getClassName());
                    v.addImportClass(abstractClassBean.getPackageName());
                }


                if(decorateTypeEnum.getCode() == DecorateTypeEnum.METHOD_ANNOTATION.getCode()){
                    v.addImportClass(abstractClassBean.getPackageName());
                }
            }
        });
    }
}
