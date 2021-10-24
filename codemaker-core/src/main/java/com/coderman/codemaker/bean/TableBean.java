package com.coderman.codemaker.bean;

import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 2018-7-19.
 *
 * @author: fanchunshuai
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
     * 使用的存储引擎
     */
    private String engineName;

    /**
     * 字符集
     */
    private String tableCollation;

    /**
     * 表记录数
     */
    private Long tableRows;

    /**
     * 表名对应的驼峰式命名
     */
    private String humpTableName;

    /**
     * 表名对应的类名
     */
    private String humpClassName;
    /**
     * 列名列表，字符串逗号分割
     */
    private String columnNameList;

    /**
     * 插入sql value替换字符串
     */
    private String insertColumnNameList;

    /**
     * 插入sql value替换字符串
     */
    private String insertColumnNames;
    /**
     * 修改sql value替换字符串
     */
    private String updateColumnNameList;


    /**
     * 转换对应的class信息，打通dynamicddd派生类生成
     */
    private ClassBean classBean;

    public ClassBean convertToClassBean(String packageName){
        ClassBean classBean = new ClassBean();
        classBean.setClassName(this.getHumpClassName()+"DO");
        classBean.setPackageName(packageName);
        classBean.setClassDesc(tableComment);
        classBean.setPlantUMLPackage("dao.dataobject");
        classBean.setMethodBeanList(Lists.newArrayList());

        return classBean;
    }

    /**
     * 将mapper层整体注册到plantUmlContextBean 上下文中
     * @param packageName
     * @return
     */
    public InterfaceBean convertToMapperInterface(String packageName){
        InterfaceBean interfaceBean = new InterfaceBean();
        interfaceBean.setClassName(this.getHumpClassName()+"Mapper");
        interfaceBean.setPackageName(packageName);
        interfaceBean.setClassDesc(tableComment);
        interfaceBean.setPlantUMLPackage("dao.mapper");

        List<MethodBean> methodBeanList = new ArrayList<>();
        String paramType = this.getHumpClassName()+"DO";
        String varName = paramType.substring(0,1).toLowerCase()+paramType.substring(1);
        methodBeanList.add(new MethodBean("insert("+paramType+" "+varName+")","int"));
        methodBeanList.add(new MethodBean("update("+paramType+" "+varName+")","int"));
        methodBeanList.add(new MethodBean("getAll()","Long"));
        methodBeanList.add(new MethodBean("getById(Long id)","Long"));
        methodBeanList.add(new MethodBean("deleteById(Long id)","int"));

        interfaceBean.setMethodBeanList(methodBeanList);
        return interfaceBean;
    }

    public ClassBean getClassBean() {

        return classBean;
    }

    public void setClassBean(ClassBean classBean) {
        this.classBean = classBean;
    }

    public String getInsertColumnNames() {
        return insertColumnNames;
    }

    public void setInsertColumnNames(String insertColumnNames) {
        this.insertColumnNames = insertColumnNames;
    }

    public String getUpdateColumnNameList() {
        return updateColumnNameList;
    }

    public void setUpdateColumnNameList(String updateColumnNameList) {
        this.updateColumnNameList = updateColumnNameList;
    }

    public String getInsertColumnNameList() {
        return insertColumnNameList;
    }

    public void setInsertColumnNameList(String insertColumnNameList) {
        this.insertColumnNameList = insertColumnNameList;
    }

    public String getColumnNameList() {
        return columnNameList;
    }

    public void setColumnNameList(String columnNameList) {
        this.columnNameList = columnNameList;
    }

    public String getHumpClassName() {
        return humpClassName;
    }

    public void setHumpClassName(String humpClassName) {
        this.humpClassName = humpClassName;
    }

    public String getHumpTableName() {
        return humpTableName;
    }

    public void setHumpTableName(String humpTableName) {
        this.humpTableName = humpTableName;
    }

    public Long getTableRows() {
        return tableRows;
    }

    public void setTableRows(Long tableRows) {
        this.tableRows = tableRows;
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

    public String getEngineName() {
        return engineName;
    }

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    public String getTableCollation() {
        return tableCollation;
    }

    public void setTableCollation(String tableCollation) {
        this.tableCollation = tableCollation;
    }
}
