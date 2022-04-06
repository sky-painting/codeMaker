package com.tianhua.codemaker.bean.plantuml;

/**
 * Description:范型模型
 * date: 2022/3/14
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class PatternBean {

    /**
     * 范型类型标示
     * 或者类名
     */
    public String tagClass;

    /**
     * 范型类继承的父类
     * ? extends T
     */
    public boolean withExtends;

    /**
     * 范型类的父类
     * ? super T
     */
    public Boolean withSuper;


    public String getTagClass() {
        return tagClass;
    }

    public void setTagClass(String tagClass) {
        this.tagClass = tagClass;
    }

    public boolean isWithExtends() {
        return withExtends;
    }

    public void setWithExtends(boolean withExtends) {
        this.withExtends = withExtends;
    }

    public Boolean getWithSuper() {
        return withSuper;
    }

    public void setWithSuper(Boolean withSuper) {
        this.withSuper = withSuper;
    }
}
