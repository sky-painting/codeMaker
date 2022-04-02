package com.tianhua.codemaker.bean.config;

import lombok.Data;

/**
 * Description:
 * date: 2022/1/17
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Data
public class GAVBean {
    private String groupId;
    private String artifactId;
    private String version;

    private String type;

    private String scope;

    private String exclusionGAContent;


    /**
     * 所属服务组件名称
     */
    private String componentName;

    public GAVBean(){}
    public GAVBean(String componentName){
        this.componentName = componentName;
    }

}
