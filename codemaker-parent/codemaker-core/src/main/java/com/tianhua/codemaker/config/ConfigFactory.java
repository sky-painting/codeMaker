package com.tianhua.codemaker.config;

import com.tianhua.codemaker.bean.component.ComponentConfigBean;
import com.tianhua.codemaker.bean.component.ComponentContextBean;
import com.tianhua.codemaker.bean.config.ProjectBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * date: 2022/3/28
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class ConfigFactory {

    @Autowired
    private AppServiceConfig appServiceConfig;
    /**
     * 构建项目配置信息
     * @param plantUmlContextBean
     * @return
     */
    public ProjectBean getProjectBean(PlantUmlContextBean plantUmlContextBean){

        ProjectBean projectBean = new ProjectBean();
        projectBean.setPackageName(appServiceConfig.getPackage());
        projectBean.setApplicationName(appServiceConfig.getApplicationName());
        projectBean.setDataBaseName(appServiceConfig.getDbName());
        Map<String, ComponentContextBean> contextBeanMap = plantUmlContextBean.getCompContextBeanMap();
        Map<String, ComponentConfigBean> listMap = new HashMap<>();
        if(contextBeanMap != null && !contextBeanMap.isEmpty()){
            contextBeanMap.forEach((k,v)->{
                ComponentConfigBean componentConfigBean = v.getComponentConfigBean();
                if(componentConfigBean != null && CollectionUtils.isNotEmpty(componentConfigBean.getConfigList())){

                    listMap.put(componentConfigBean.getConfigName(),componentConfigBean);
                }
            });
        }
        projectBean.setConfigFileMap(listMap);
        return projectBean;
    }
}
