package com.tianhua.codemaker.bean.component;


import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2021/11/22
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Data
public class ComponentConfigBean {

    /**
     * maven GAV
     */
    private String artifactId;

    private String groupId;

    private String version;


    private String type;

    private String scope;

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
     * 组件配置名称
     *
     */
    private String configName;

    /**
     * 组件要排除的依赖项
     */
    private String exclusionGAList;

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
    private Map<String, List<String>> methodMap = new HashMap<>();

    /**
     * 属性列表
     */
    private Map<String, List<String>> fieldMap = new HashMap<>();

    /**
     * 组件配置内容
     */
    private List<String> configList = new ArrayList<>();

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

    public void addMethod(String className, String methodStr){
        List<String> methodList = this.methodMap.get(className);
        if(CollectionUtils.isEmpty(methodList)){
            methodList = new ArrayList<>();
            methodList.add(methodStr);
            methodMap.put(className, methodList);
            return;
        }
        methodList.add(methodStr);
        methodMap.put(className, methodList);
    }

    public void addField(String className, String fieldStr){
        List<String> fieldList = this.fieldMap.get(className);

        if(CollectionUtils.isEmpty(fieldList)){
            fieldList = new ArrayList<>();
            fieldList.add(fieldStr);
            fieldMap.put(className, fieldList);
            return;
        }
        fieldList.add(fieldStr);
        fieldMap.put(className, fieldList);
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
