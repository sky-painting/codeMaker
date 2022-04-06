package com.tianhua.codemaker.bean;

import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.FieldBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.bean.plantuml.MethodBean;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
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

    /**
     * 将tablebean对象转换为classBean对象，代表DO类对象
     * @param packageName
     * @param columnBeanList
     * @return
     */
    public ClassBean convertToClassBean(String packageName,List<ColumnBean> columnBeanList){
        ClassBean classBean = new ClassBean();
        classBean.setClassName(this.getHumpClassName()+"DO");
        classBean.setPackageName(packageName);
        classBean.setClassDesc(tableComment);
        classBean.setPlantUMLPackage("dao.dataobject");

        classBean.setMethodBeanList(Lists.newArrayList());
        classBean.setTableBean(this);
        classBean.setColumnBeanList(columnBeanList);

        if(CollectionUtils.isNotEmpty(columnBeanList)){
            List<FieldBean> fieldBeanList = new ArrayList<>();
            columnBeanList.forEach(columnBean -> fieldBeanList.add(columnBean.convert2FieldBean()));
            classBean.setFieldBeanList(fieldBeanList);
        }else {
            classBean.setFieldBeanList(Lists.newArrayList());
        }
        return classBean;
    }

    /**
     * 将mapper层整体注册到plantUmlContextBean 上下文中
     * @param packageName
     * @return
     */
    public InterfaceBean convertToMapperInterface(String packageName,List<ColumnBean> columnBeanList, boolean useMybatisPlus){
        InterfaceBean interfaceBean = new InterfaceBean();
        interfaceBean.setClassName(this.getHumpClassName()+"Mapper");
        interfaceBean.setPackageName(packageName);
        interfaceBean.setClassDesc(tableComment);
        interfaceBean.setPlantUMLPackage("dao.mapper");
        String doPackageName = packageName.replace("mapper","dataobject");
        String importDO = doPackageName+"."+this.getHumpClassName()+"DO";
        interfaceBean.setImportClassList(Lists.newArrayList(importDO));
        if(useMybatisPlus){
            interfaceBean.setMethodBeanList(Lists.newArrayList());
        }else {
            interfaceBean.setMethodBeanList(getDefaultMapperMethod());
        }
        interfaceBean.setTableBean(this);
        interfaceBean.setColumnBeanList(columnBeanList);


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


    public List<MethodBean> getDefaultMapperMethod(){
        List<MethodBean> methodBeanList = new ArrayList<>();
        String paramType = this.getHumpClassName()+"DO";
        String varName = paramType.substring(0,1).toLowerCase()+paramType.substring(1);
        methodBeanList.add(new MethodBean("insert("+paramType+" "+varName+")","long","保存数据记录"));
        methodBeanList.add(new MethodBean("update("+paramType+" "+varName+")","int","更新数据记录"));
        methodBeanList.add(new MethodBean("getAll()","List<"+paramType+">","获取所有数据记录"));
        methodBeanList.add(new MethodBean("getById(Long id)",paramType,"根据ID获取单条记录"));
        methodBeanList.add(new MethodBean("deleteById(Long id)","int","根据ID删除单条记录"));
        methodBeanList.stream().forEach(methodBean -> methodBean.buildParamArr());
        return  methodBeanList;
    }
}
