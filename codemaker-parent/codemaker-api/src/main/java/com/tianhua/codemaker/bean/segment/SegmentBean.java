package com.tianhua.codemaker.bean.segment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description;代码片段模型
 * date; 2022/1/25
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class SegmentBean {

    public SegmentBean(){}
    public SegmentBean(String identifer){
        this.identifer = identifer;
    }

    public SegmentBean(String identifer, String code){
        this.identifer = identifer;
        this.code = code;
    }
    /**
     * 代码段内容
     */
    private String code;
    /**
     * 代码段中需要导入的包
     */
    private List<String> packageNameList = new ArrayList<>();
    /**
     * 代码段类型标示
     */
    private String identifer;
    /**
     * 代码段模板
     */
    private String segmentTemplate;

    /**
     * 代码段模板对应的变量列表map
     */
    private Map<String,Object> templateVarMap;

    /**
     * 关联的代码类名称
     */
    private String relationClassName;




    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getPackageNameList() {
        return packageNameList;
    }

    public void setPackageNameList(List<String> packageNameList) {
        this.packageNameList = packageNameList;
    }

    public String getIdentifer() {
        return identifer;
    }

    public void setIdentifer(String identifer) {
        this.identifer = identifer;
    }

    public String getSegmentTemplate() {
        return segmentTemplate;
    }

    public void setSegmentTemplate(String segmentTemplate) {
        this.segmentTemplate = segmentTemplate;
    }

    public Map<String, Object> getTemplateVarMap() {
        return templateVarMap;
    }

    public void setTemplateVarMap(Map<String, Object> templateVarMap) {
        this.templateVarMap = templateVarMap;
    }

    public String getRelationClassName() {
        return relationClassName;
    }

    public void setRelationClassName(String relationClassName) {
        this.relationClassName = relationClassName;
    }


    public static SegmentBean getInstance(String identifer, String code ){
        return new SegmentBean(identifer, code);
    }

    public void importPackageName(String packageName){
        this.packageNameList.add(packageName);
    }

}
