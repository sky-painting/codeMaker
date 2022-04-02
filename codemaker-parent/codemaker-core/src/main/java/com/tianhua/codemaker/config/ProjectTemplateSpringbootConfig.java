package com.tianhua.codemaker.config;

import com.tianhua.codemaker.bean.GlobalConstant;
import com.tianhua.codemaker.bean.config.ConfigFileBean;
import com.tianhua.codemaker.bean.config.FtlBean;
import com.tianhua.codemaker.bean.config.PomBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * description: ProjectTemplateConfig <br>
 * date: 2020/7/7 22:14 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
@Configuration
@Component
@PropertySource("classpath:projecttemplate-springboot.properties")
public class ProjectTemplateSpringbootConfig {

    @Autowired
    private ConfigParseService configParseService;


    @Value(value = "${springboot.global.dbName}")
    private String dbName;

    @Value(value = "${springboot.global.applicationName}")
    private String applicationName;

    @Value(value = "${springboot.pom.groupId}")
    private String groupId;

    @Value(value = "${springboot.pom.artifactId}")
    private String artifactId;

    @Value(value = "${springboot.pom.version}")
    private String version;


    /**
     * 代码生成项目主目录
     */
    @Value(value = "${springboot.code.app.project.path}")
    private String projectPath;

    /**
     * 应用工程别名
     */
    @Value(value = "${springboot.global.applicationNameAlias}")
    private String appAliasName;

    /**
     * 应用服务的plantUML类图文件,不配置则走基于数据表的方式生成代码
     */
    @Value("${springboot.domain.plantuml}")
    private String plantumlName;

    /**
     * 是否构建api文档
     */
    @Value("${springboot.api.generator}")
    private Boolean apiGenerate;


    /**
     * 是否同步plantuml文档到工程中
     */
    @Value("${springboot.plantumldoc.sync}")
    private Boolean plantUMlDocSync;


    @Value(value = "${springboot.component.scan.config}")
    private String componentList;


    @Value(value = "${springboot.component.dsl.read}")
    private String readSenceSet;


    @Value(value = "${springboot.component.dsl.write}")
    private String writeSenceSet;

    @Value(value = "${springboot.subpackage.request}")
    private Boolean request;

    @Value(value = "${springboot.subpackage.response}")
    private Boolean response;

    @Value(value = "${springboot.component.scan.beans}")
    private String componentScanBeans;


    @Value(value = "${springboot.component.decorate.beans}")
    private String componentDecorateBeans;

    @Value(value = "${springboot.component.init.clazz}")
    private String initClazz;


    @Value(value = "${springboot.code.template.custom.ftl}")
    private String customFtl;


    /**
     * 初始化的pom依赖
     */
    @Value(value = "${springboot.component.init.pom}")
    private String initPom;

    /**
     * 项目需要依赖的配置文件
     */
    @Value(value = "${springboot.component.init.config}")
    private String configFile;

    /**
     * 参数校验逻辑 植入bean配置
     */
    @Value(value = "${springboot.component.validate.beans}")
    private String validateBeans;


    public String getInitClazz() {
        return initClazz;
    }


    public String getComponentScanBeans() {
        return componentScanBeans;
    }


    public String getComponentDecorateBeans() {
        return componentDecorateBeans;
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


    public String getComponentList() {
        return componentList;
    }


    public Boolean getApiGenerate() {
        return apiGenerate;
    }


    public String getPlantumlName() {
        return plantumlName;
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public Boolean getPlantUMlDocSync() {
        return plantUMlDocSync;
    }

    public void setPlantUMlDocSync(Boolean plantUMlDocSync) {
        this.plantUMlDocSync = plantUMlDocSync;
    }

    @Override
    public String toString() {
        return "ProjectTemplateSpringbootConfig{" +
                "dbName='" + dbName + '\'' +
                ", applicationName='" + applicationName + '\'' +
                ", groupId='" + groupId + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", version='" + version + '\'' +
                ", plantumlName='" + plantumlName + '\'' +
                '}';
    }

    /**
     * 获取api doc文件存储路径
     * @return
     */
    public String getApiDocPath(){
        return getModulePath() + "/" + GlobalConstant.API_DOC;
    }

    /**
     * 获取UML doc文件存储路径
     * @return
     */
    public String getUMLDocPath(){
        return getModulePath() + "/" + GlobalConstant.PLANT_UML_DOC;
    }

    public String getModulePath() {
        return projectPath + "/" + applicationName;
    }
    /**
     * 获取项目配置文件配置
     * @return
     */
    public List<ConfigFileBean> getConfigFileBean(){
        return configParseService.getConfigFileBean(this.configFile);
    }

    /**
     * 获取统一的pom配置
     * 默认是父级pom.xml的实例
     * @return
     */
    public Map<String, PomBean> getPomBeanV2(){
        return configParseService.getPomBeanV2(initPom, applicationName, appAliasName,groupId,artifactId,version);
    }

    /**
     * 返回配置的参数验证服务bean
     * @return
     */
    public List<String> getValidateBeanList(){
        return configParseService.getValidateBeanList(validateBeans);
    }

    /**
     * 获取自定义配置的代码元素
     * @return
     */
    public List<FtlBean> getFtlBeanList(){
        return configParseService.getFtlBeanList(this.customFtl,initPom, applicationName, appAliasName,groupId,artifactId,version);
    }
}
