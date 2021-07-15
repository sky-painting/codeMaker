package com.coderman.codemaker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * description: ProjectTemplateConfig <br>
 * date: 2020/7/7 22:14 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
@Configuration
@Component
@PropertySource( "classpath:projecttemplate-springboot.properties")
public class ProjectTemplateSpringbootConfig {



    @Value(value = "${codemaker.global.dbName}")
    private String dbName;

    @Value(value = "${codemaker.pom.projectName}")
    private String projectName;

    @Value(value = "${codemaker.pom.groupId}")
    private String groupId;

    @Value(value = "${codemaker.pom.artifactId}")
    private String artifactId;

    @Value(value = "${codemaker.pom.version}")
    private String version;

    @Value(value = "${codemaker.code.outpath}")
    private String outPath;

    /**
     * 应用服务的plantUML类图文件,不配置则走基于数据表的方式生成代码
     */
    @Value("${codemaker.domain.plantuml}")
    private String plantumlName;

    public String getPlantumlName() {
        return plantumlName;
    }

    public void setPlantumlName(String plantumlName) {
        this.plantumlName = plantumlName;
    }

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
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

    @Override
    public String toString() {
        return "ProjectTemplateSpringbootConfig{" +
                "dbName='" + dbName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", groupId='" + groupId + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", version='" + version + '\'' +
                ", outPath='" + outPath + '\'' +
                ", plantumlName='" + plantumlName + '\'' +
                '}';
    }
}
