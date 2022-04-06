package com.tianhua.codemaker.config;

import com.tianhua.codemaker.bean.GlobalConstant;
import com.tianhua.codemaker.bean.config.ConfigFileBean;
import com.tianhua.codemaker.bean.config.FtlBean;
import com.tianhua.codemaker.bean.config.GAVBean;
import com.tianhua.codemaker.bean.config.PomBean;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * 生成dubbo项目的配置文件
 * date: 2021/6/18
 *
 * @author shenshuai
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
     * 代码生成项目主目录
     */
    @Value(value = "${cola.code.app.project.path}")
    private String projectPath;


    /**
     * 应用工程别名
     */
    @Value(value = "${cola.global.applicationNameAlias}")
    private String appAliasName;



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

    /**
     * 是否同步plantuml文档到工程中
     */
    @Value("${cola.plantumldoc.sync}")
    private Boolean plantUMlDocSync;

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

    @Value(value = "${cola.pom.parent.groupId}")
    private String groupId;

    @Value(value = "${cola.pom.parent.artifactId}")
    private String artifactId;

    @Value(value = "${cola.pom.parent.version}")
    private String version;


    @Value(value = "${cola.code.template.custom.ftl}")
    private String customFtl;


    /**
     * 初始化的pom依赖
     */
    @Value(value = "${cola.component.init.pom}")
    private String initPom;

    /**
     * 项目需要依赖的配置文件
     */
    @Value(value = "${cola.component.init.config}")
    private String configFile;

    /**
     * 参数校验逻辑 植入bean配置
     */
    @Value(value = "${cola.component.validate.beans}")
    private String validateBeans;


    public String getModuleStartPath() {
        return projectPath + "/" + applicationName + "/" +appAliasName+GlobalConstant.START;
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
        return projectPath + "/" + applicationName + "/" +appAliasName+GlobalConstant.ADAPTER;
    }

    public String getModuleAppPath() {
        return projectPath + "/" + applicationName + "/" +appAliasName+GlobalConstant.APP;
    }

    public String getModuleParentPath() {
        return projectPath + "/" + applicationName;
    }

    public String getModuleClientPath() {
        return projectPath + "/" + applicationName + "/" +appAliasName+GlobalConstant.CLIENT;
    }

    public String getModuleInfrastPath() {
        return projectPath + "/" + applicationName + "/" +appAliasName+GlobalConstant.INFRAST;
    }

    public String getModuleDomainPath() {
        return projectPath + "/" + applicationName + "/" +appAliasName+GlobalConstant.DOMAIN;

    }

    public Boolean getPlantUMlDocSync() {
        return plantUMlDocSync;
    }

    public void setPlantUMlDocSync(Boolean plantUMlDocSync) {
        this.plantUMlDocSync = plantUMlDocSync;
    }

    /**
     * 获取api doc文件存储路径
     * @return
     */
    public String getApiDocPath(){
        int index = this.getModuleDomainPath().lastIndexOf("/");
        return this.getModuleDomainPath().substring(0,index)+"/"+ GlobalConstant.API_DOC;
    }


    /**
     * 获取UML doc文件存储路径
     * @return
     */
    public String getUMLDocPath(){
        int index = this.getModuleDomainPath().lastIndexOf("/");
        return this.getModuleDomainPath().substring(0,index)+"/"+ GlobalConstant.PLANT_UML_DOC;
    }

    public String getComponentScanBeans() {
        return componentScanBeans;
    }


    public String getComponentDecorateBeans() {
        return componentDecorateBeans;
    }

    public String getInitClazz() {
        return initClazz;
    }

    public String getModuleFeignApiPath() {
        return projectPath + "/" + applicationName + "/" +appAliasName+GlobalConstant.FEIGN_API;
    }

    /**
     * 获取自定义配置的代码元素
     * @return
     */
    public List<FtlBean> getFtlBeanList(){
        List<FtlBean> ftlBeans = new ArrayList<>();
        Map<String, PomBean> pomBeanMap = getPomBeanV2();
        for (Map.Entry<String,PomBean> pomBeanEntry : pomBeanMap.entrySet()){
            PomBean pomBean = pomBeanEntry.getValue();
            String ftlStr = pomBean.getTemplateCode()+":pom:"+pomBean.getModuleCode();
            ftlBeans.add(FtlBean.getInstance(ftlStr));
        }

        if(StringUtils.isEmpty(this.customFtl)){
            return ftlBeans;
        }
        if(this.customFtl.contains(",")){
            String [] ftlStrArr = this.customFtl.split(",");
            for (String flt : ftlStrArr){
                ftlBeans.add(FtlBean.getInstance(flt));
            }
        }else {
            ftlBeans.add(FtlBean.getInstance(customFtl));
        }


        return ftlBeans;
    }

    /**
     * 获取cola的统一的pom配置
     * 默认是父级pom.xml的实例
     * @return
     */
    public Map<String, PomBean> getPomBeanV2(){
        Map<String, PomBean> pomBeanMap = getPomBeanMap();
        if(StringUtils.isNotEmpty(initPom)){
            String [] arr = initPom.split(",");
            for (String pom : arr){
                String [] pomArr = pom.split(":");
                PomBean modulePom = pomBeanMap.get(pomArr[1]);
                if(modulePom == null){
                    modulePom = new PomBean();
                    modulePom.setModuleName(pomArr[1]);
                    modulePom.setModulePrefix(appAliasName);
                    modulePom.setGroupId(groupId);
                    List<GAVBean> gavBeanList = new ArrayList<>();
                    gavBeanList.add(new GAVBean(pomArr[0]));
                    modulePom.setDependencyList(gavBeanList);
                }else {
                    modulePom.getDependencyList().add(new GAVBean(pomArr[0]));
                }
                pomBeanMap.put(pomArr[1], modulePom);
            }
        }
        return pomBeanMap;
    }

    /**
     * 获取pom配置
     * @return
     */
    private Map<String,PomBean> getPomBeanMap(){
        Map<String,PomBean> pomBeanMap = new HashMap<>();
        PomBean parentPom = new PomBean();
        parentPom.setUrl(null);
        parentPom.buildGAV(groupId,artifactId,version);
        parentPom.setTemplateCode(TemplateFileEnum.PARENT_POM.getTempFileName());
        parentPom.setAppName(applicationName);
        parentPom.buildModule(appAliasName, GlobalConstant.PARENT);
        parentPom.setDependencyList(new ArrayList<>());


        PomBean clientPom = new PomBean();
        clientPom.buildGAV(groupId,artifactId,version);
        clientPom.setDependencyList(new ArrayList<>());
        clientPom.setTemplateCode(TemplateFileEnum.CLIENT_POM.getTempFileName());
        clientPom.buildModule(appAliasName, GlobalConstant.CLIENT);


        PomBean appPom = new PomBean();
        appPom.buildGAV(groupId,artifactId,version);
        appPom.setDependencyList(new ArrayList<>());
        appPom.setTemplateCode(TemplateFileEnum.APP_POM.getTempFileName());
        appPom.buildModule(appAliasName, GlobalConstant.APP);

        PomBean domainPom = new PomBean();
        domainPom.buildGAV(groupId,artifactId,version);
        domainPom.setDependencyList(new ArrayList<>());
        domainPom.setTemplateCode(TemplateFileEnum.DOMAIN_POM.getTempFileName());
        domainPom.buildModule(appAliasName, GlobalConstant.DOMAIN);


        PomBean infrastPom = new PomBean();
        infrastPom.buildGAV(groupId,artifactId,version);
        infrastPom.setDependencyList(new ArrayList<>());
        infrastPom.setTemplateCode(TemplateFileEnum.INFRAST_POM.getTempFileName());
        infrastPom.buildModule(appAliasName, GlobalConstant.INFRAST);

        PomBean startPom = new PomBean();
        startPom.buildGAV(groupId,artifactId,version);
        startPom.setDependencyList(new ArrayList<>());
        startPom.setTemplateCode(TemplateFileEnum.START_POM.getTempFileName());
        startPom.buildModule(appAliasName, GlobalConstant.START);

        PomBean adapterPom = new PomBean();
        adapterPom.buildGAV(groupId,artifactId,version);
        adapterPom.setDependencyList(new ArrayList<>());
        adapterPom.setTemplateCode(TemplateFileEnum.ADAPTER_POM.getTempFileName());
        adapterPom.buildModule(appAliasName, GlobalConstant.ADAPTER);

        PomBean feignApiPom = new PomBean();
        feignApiPom.buildGAV(groupId,artifactId,version);
        feignApiPom.setDependencyList(new ArrayList<>());
        feignApiPom.setTemplateCode(TemplateFileEnum.FEIGN_API_POM.getTempFileName());
        feignApiPom.buildModule(appAliasName, GlobalConstant.FEIGN_API);

        pomBeanMap.put(GlobalConstant.PARENT,parentPom);
        pomBeanMap.put(GlobalConstant.ADAPTER.replace("-",""),adapterPom);
        pomBeanMap.put(GlobalConstant.START.replace("-",""),startPom);
        pomBeanMap.put(GlobalConstant.CLIENT.replace("-",""),clientPom);
        pomBeanMap.put(GlobalConstant.APP.replace("-",""),appPom);
        pomBeanMap.put(GlobalConstant.DOMAIN.replace("-",""),domainPom);
        pomBeanMap.put(GlobalConstant.INFRAST.replace("-",""),infrastPom);
        pomBeanMap.put(GlobalConstant.FEIGN_API.replace("-",""),feignApiPom);

        return pomBeanMap;
    }


    /**
     * 获取项目配置文件配置
     * @return
     */
    public List<ConfigFileBean> getConfigFileBean(){
        List<ConfigFileBean> list = new ArrayList<>();
        if(StringUtils.isEmpty(configFile)){
            return list;
        }
        String [] configFileArr = configFile.split(",");
        for (String configStr : configFileArr){
            String [] moduleConfigArr = configStr.split(":");
            String moduleName = moduleConfigArr[1];
            String [] configArr = moduleConfigArr[0].split("_");
            ConfigFileBean configFileBean = ConfigFileBean.getInstance(configArr[0],configArr[1], moduleName);
            configFileBean.setTemplateName(moduleConfigArr[0]);
            list.add(configFileBean);
        }
        return list;
    }

    /**
     * 返回配置的参数验证服务bean
     * @return
     */
    public List<String> getValidateBeanList(){
        if(StringUtils.isEmpty(this.validateBeans)){
            return Lists.newArrayList();
        }
        if(!this.validateBeans.contains(",")){
            return Lists.newArrayList(this.validateBeans);
        }else {
            return Lists.newArrayList(this.validateBeans.split(","));
        }
    }


}