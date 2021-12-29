package com.coderman.codemaker.element.impl;

import com.coderman.codemaker.bean.plantuml.AbstractClassBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.constant.ApiConstant;
import com.coderman.codemaker.constant.ArchConstant;
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
@Component(value = "eleFacadeDecorateHandler")
public class EleFacadeDecorateHandler implements ElementDecorateHandler {
    @Override
    public void decorateElement(Map<String, AbstractClassBean> classCompMap, PlantUmlContextBean plantUmlContextBean) {
        AbstractClassBean resultDataDtoClassBean = classCompMap.get(ApiConstant.CLASS_API_RESULT_DATA_DTO);
        AbstractClassBean pageDTOClassBean = classCompMap.get(ApiConstant.CLASS_API_PAGE_DTO);
        AbstractClassBean dubboServiceBean = classCompMap.get(ArchConstant.ANNO_DUBBO_SERVICE);

        if(resultDataDtoClassBean != null){
            decorateFacade(resultDataDtoClassBean, plantUmlContextBean, DecorateTypeEnum.METHOD_RETURN_CLASS);
        }
        if(pageDTOClassBean != null){
            decorateFacade(pageDTOClassBean, plantUmlContextBean, DecorateTypeEnum.METHOD_RETURN_PAGEDTO_CLASS);
        }

        if(dubboServiceBean != null){
            decorateFacade(dubboServiceBean, plantUmlContextBean, DecorateTypeEnum.CLASS_ANNOTATION);
        }
    }

    /**
     * 包装facade
     * @param abstractClassBean
     * @param plantUmlContextBean
     */
    private void decorateFacade(AbstractClassBean abstractClassBean, PlantUmlContextBean plantUmlContextBean, DecorateTypeEnum decorateTypeEnum) {
        if (abstractClassBean == null) {
            return;
        }
        plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k, v) -> {
            if (k.toLowerCase().endsWith(TemplateFileEnum.FACADE_IMPL.getTempFileName().toLowerCase())) {
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
            }
        });

        plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().forEach((k, v) -> {
            if (k.toLowerCase().endsWith(TemplateFileEnum.FACADE.getTempFileName())) {
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
            }
        });
    }
}
