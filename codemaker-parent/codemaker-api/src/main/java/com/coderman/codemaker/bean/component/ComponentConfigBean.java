package com.coderman.codemaker.bean.component;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/11/22
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ComponentConfigBean {

    /**
     * maven GAV
     */
    private String artifactId;

    private String groupId;

    private String version;

    private String desc;

    /**
     * 如果GAV找不到则通过jarPath找
     */
    private String jarPath;

    /**
     * 目标类所在的包名
     */
    private String packageName;

    /**
     * 组件代表的别名
     */
    private String serviceName;

    /**
     * 组件类型--CompTypeEnum
     */
    private String compType;

    /**
     * 类-类型
     */
    private String classType;


    /**
     * 组件官网
     */
    private String compSite;

    /**
     * 组件描述
     */
    private String compDesc;

    /**
     * 接口列表
     */
    private List<String> interfaceList = new ArrayList<>();

    /**
     * 类列表
     */
    private List<String> classList = new ArrayList<>();

    /**
     * 枚举列表
     */
    private List<String> enumList = new ArrayList<>();

    /**
     * 注解列表
     */
    private List<String> annotationList = new ArrayList<>();

    /**
     * 方法列表
     */
    private List<String> methodList = new ArrayList<>();

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getJarPath() {
        return jarPath;
    }

    public void setJarPath(String jarPath) {
        this.jarPath = jarPath;
    }


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getInterfaceList() {
        return interfaceList;
    }

    public List<String> getClassList() {
        return classList;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }


    public List<String> getMethodList() {
        return methodList;
    }

    public List<String> getEnumList() {
        return enumList;
    }

    public List<String> getAnnotationList() {
        return annotationList;
    }

    public String getCompSite() {
        return compSite;
    }

    public void setCompSite(String compSite) {
        this.compSite = compSite;
    }

    public String getCompDesc() {
        return compDesc;
    }

    public void setCompDesc(String compDesc) {
        this.compDesc = compDesc;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }


    public String getCompType() {
        return compType;
    }

    public void setCompType(String compType) {
        this.compType = compType;
    }

    public void addClass(String clazz){
        if(StringUtils.isNotEmpty(this.getPackageName())){
            clazz = this.getPackageName()+"."+clazz;
        }
        this.classList.add(clazz);
    }

    public void addInterface(String interfaceName){
        if(StringUtils.isNotEmpty(this.getPackageName())){
            interfaceName = this.getPackageName()+"."+interfaceName;
        }
        this.interfaceList.add(interfaceName);
    }

    public void addAnnotation(String annotation){
        if(StringUtils.isNotEmpty(this.getPackageName())){
            annotation = this.getPackageName()+"."+annotation;
        }
        this.annotationList.add(annotation);
    }


    public void addEnum(String enumClass){
        if(StringUtils.isNotEmpty(this.getPackageName())){
            enumClass = this.getPackageName()+"."+enumClass;
        }
        this.enumList.add(enumClass);
    }

    public void addMethod(String methodStr){
        if(StringUtils.isNotEmpty(methodStr)){
            this.methodList.add(methodStr);
        }
    }


    /**
     * 构建需要读取的jar包绝对路径,带jar的
     * @return
     */
    public String buildRepoJarPath(String mvnRepoPath){
        String path = mvnRepoPath + "/" + this.getGroupId().replace(".","/")+"/"+this.getArtifactId()+"/"+this.getVersion();
        String jarName = this.getArtifactId()+"-"+this.getVersion()+".jar";
        return path + "/" + jarName+"!/";
    }

    /**
     * 构建需要读取的jar包绝对路径
     * @return
     */
    public String buildJarPath(String mvnRepoPath){
        String path = mvnRepoPath.replace("jar:file://","") + "/" + this.getGroupId().replace(".","/")+"/"+this.getArtifactId()+"/"+this.getVersion();
        String jarName = this.getArtifactId()+"-"+this.getVersion()+".jar";
        return path + "/" + jarName;
    }

    @Override
    public String toString() {
        return "ComponentConfigBean{" +
                "artifactId='" + artifactId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", version='" + version + '\'' +
                ", desc='" + desc + '\'' +
                ", jarPath='" + jarPath + '\'' +
                ", packageName='" + packageName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", compType='" + compType + '\'' +
                ", classType='" + classType + '\'' +
                ", compSite='" + compSite + '\'' +
                ", compDesc='" + compDesc + '\'' +
                '}';
    }
}
