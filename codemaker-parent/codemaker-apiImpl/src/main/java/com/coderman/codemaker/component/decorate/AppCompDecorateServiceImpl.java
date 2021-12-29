package com.coderman.codemaker.component.decorate;

import com.coderman.codemaker.api.ICompDecorateService;
import com.coderman.codemaker.bean.component.ComponentContextBean;
import com.coderman.codemaker.bean.plantuml.*;
import com.coderman.codemaker.constant.ApiConstant;
import com.coderman.codemaker.element.ElementDecorateHandler;
import com.coderman.codemaker.enums.TemplateFileEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 装饰可以按一个组件一个组件的装饰实现也可以按应用维度一次性装饰多个组件到代码上下文模型中
 *
 * 这里对controller和facade接口方法返回值和方法page参数用apiresult组件进行包装
 * date: 2021/11/26
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "appCompDecorateService")
public class AppCompDecorateServiceImpl implements ICompDecorateService {
    @Resource(name = "eleControllerDecorateHandler")
    private ElementDecorateHandler eleControllerDecorateHandler;

    @Resource(name = "eleFacadeDecorateHandler")
    private ElementDecorateHandler eleFacadeDecorateHandler;

    @Resource(name = "eleFeignDecorateHandler")
    private ElementDecorateHandler eleFeignDecorateHandler;

    @Override
    public void decorateComp(ComponentContextBean componentContextBean, PlantUmlContextBean plantUmlContextBean) {

        //包装应用代码内部相关的类
        ClassBean resultDataDtoClassBean = componentContextBean.getTargetClass(ApiConstant.CLASS_API_RESULT_DATA_DTO);
        ClassBean pageVOClassBean = componentContextBean.getTargetClass(ApiConstant.CLASS_API_PAGE_VO);
        Map<String,AbstractClassBean> controllerClassBeanMap = new HashMap<>();
        controllerClassBeanMap.put(ApiConstant.CLASS_API_RESULT_DATA_DTO, resultDataDtoClassBean);
        controllerClassBeanMap.put(ApiConstant.CLASS_API_PAGE_VO, pageVOClassBean);
        eleControllerDecorateHandler.decorateElement(controllerClassBeanMap, plantUmlContextBean);

        ClassBean pageDTOClassBean = componentContextBean.getTargetClass(ApiConstant.CLASS_API_PAGE_DTO);
        Map<String,AbstractClassBean> apiClassBeanMap = new HashMap<>();
        apiClassBeanMap.put(ApiConstant.CLASS_API_RESULT_DATA_DTO, resultDataDtoClassBean);
        apiClassBeanMap.put(ApiConstant.CLASS_API_PAGE_DTO, pageDTOClassBean);
        eleFacadeDecorateHandler.decorateElement(apiClassBeanMap, plantUmlContextBean);


        eleFeignDecorateHandler.decorateElement(apiClassBeanMap, plantUmlContextBean);

    }

    /**
     * 包装controller
     * @param abstractClassBean
     * @param plantUmlContextBean
     *//*
    private void decorateControllerApiResult(AbstractClassBean abstractClassBean, PlantUmlContextBean plantUmlContextBean) {
        if (abstractClassBean == null) {
            return;
        }
        plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k, v) -> {
            if (k.toLowerCase().endsWith(TemplateFileEnum.CONTROLLER.getTempFileName())) {
                v.getImportClassList().add(abstractClassBean.getPackageName());
                //导入分页对象
                Optional<MethodBean> methodBeanOptional = v.getMethodBeanList().stream().filter(methodBean -> methodBean.getReturnClass().contains(ApiConstant.CLASS_API_PAGE_VO)).findFirst();
                if(methodBeanOptional.isPresent()){

                }
                v.getMethodBeanList().forEach(methodBean -> methodBean.setReturnClass(ApiConstant.CLASS_API_RESULT_DATA_DTO+"<"+methodBean.getReturnClass()+">"));
            }
        });
    }

    *//**
     * 包装facadeimpl
     * @param abstractClassBean
     * @param plantUmlContextBean
     *//*
    private void decorateFacadeImpl(AbstractClassBean abstractClassBean, PlantUmlContextBean plantUmlContextBean) {
        if (abstractClassBean == null) {
            return;
        }
        plantUmlContextBean.getDerivedPlantUmlContextBean().getClassBeanMap().forEach((k, v) -> {
            //装饰dubbo注解
            if (k.toLowerCase().endsWith(TemplateFileEnum.FACADE_IMPL.getTempFileName().toLowerCase()) && abstractClassBean.getPackageName().contains("dubbo")) {
                v.setAnnotation("@" + abstractClassBean.getClassName());
                v.getImportClassList().add(abstractClassBean.getPackageName());

            }
        });

        plantUmlContextBean.getDerivedPlantUmlContextBean().getInterfaceBeanMap().forEach((k, v) -> {
            if (k.toLowerCase().endsWith(TemplateFileEnum.FACADE.getTempFileName().toLowerCase())) {
                v.getImportClassList().add(abstractClassBean.getPackageName());
                v.getMethodBeanList().forEach(methodBean -> methodBean.setReturnClass(ApiConstant.CLASS_API_RESULT_DATA_DTO+"<"+methodBean.getReturnClass()+">"));
            }
        });

    }*/
}
