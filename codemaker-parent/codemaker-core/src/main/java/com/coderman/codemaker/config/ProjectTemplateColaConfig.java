package com.coderman.codemaker.config;

import com.coderman.codemaker.bean.GlobalConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

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
@PropertySource("classpath:projecttemplate-cola.properties")
public class ProjectTemplateColaConfig {

    /**
     * 项目数据库名称
     */
    @Value(value = "${cola.global.dbName}")
    private String dbName;

    /**
     * 应用名称
     */
    @Value(value = "${cola.global.applicationName}")
    private String applicationName;

    /**
     * dubbo-common模块地址
     */
    @Value(value = "${cola.code.outpath.cola-adapter}")
    private String moduleAdapterPath;

    /**
     * dubbo-api模块地址
     */
    @Value(value = "${cola.code.outpath.cola-app}")
    private String moduleAppPath;

    /**
     * dubbo-api模块地址
     */
    @Value(value = "${cola.code.outpath.cola-client}")
    private String moduleClientPath;
    /**
     * dubbo-api模块地址
     */
    @Value(value = "${cola.code.outpath.cola-infrast}")
    private String moduleInfrastPath;

    /**
     * dubbo-core模块地址
     */
    @Value(value = "${cola.code.outpath.cola-domain}")
    private String moduleDomainPath;

    /**
     * cola-start模块地址
     */
    @Value(value = "${cola.code.outpath.cola-start}")
    private String moduleStartPath;


    /**
     * cola-feign-api模块地址
     */
    @Value(value = "${cola.code.outpath.cola-feign-api}")
    private String moduleFeignApiPath;



    /**
     * 应用服务的plantUML类图文件,不配置则走基于数据表的方式生成代码
     */
    @Value("${cola.domain.plantuml}")
    private String plantumlName;

    /**
     * 是否构建api文档
     */
    @Value("${cola.api.generator}")
    private Boolean apiGenerate;

    @Value(value = "${cola.component.scan.config}")
    private String componentList;

    @Value(value = "${springboot.component.dsl.read}")
    private String readSenceSet;


    @Value(value = "${springboot.component.dsl.write}")
    private String writeSenceSet;


    @Value(value = "${cola.subpackage.request}")
    private Boolean request;

    @Value(value = "${cola.subpackage.response}")
    private Boolean response;

    @Value(value = "${cola.component.scan.beans}")
    private String componentScanBeans;


    @Value(value = "${cola.component.decorate.beans}")
    private String componentDecorateBeans;

    @Value(value = "${cola.component.init.clazz}")
    private String initClazz;


    public String getModuleStartPath() {
        return moduleStartPath;
    }

    public void setModuleStartPath(String moduleStartPath) {
        this.moduleStartPath = moduleStartPath;
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

    public String getModuleAdapterPath() {
        return moduleAdapterPath;
    }

    public void setModuleAdapterPath(String moduleAdapterPath) {
        this.moduleAdapterPath = moduleAdapterPath;
    }

    public String getModuleAppPath() {
        return moduleAppPath;
    }

    public void setModuleAppPath(String moduleAppPath) {
        this.moduleAppPath = moduleAppPath;
    }

    public String getModuleClientPath() {
        return moduleClientPath;
    }

    public void setModuleClientPath(String moduleClientPath) {
        this.moduleClientPath = moduleClientPath;
    }

    public String getModuleInfrastPath() {
        return moduleInfrastPath;
    }

    public void setModuleInfrastPath(String moduleInfrastPath) {
        this.moduleInfrastPath = moduleInfrastPath;
    }

    public String getModuleDomainPath() {
        return moduleDomainPath;
    }

    public void setModuleDomainPath(String moduleDomainPath) {
        this.moduleDomainPath = moduleDomainPath;
    }

    /**
     * 获取api doc文件存储路径
     * @return
     */
    public String getApiDocPath(){
        int index = this.getModuleDomainPath().lastIndexOf("/");
        return this.getModuleDomainPath().substring(0,index)+"/"+ GlobalConstant.API_DOC;
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


    public String getInitClazz() {
        return initClazz;
    }

    public void setInitClazz(String initClazz) {
        this.initClazz = initClazz;
    }

    public String getModuleFeignApiPath() {
        return moduleFeignApiPath;
    }

    public void setModuleFeignApiPath(String moduleFeignApiPath) {
        this.moduleFeignApiPath = moduleFeignApiPath;
    }
}