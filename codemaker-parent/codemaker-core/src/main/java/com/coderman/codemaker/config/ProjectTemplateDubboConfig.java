package com.coderman.codemaker.config;

import com.coderman.codemaker.bean.GlobalConstant;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 * 生成dubbo项目的配置文件
 * date: 2021/6/18
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Configuration
@Component
@PropertySource("classpath:projecttemplate-dubbo.properties")
public class ProjectTemplateDubboConfig {

    /**
     * 项目数据库名称
     */
    @Value(value = "${dubbo.global.dbName}")
    private String dbName;

    /**
     * 应用名称
     */
    @Value(value = "${dubbo.global.applicationName}")
    private String applicationName;


    /**
     * dubbo-common模块地址
     */
    @Value(value = "${dubbo.code.outpath.dubbo-common}")
    private String moduleCommonPath;

    /**
     * dubbo-api模块地址
     */
    @Value(value = "${dubbo.code.outpath.dubbo-api}")
    private String moduleApiPath;

    /**
     * dubbo-core模块地址
     */
    @Value(value = "${dubbo.code.outpath.dubbo-core}")
    private String moduleCorePath;

    /**
     * 应用服务的plantUML类图文件,不配置则走基于数据表的方式生成代码
     */
    @Value("${dubbo.domain.plantuml}")
    private String plantumlName;

    /**
     * 是否构建api文档
     */
    @Value(("${dubbo.api.generator}"))
    private Boolean apiGenerate;

    @Value(value = "${dubbo.component.scan.config}")
    private String componentList;


    @Value(value = "${springboot.component.dsl.read}")
    private String readSenceSet;


    @Value(value = "${springboot.component.dsl.write}")
    private String writeSenceSet;

    @Value(value = "${dubbo.subpackage.request}")
    private Boolean request;

    @Value(value = "${dubbo.subpackage.response}")
    private Boolean response;

    @Value(value = "${dubbo.component.scan.beans}")
    private String componentScanBeans;


    @Value(value = "${dubbo.component.decorate.beans}")
    private String componentDecorateBeans;


    @Value(value = "${dubbo.component.init.clazz}")
    private String initClazz;

    public String getInitClazz() {
        return initClazz;
    }

    public void setInitClazz(String initClazz) {
        this.initClazz = initClazz;
    }

    public String getComponentScanBeans() {
        return componentScanBeans;
    }

    public void setComponentScanBeans(String componentScanBeans) {
        this.componentScanBeans = componentScanBeans;
    }

    public String getComponentDecorateBeans() {
        return componentDecorateBeans;
    }

    public void setComponentDecorateBeans(String componentDecorateBeans) {
        this.componentDecorateBeans = componentDecorateBeans;
    }

    public Boolean getRequest() {
        return request;
    }

    public void setRequest(Boolean request) {
        this.request = request;
    }

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }

    public String getReadSenceSet() {
        return readSenceSet;
    }

    public void setReadSenceSet(String readSenceSet) {
        this.readSenceSet = readSenceSet;
    }

    public String getWriteSenceSet() {
        return writeSenceSet;
    }

    public void setWriteSenceSet(String writeSenceSet) {
        this.writeSenceSet = writeSenceSet;
    }

    public String getComponentList() {
        return componentList;
    }

    public void setComponentList(String componentList) {
        this.componentList = componentList;
    }

    public Boolean getApiGenerate() {
        return apiGenerate;
    }

    public void setApiGenerate(Boolean apiGenerate) {
        this.apiGenerate = apiGenerate;
    }

    public String getPlantumlName() {
        return plantumlName;
    }

    public void setPlantumlName(String plantumlName) {
        this.plantumlName = plantumlName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getModuleCommonPath() {
        return moduleCommonPath;
    }

    public void setModuleCommonPath(String moduleCommonPath) {
        this.moduleCommonPath = moduleCommonPath;
    }

    public String getModuleApiPath() {
        return moduleApiPath;
    }

    public void setModuleApiPath(String moduleApiPath) {
        this.moduleApiPath = moduleApiPath;
    }

    public String getModuleCorePath() {
        return moduleCorePath;
    }

    public void setModuleCorePath(String moduleCorePath) {
        this.moduleCorePath = moduleCorePath;
    }

    /**
     * 获取api doc文件存储路径
     * @return
     */
    public String getApiDocPath(){
        int index = this.getModuleApiPath().lastIndexOf("/");
        return this.getModuleApiPath().substring(0,index)+"/"+ GlobalConstant.API_DOC;
    }

    /**
     * 获取配置的组件扫描bean
     * @return
     */
    public List<String> getComponentScanBeanList(){
        String scanBeansStr = this.getComponentScanBeans();

        if(StringUtils.isEmpty(scanBeansStr)){
            return null;
        }
        return Lists.newArrayList(scanBeansStr.split(","));
    }

    /**
     * 获取配置的组件装饰bean
     * @return
     */
    public List<String> getComponentDecorateBeanList(){
        String  decorateBeansStr = this.getComponentDecorateBeans();

        if(StringUtils.isEmpty(decorateBeansStr)){
            return null;
        }
        return Lists.newArrayList(decorateBeansStr.split(","));
    }


}
