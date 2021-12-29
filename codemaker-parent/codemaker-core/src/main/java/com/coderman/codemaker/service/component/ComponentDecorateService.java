package com.coderman.codemaker.service.component;

import com.alibaba.fastjson.JSON;
import com.coderman.codemaker.api.ICompScanService;
import com.coderman.codemaker.api.ICompDecorateService;
import com.coderman.codemaker.bean.component.ComponentContextBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.config.AppServiceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2021/11/23
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
@Slf4j
public class ComponentDecorateService {

    @Autowired
    private AppServiceConfig appServiceConfig;


    /**
     * 装饰支持的框架注解类等信息
     * 包括框架级中间件依赖和应用级组件依赖
     * @param plantUmlContextBean
     */
    public void decorateComponent(PlantUmlContextBean plantUmlContextBean){

        //框架级全局组件扫描装饰
        Map<String, ComponentContextBean> defaultComponentContextBeanMap = appServiceConfig.getDefaultCompScanService().scanComponent(appServiceConfig.getDefaultComponentList());
        if (defaultComponentContextBeanMap == null || defaultComponentContextBeanMap.isEmpty()){
            return;
        }
        for (Map.Entry<String,ComponentContextBean> entry : defaultComponentContextBeanMap.entrySet()){
            appServiceConfig.getDefaultCompDecorateService().decorateComp(entry.getValue(),plantUmlContextBean);
        }



        //应用组件级扫描装饰
        List<ICompScanService> customCompScanServiceList = appServiceConfig.getCustomICompScanServiceList();
        Map<String, ComponentContextBean> customCompContextBeanMap = new HashMap<>();
        for (ICompScanService compScanService : customCompScanServiceList){
            customCompContextBeanMap.putAll(compScanService.scanComponent(appServiceConfig.getCustomComponentList()));
        }
        List<ICompDecorateService> customCompDecorateServiceList = appServiceConfig.getCustomICompDecorateServiceList();
        for (ICompDecorateService compDecorateService : customCompDecorateServiceList){
            for (Map.Entry<String,ComponentContextBean> entry : customCompContextBeanMap.entrySet()){
                compDecorateService.decorateComp(entry.getValue(), plantUmlContextBean);
            }
        }

        //这里只注册应用级组件，框架级组件等有需求再注册
        plantUmlContextBean.setCompContextBeanMap(customCompContextBeanMap);
        log.info("===========comp.size = "+customCompContextBeanMap.size());
    }
}
