package com.coderman.codemaker.bean.plantuml;

/**
 * Description:
 * date: 2021/6/28
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class MethodBean {

    /**
     * 方法名称，
     * 包括参数，括号
     */
    private String methodName;
    /**
     * 访问权限
     */
    private String visibility;

    /**
     * 方法描述
     */
    private String desc;

    /**
     * 是否是静态属性
     */
    private boolean isStatic;

    /**
     * 方法返回值
     */
    private String returnClass;

    /**
     * 方法返回对象所在包
     */
    private String returnClassPackage;

    /**
     * 方法返回体
     */
    private String returnBody;

    /**
     * controller方法的请求路径
     */
    private String pathValue;

    public String getPathValue() {
        return pathValue;
    }

    public void setPathValue(String pathValue) {
        this.pathValue = pathValue;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public String getReturnClass() {
        return returnClass;
    }

    public void setReturnClass(String returnClass) {
        this.returnClass = returnClass;
    }

    public String getReturnClassPackage() {
        return returnClassPackage;
    }

    public void setReturnClassPackage(String returnClassPackage) {
        this.returnClassPackage = returnClassPackage;
    }


    public String getReturnBody() {
        return returnBody;
    }

    public void setReturnBody(String returnBody) {
        this.returnBody = returnBody;
    }
}
