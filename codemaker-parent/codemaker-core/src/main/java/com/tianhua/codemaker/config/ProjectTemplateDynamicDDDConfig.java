package com.tianhua.codemaker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

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
@PropertySource("classpath:projecttemplate-dynamicddd.properties")
public class ProjectTemplateDynamicDDDConfig {


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

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }
}