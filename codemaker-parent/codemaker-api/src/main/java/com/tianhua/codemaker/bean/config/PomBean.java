package com.tianhua.codemaker.bean.config;

import com.tianhua.codemaker.bean.WriteContentBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2022/1/17
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class PomBean {

    /**
     * 模块前缀
     */
    private String modulePrefix;

    private String appName;


    /**
     * pom.url
     */
    private String url;


    /**
     * 模块名称
     */
    private String moduleName;


    private String groupId;
    private String artifactId;
    private String version;


    private String templateCode;

    private String moduleCode;

    /**
     * 依赖的mv
     */
    private List<GAVBean> dependencyList;


    public Map<String,Object> buildVarMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("modulePrefix",modulePrefix);
        map.put("groupId",groupId);
        map.put("artifactId",artifactId);
        map.put("version",version);
        map.put("appName",appName);
        map.put("dependencyList",dependencyList);
        return map;
    }

    public WriteContentBean buildWriteContentBean(String content,String templateCode){
        return WriteContentBean.builder()
                .customCodeElement(true)
                .content(content)
                .templateName(templateCode).build();
    }


    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getModulePrefix() {
        return modulePrefix;
    }

    public void setModulePrefix(String modulePrefix) {
        this.modulePrefix = modulePrefix;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
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

    public List<GAVBean> getDependencyList() {
        return dependencyList;
    }

    public void setDependencyList(List<GAVBean> dependencyList) {
        this.dependencyList = dependencyList;
    }


    public void buildModule(String aliasName, String moduleCode){
        this.setModuleName(aliasName+ moduleCode);
        this.setModulePrefix(aliasName);
        this.setModuleCode(moduleCode.replace("-",""));
    }

    public void buildGAV(String groupId, String artifactId, String version){
        this.setGroupId(groupId);
        this.setArtifactId(artifactId);
        this.setVersion(version);
    }


}
