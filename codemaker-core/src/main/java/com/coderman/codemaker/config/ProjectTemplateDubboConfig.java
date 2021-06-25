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
     * 全局包名
     */
    @Value(value = "${dubbo.global.package}")
    private String globalPackage;

    /**
     * 项目作者
     */
    @Value(value = "${dubbo.global.author}")
    private String author;

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
    @Value(value = "${dubbo.code.outpath.dubbbo-common}")
    private String moduleCommonPath;

    /**
     * dubbo-api模块地址
     */
    @Value(value = "${dubbo.code.outpath.dubbbo-api}")
    private String moduleApiPath;

    /**
     * dubbo-core模块地址
     */
    @Value(value = "${dubbo.code.outpath.dubbbo-core}")
    private String moduleCorePath;

    public String getGlobalPackage() {
        return globalPackage;
    }

    public void setGlobalPackage(String globalPackage) {
        this.globalPackage = globalPackage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
