package com.coderman.codemaker.dberparse.bean;

import java.util.List;

/**
 * Description:
 * date: 2021/8/12
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 * 表entity模型
 */
public class EntityBean {
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 表描述
     */
    private String tableDesc;
    /**
     * 表字段列表
     */
    private List<EntityFieldBean> columnBeanList;
    /**
     * 表索引列表
     */
    private List<EntityFieldBean> indexBeanList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }

    public List<EntityFieldBean> getColumnBeanList() {
        return columnBeanList;
    }

    public void setColumnBeanList(List<EntityFieldBean> columnBeanList) {
        this.columnBeanList = columnBeanList;
    }

    public List<EntityFieldBean> getIndexBeanList() {
        return indexBeanList;
    }

    public void setIndexBeanList(List<EntityFieldBean> indexBeanList) {
        this.indexBeanList = indexBeanList;
    }
}
