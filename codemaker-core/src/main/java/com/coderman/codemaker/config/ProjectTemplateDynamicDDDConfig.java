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
@PropertySource( "classpath:projecttemplate-dynamicddd.properties")
public class ProjectTemplateDynamicDDDConfig {

    /**
     * 全局包名
     */
    @Value(value = "${dynamicddd.global.package}")
    private String globalPackage;

    /**
     * 项目作者
     */
    @Value(value = "${dynamicddd.global.author}")
    private String author;

    @Value(value = "${dynamicddd.code.outpath}")
    private String outPath;


    @Value("${dynamicddd.domain.plantuml}")
    private String plantumlFileName;

    public String getPlantumlFileName() {
        return plantumlFileName;
    }

    public void setPlantumlFileName(String plantumlFileName) {
        this.plantumlFileName = plantumlFileName;
    }

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

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }
}