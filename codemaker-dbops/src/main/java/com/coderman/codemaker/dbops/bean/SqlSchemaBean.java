package com.coderman.codemaker.dbops.bean;

import java.util.List;
import java.util.Map;

/**
 * description: SqlSchemaBean <br>
 * date: 2020/8/9 12:38 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 * 每个应用下的子数据库分库分表映射bean
 */
public class SqlSchemaBean {
    /**
     * 子模块或者子应用数据库名称，如snail_app_room
     */
    private String schemaName;
    /**
     * 子模块或者子应用数据库里的sql内容
     */
    private Map<String,List<String>> tableSchemaMap;

    /**
     * 子模块或者子应用数据库里的分库数量 正整数类型,对应dbsharding.txt文件
     */
    private Integer dbSharding;

    /**
     * 子模块或者子应用数据库里的分库数量 正整数类型,对应tablesharding.txt文件
     */
    private Map<String,Integer> tableShardingMap;

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public Map<String, List<String>> getTableSchemaMap() {
        return tableSchemaMap;
    }

    public void setTableSchemaMap(Map<String, List<String>> tableSchemaMap) {
        this.tableSchemaMap = tableSchemaMap;
    }

    public Integer getDbSharding() {
        return dbSharding;
    }

    public void setDbSharding(Integer dbSharding) {
        this.dbSharding = dbSharding;
    }

    public Map<String, Integer> getTableShardingMap() {
        return tableShardingMap;
    }

    public void setTableShardingMap(Map<String, Integer> tableShardingMap) {
        this.tableShardingMap = tableShardingMap;
    }
}
