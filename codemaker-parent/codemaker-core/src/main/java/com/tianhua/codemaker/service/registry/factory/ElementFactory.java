package com.tianhua.codemaker.service.registry.factory;

import com.alibaba.fastjson.JSON;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.app.dynamicddd.ElementHandlerContainer;
import com.tianhua.codemaker.bean.component.ComponentContextBean;
import com.tianhua.codemaker.bean.config.FtlBean;
import com.tianhua.codemaker.bean.config.PomBean;
import com.tianhua.codemaker.bean.dddelement.ElementBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.config.AppServiceConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2022/3/28
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Slf4j
@Service
public class ElementFactory {
    @Autowired
    private AppServiceConfig appServiceConfig;
    @Autowired
    protected ElementHandlerContainer elementHandlerContainer;


    /**
     * 构建自定义代码元素集合
     * @param plantUmlContextBean
     * @return
     */
    public Map<String, ElementBean> getElementMap(PlantUmlContextBean plantUmlContextBean){
        List<FtlBean> ftlBeanList = appServiceConfig.getCustomCodeFtlList();
        Map<String, ElementBean> customElementMap = new HashMap<>();
        if(CollectionUtils.isNotEmpty(ftlBeanList)){
            for (FtlBean ftlBean : ftlBeanList){
                DomainElementHandler domainElementHandler = elementHandlerContainer.getElementHandler(ftlBean.getCodeTempFileName());
                if(domainElementHandler == null){
                    log.error("找不到对应代码元素处理器,ftlBean = "+ JSON.toJSONString(ftlBean));
                    continue;
                }
                plantUmlContextBean.setFtlBean(ftlBean);
                ElementBean elementBean = (ElementBean)domainElementHandler.getElementBeanList(plantUmlContextBean);
                customElementMap.put(ftlBean.getFtlStr(),elementBean);
            }
        }

        return customElementMap;
    }


    /**
     * 处理pom依赖
     * @param plantUmlContextBean
     */
    public void dealPomDependency(PlantUmlContextBean plantUmlContextBean){
        Map<String, ComponentContextBean> contextBeanMap = plantUmlContextBean.getCompContextBeanMap();
        Map<String, PomBean> pomBeanMap = plantUmlContextBean.getPomBeanMap();
        for (Map.Entry<String,PomBean> pomBeanEntry : pomBeanMap.entrySet()){
            PomBean pomBean = pomBeanEntry.getValue();
            if(CollectionUtils.isEmpty(pomBean.getDependencyList())){
                continue;
            }
            pomBean.getDependencyList().forEach(gavBean -> {
                ComponentContextBean componentContextBean = contextBeanMap.get(gavBean.getComponentName());
                if(componentContextBean != null && componentContextBean.getComponentConfigBean() != null){
                    gavBean.setArtifactId(componentContextBean.getComponentConfigBean().getArtifactId());
                    gavBean.setGroupId(componentContextBean.getComponentConfigBean().getGroupId());
                    gavBean.setVersion(componentContextBean.getComponentConfigBean().getVersion());
                    gavBean.setScope(componentContextBean.getComponentConfigBean().getScope());
                    gavBean.setType(componentContextBean.getComponentConfigBean().getType());
                    gavBean.setExclusionGAContent(buildExclusionGAContent(componentContextBean.getComponentConfigBean().getExclusionGAList()));
                }
            });
        }
    }


    private String buildExclusionGAContent(String exclusionGAList){
        if(StringUtils.isEmpty(exclusionGAList)){
            return null;
        }
        StringBuilder builder = new StringBuilder("            <exclusions>\n");
        if(exclusionGAList.contains(",")){
            String [] exclusionArr = exclusionGAList.split(",");
            for (String exclusionGA : exclusionArr){
                String [] gaArr = exclusionGA.split(":");
                builder.append("                <exclusion>\n");
                builder.append("                    <groupId>" + gaArr[0] + "</groupId>\n");
                builder.append("                    <artifactId>" + gaArr[1] + "</artifactId>\n");
                builder.append("                </exclusion>\n");
            }
        }else{
            String [] gaArr = exclusionGAList.split(":");
            builder.append("                <exclusion>\n");
            builder.append("                    <groupId>" + gaArr[0] + "</groupId>\n");
            builder.append("                    <artifactId>" + gaArr[1] + "</artifactId>\n");
            builder.append("                </exclusion>\n");
        }
        builder.append("            </exclusions>\n");
        return builder.toString();
    }

}
