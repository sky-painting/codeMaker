package com.coderman.codemaker.dbergenerate.bean;

import java.util.Map;

/**
 * Description:e-r图示例
 * date: 2020/10/20
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ErPictureBean {
    private Map<String,String> mainEntityMap;
    private String relationStr;

    public Map<String, String> getMainEntityMap() {
        return mainEntityMap;
    }

    public void setMainEntityMap(Map<String, String> mainEntityMap) {
        this.mainEntityMap = mainEntityMap;
    }

    public String getRelationStr() {
        return relationStr;
    }

    public void setRelationStr(String relationStr) {
        this.relationStr = relationStr;
    }
}
