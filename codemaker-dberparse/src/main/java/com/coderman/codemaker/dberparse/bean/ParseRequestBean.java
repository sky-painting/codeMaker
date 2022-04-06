package com.coderman.codemaker.dberparse.bean;

import java.util.List;

/**
 * Description:
 * date: 2021/8/12
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ParseRequestBean {

    /**
     * 解析出产生的sql脚本文件存放目录
     */
    private String sqlFilePath;
    /**
     * er图的plantUML内容
     */
    private List<String> contentList;

    public String getSqlFilePath() {
        return sqlFilePath;
    }

    public void setSqlFilePath(String sqlFilePath) {
        this.sqlFilePath = sqlFilePath;
    }

    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }
}
