package com.tianhua.codemaker.bean.config;

import com.tianhua.codemaker.bean.component.ComponentConfigBean;
import lombok.Data;

import java.util.Map;

/**
 * Description:项目基本配置服务
 * date: 2022/3/9
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Data
public class ProjectBean {
    /**
     * 全局包名
     */
    private String packageName;

    /**
     * 应用名
     */
    private String applicationName;

    /**
     * 数据库名
     */
    private String dataBaseName;

    /**
     * 业务配置信息
     */
    private Map<String, ComponentConfigBean> configFileMap;

}
