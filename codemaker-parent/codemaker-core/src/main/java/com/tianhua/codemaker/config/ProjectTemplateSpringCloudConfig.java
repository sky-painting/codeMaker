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
@PropertySource("classpath:projecttemplate-springcloud.properties")
public class ProjectTemplateSpringCloudConfig {

    @Autowired
    private ConfigParseService configParseService;

    @Value(value = "${springcloud.global.dbName}")
    private String dbName;

    @Value(value = "${springcloud.global.applicationName}")
    private String applicationName;

    /**
     * 代码生成项目主目录
     */
    @Value(value = "${springcloud.code.app.project.path}")
    private String projectPath;

    /**
     * 应用工程别名
     */
    @Value(value = "${springcloud.global.applicationNameAlias}")
    private String appAliasName;

    @Value(value = "${springcloud.pom.groupId}")
    private String groupId;

    @Value(value = "${springcloud.pom.artifactId}")
    private String artifactId;

    @Value(value = "${springcloud.pom.version}")
    private String version;

    /**
     * 应用服务的plantUML类图文件,不配置则走基于数据表的方式生成代码
     */
    @Value("${springcloud.domain.plantuml}")
    private String plantumlName;

    /**
     * 是否构建api文档
     */
    @Value("${springcloud.api.generator}")
    private Boolean apiGenerate;

    /**
     * 是否同步plantuml文档到工程中
     */
    @Value("${springcloud.plantumldoc.sync}")
    private Boolean plantUMlDocSync;

    @Value(value = "${springcloud.component.scan.config}")
    private String componentList;


    @Value(value = "${springcloud.component.dsl.read}")
    private String readSenceSet;


    @Value(value = "${springcloud.component.dsl.write}")
    private String writeSenceSet;

    @Value(value = "${springcloud.subpackage.request}")
    private Boolean request;

    @Value(value = "${springcloud.subpackage.response}")
    private Boolean response;

    @Value(value = "${springcloud.component.scan.beans}")
    private String componentScanBeans;


    @Value(value = "${springcloud.component.decorate.beans}")
    private String componentDecorateBeans;

    @Value(value = "${springcloud.component.init.clazz}")
    private String initClazz;


    @Value(value = "${springcloud.code.template.custom.ftl}")
    private String customFtl;


    /**
     * 初始化的pom依赖
     */
    @Value(value = "${springcloud.component.init.pom}")
    private String initPom;

    /**
     * 项目需要依赖的配置文件
     */
    @Value(value = "${springcloud.component.init.config}")
    private String configFile;

    /**
     * 参数校验逻辑 植入bean配置
     */
    @Value(value = "${springcloud.component.validate.beans}")
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

    public String getModuleFeginApiPath() {
        return projectPath + "/" + applicationName + "/" +appAliasName+GlobalConstant.API;
    }


    public String getModuleFeginProviderPath() {
        return projectPath + "/" + applicationName + "/" +appAliasName+GlobalConstant.PROVIDER;
    }


    public String getModuleParentPath() {
        return projectPath + "/" + applicationName;
    }

    /**
     * 获取api doc文件存储路径
     * @return
     */
    public String getApiDocPath(){
        int index = this.getModuleFeginApiPath().lastIndexOf("/");
        return this.getModuleFeginApiPath().substring(0,index)+"/"+ GlobalConstant.API_DOC;
    }

    /**
     * 获取UML doc文件存储路径
     * @return
     */
    public String getUMLDocPath(){
        int index = this.getModuleFeginProviderPath().lastIndexOf("/");
        return this.getModuleFeginProviderPath().substring(0,index)+"/"+ GlobalConstant.PLANT_UML_DOC;
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
