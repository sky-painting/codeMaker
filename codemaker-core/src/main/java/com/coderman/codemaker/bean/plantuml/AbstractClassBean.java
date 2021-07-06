package com.coderman.codemaker.bean.plantuml;

import java.util.List;

/**
 * Description:
 * 抽象plantuml 类
 * date: 2021/6/28
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public abstract class AbstractClassBean {
    /**
     * 类名
     */
    protected String className;

    /**
     * 所在包
     */
    protected String packageName;
    /**
     * 所属模块
     */
    protected String belongModel;

    /**
     * 类描述
     */
    protected String classDesc;

    /**
     * 方法
     */
    private List<MethodBean> methodBeanList;

    /**
     * 需要引入的class包名
     */
    private List<String> importClassList;
    /**
     * 属性列表
     *
     */
    private List<FieldBean> fieldBeanList;



    /**
     * 实现接口
     */
    private InterfaceBean implInterfaceBean;

    /**
     * 继承类列表
     */
    private String relationClassStr;
    /**
     * 项目作者
     */
    private String author;

    /**
     * 所在plantUML的包名
     */
    private String plantUMLPackage;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getPlantUMLPackage() {

        return plantUMLPackage;
    }

    public void setPlantUMLPackage(String plantUMLPackage) {
        this.plantUMLPackage = plantUMLPackage;
    }

    public String getRelationClassStr() {
        return relationClassStr;
    }

    public void setRelationClassStr(String relationClassStr) {
        this.relationClassStr = relationClassStr;
    }

    public InterfaceBean getImplInterfaceBean() {
        return implInterfaceBean;
    }

    public void setImplInterfaceBean(InterfaceBean implInterfaceBean) {
        this.implInterfaceBean = implInterfaceBean;
    }


    public List<MethodBean> getMethodBeanList() {
        return methodBeanList;
    }

    public void setMethodBeanList(List<MethodBean> methodBeanList) {
        this.methodBeanList = methodBeanList;
    }

    public List<FieldBean> getFieldBeanList() {
        return fieldBeanList;
    }

    public void setFieldBeanList(List<FieldBean> fieldBeanList) {
        this.fieldBeanList = fieldBeanList;
    }

    public List<String> getImportClassList() {
        return importClassList;
    }

    public void setImportClassList(List<String> importClassList) {
        this.importClassList = importClassList;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getBelongModel() {
        return belongModel;
    }

    public void setBelongModel(String belongModel) {
        this.belongModel = belongModel;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }
}
