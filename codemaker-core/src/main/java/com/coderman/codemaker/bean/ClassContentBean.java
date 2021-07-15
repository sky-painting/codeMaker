package com.coderman.codemaker.bean;

/**
 * Description:
 * date: 2021/6/18
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ClassContentBean {
    /**
     * 文件内容
     */
    private String classContent;

    /**
     * 文件路径
     */
    private String classFilePath;


    private String childPackageName;

    private String classSuffix;

    private String humpClassName;

    /**
     * 类所在包名，包括子包
     */
    private String classPackageName;

    /**
     * 所属模块
     */
    private String modulePath;

    public String getModulePath() {
        return modulePath;
    }

    public void setModulePath(String modulePath) {
        this.modulePath = modulePath;
    }

    public String getClassPackageName() {
        return classPackageName;
    }

    public void setClassPackageName(String classPackageName) {
        this.classPackageName = classPackageName;
    }

    public String getChildPackageName() {
        return childPackageName;
    }

    public void setChildPackageName(String childPackageName) {
        this.childPackageName = childPackageName;
    }

    public String getClassSuffix() {
        return classSuffix;
    }

    public void setClassSuffix(String classSuffix) {
        this.classSuffix = classSuffix;
    }

    public String getClassFilePath() {
        return classFilePath;
    }

    public void setClassFilePath(String classFilePath) {
        this.classFilePath = classFilePath;
    }

    public String getHumpClassName() {
        return humpClassName;
    }

    public void setHumpClassName(String humpClassName) {
        this.humpClassName = humpClassName;
    }

    public String getClassContent() {
        return classContent;
    }

    public void setClassContent(String classContent) {
        this.classContent = classContent;
    }
}
