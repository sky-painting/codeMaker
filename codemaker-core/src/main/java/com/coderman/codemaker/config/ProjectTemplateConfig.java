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
@PropertySource( "classpath:projecttemplate.properties")
public class ProjectTemplateConfig {

    @Value(value = "${codemaker.global.package}")
    private String packageName;

    @Value(value = "${codemaker.global.author}")
    private String author;

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

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
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
        return "ProjectTemplateConfig{" +
                "packageName='" + packageName + '\'' +
                ", author='" + author + '\'' +
                ", dbName='" + dbName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", groupId='" + groupId + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
