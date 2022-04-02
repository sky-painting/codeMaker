package com.tianhua.codemaker.bean.config;

/**
 * Description:文件配置bean
 * date: 2022/1/23
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ConfigFileBean {

    /**
     * 文件名
     */
    private String configFileName;

    /**
     * 文件后缀
     */
    private String configFileSuffix;

    /**
     * 所属模块
     */
    private String moduleName;

    /**
     * ftl文件模板名称
     */
    private String templateName;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public ConfigFileBean(){}

    public ConfigFileBean(String configFileName, String configFileSuffix, String moduleName){
        this.configFileName = configFileName;
        this.configFileSuffix = configFileSuffix;
        this.moduleName = moduleName;
    }


    /**
     * 构建配置文件实例
     * @param configFileName
     * @param configFileSuffix
     * @param moduleName
     * @return
     */
    public static ConfigFileBean getInstance(String configFileName, String configFileSuffix, String moduleName){
        return new ConfigFileBean(configFileName,configFileSuffix,moduleName);
    }

    public String getConfigFileSuffix() {
        return configFileSuffix;
    }

    public void setConfigFileSuffix(String configFileSuffix) {
        this.configFileSuffix = configFileSuffix;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getConfigFileName() {
        return configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }
}
