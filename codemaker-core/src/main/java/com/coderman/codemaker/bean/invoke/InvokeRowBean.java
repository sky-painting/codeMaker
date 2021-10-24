package com.coderman.codemaker.bean.invoke;

import java.util.Objects;

/**
 * Description:动态绘制的调用代码行
 * date: 2021/10/21
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class InvokeRowBean {
    /**
     * 被调用方的类名
     */
    private String providerClassName;
    /**
     * 被调用方的方法名
     */
    private String providerMethodName;

    /**
     * 被调用方的方法参数value值
     * 如:null,xxxBo
     */
    private String [] providerMethodParamValueArr;

    /**
     * 被调用方的方法参数类型
     * 如:XXXBO xxxBo
     */
    private String [] providerMethodParamTypeArr;

    /**
     * 被调用方的方法返回值类型
     */
    private String returnClassName;

    /**
     * 被调用方的方法返回值
     */
    private String returnClassValue;

    public String getProviderClassName() {
        return providerClassName;
    }

    public void setProviderClassName(String providerClassName) {
        this.providerClassName = providerClassName;
    }

    public String getProviderMethodName() {
        return providerMethodName;
    }

    public void setProviderMethodName(String providerMethodName) {
        this.providerMethodName = providerMethodName;
    }

    public String[] getProviderMethodParamValueArr() {
        return providerMethodParamValueArr;
    }

    public void setProviderMethodParamValueArr(String[] providerMethodParamValueArr) {
        this.providerMethodParamValueArr = providerMethodParamValueArr;
    }

    public String[] getProviderMethodParamTypeArr() {
        return providerMethodParamTypeArr;
    }

    public void setProviderMethodParamTypeArr(String[] providerMethodParamTypeArr) {
        this.providerMethodParamTypeArr = providerMethodParamTypeArr;
    }

    public String getReturnClassName() {
        return returnClassName;
    }

    public void setReturnClassName(String returnClassName) {
        this.returnClassName = returnClassName;
    }

    public String getReturnClassValue() {
        return returnClassValue;
    }

    public void setReturnClassValue(String returnClassValue) {
        this.returnClassValue = returnClassValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvokeRowBean that = (InvokeRowBean) o;
        return Objects.equals(providerClassName, that.providerClassName) &&
                Objects.equals(providerMethodName, that.providerMethodName) &&
                Objects.equals(returnClassName, that.returnClassName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(providerClassName, providerMethodName, returnClassName);
    }
}
