package com.coderman.codemaker.config;

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
@PropertySource( "classpath:projecttemplate-cola.properties")
public class ProjectTemplateColaConfig {

    /**
     * 项目数据库名称
     */
    @Value(value = "${cola.global.dbName}")
    private String dbName;

    /**
     * 项目名称
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
     * 应用服务的plantUML类图文件,不配置则走基于数据表的方式生成代码
     */
    @Value("${cola.domain.plantuml}")
    private String plantumlName;

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
}