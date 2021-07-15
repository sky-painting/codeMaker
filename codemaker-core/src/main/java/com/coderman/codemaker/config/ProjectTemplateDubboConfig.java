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
@PropertySource( "classpath:projecttemplate-dubbo.properties")
public class ProjectTemplateDubboConfig {

    /**
     * 项目数据库名称
     */
    @Value(value = "${dubbo.global.dbName}")
    private String dbName;

    /**
     * 项目名称
     */
    @Value(value = "${codemaker.pom.projectName}")
    private String projectName;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
}
