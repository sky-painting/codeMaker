package com.coderman.codemaker.dbergenerate.bean;

import java.util.List;

/**
 * Created  on 2018-7-19.
 *
 * @author: shenshuai
 * @version: V1.0
 * @Desc:表信息描述
 */
public class TableBean {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表描述
     */
    private String tableComment;

    /**
     * 表字段简单模型
     */
    private List<ColumnBean> columnBeanList;

    public List<ColumnBean> getColumnBeanList() {
        return columnBeanList;
    }

    public void setColumnBeanList(List<ColumnBean> columnBeanList) {
        this.columnBeanList = columnBeanList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
}
