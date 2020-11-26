package com.coderman.codemaker.dbops.bean;

import java.util.Map;

/**
 * description: AppSqlSchemaBean <br>
 * date: 2020/8/9 12:36 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 * 大型应用的分库分表的配置bean
 */
public class AppSqlSchemaBean {
    /**
     * 应用名
     */
    private String appName;
    /**
     * 子应用或者子模块中的分库分表配置
     *
     */
    private Map<String,SqlSchemaBean> sqlSchemaBeanMap;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Map<String, SqlSchemaBean> getSqlSchemaBeanMap() {
        return sqlSchemaBeanMap;
    }

    public void setSqlSchemaBeanMap(Map<String, SqlSchemaBean> sqlSchemaBeanMap) {
        this.sqlSchemaBeanMap = sqlSchemaBeanMap;
    }
}
