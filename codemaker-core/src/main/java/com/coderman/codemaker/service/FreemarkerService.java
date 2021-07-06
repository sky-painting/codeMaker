package com.coderman.codemaker.service;

import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.utils.FreemarkerUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description:freemarker代码模板服务
 * date: 2021/6/23
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component
public class FreemarkerService {

    @Autowired
    private AppServiceConfig appServiceConfig;

    private static final String dynamicDDDPath = "/dynamicddd/";

    /**
     * 根据不同应用类型获取不同应用代码的模板生成代码类
     *
     * @param viewName
     * @param params
     * @return
     */
    public String parseTpl(String viewName, Map<String, Object> params) {
        String templateName;
        if (viewName.contains("/" + appServiceConfig.getApplicationType())) {
            templateName = viewName.replace("/" + appServiceConfig.getApplicationType()+"/","");
        }else {
            templateName = viewName;
        }

        String moduleName = appServiceConfig.getModuleName(templateName);
        if(StringUtils.isEmpty(moduleName)){
            return null;
        }

        String realViewName = "/" + appServiceConfig.getApplicationType() + "/" + viewName;


        if (viewName.contains("/" + appServiceConfig.getApplicationType())) {
            return FreemarkerUtils.parseTpl(viewName, params);
        }
        return FreemarkerUtils.parseTpl(realViewName, params);
    }
    /**
     * 根据不同应用类型获取不同应用代码的模板生成代码类
     *
     * @param viewName
     * @param params
     * @return
     */
    public String parseTplDynamicDDD(String viewName, Map<String, Object> params) {

        return FreemarkerUtils.parseTpl(dynamicDDDPath+viewName, params);
    }

}
