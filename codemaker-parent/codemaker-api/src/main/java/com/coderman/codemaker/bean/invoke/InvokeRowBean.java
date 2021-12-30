package com.coderman.codemaker.bean.invoke;

import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.FieldBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import org.apache.commons.lang3.StringUtils;

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
     * 被调用方的类名对应的变量名
     */
    private String providerClassNameVar;
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


    public String getProviderClassNameVar() {
        return providerClassNameVar;
    }

    public void setProviderClassNameVar(String providerClassNameVar) {
        this.providerClassNameVar = providerClassNameVar;
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


    /**
     * 转换成字符串调用行
     * @return
     */
    public String buildInvokeContent(){
        StringBuilder builder = new StringBuilder();
        if(!StringUtils.isEmpty(this.getReturnClassName()) && StringUtils.isNotEmpty(this.getReturnClassValue())){
            builder.append(this.getReturnClassName()+" ");
            builder.append(this.getReturnClassValue()+" = ");
        }
        builder.append(this.getProviderClassNameVar()+".");
        builder.append(this.getProviderMethodName());
        if(this.getProviderMethodParamTypeArr() == null || this.getProviderMethodParamTypeArr().length == 0){
            builder.append("()");
            return builder.toString();
        }
        builder.append("(");

        for (String paramValue : this.getProviderMethodParamValueArr()){
            builder.append(paramValue+", ");
        }
        return  builder.substring(0,builder.length() - 2)+")";
    }

    /**
     * 根据依赖的代码行内容，完善本身的代码行内容
     *
     * 这里主要是完善本身的方法入参内容，将上一行调用内容的变量类和变量名应用到当前行
     * @param invokeRowBean
     * @return
     */
    public String refreshInvokeContent(InvokeRowBean invokeRowBean, PlantUmlContextBean plantUmlContextBean){
        String  preRowClassName = invokeRowBean.getReturnClassName();
        if(preRowClassName.contains(".")){
            String [] arr = preRowClassName.split("\\.");
            preRowClassName = arr[arr.length - 1].replace(">","");
        }
        if (StringUtils.isEmpty(preRowClassName) || this.getProviderMethodParamTypeArr() == null || this.getProviderMethodParamTypeArr().length == 0) {
            return buildInvokeContent();
        }

        for (int i = 0;i < this.getProviderMethodParamTypeArr().length;i++){
            String currentRowClassName = this.getProviderMethodParamTypeArr()[i];
            String currentRowClassNameType = currentRowClassName;
            String currentRowClassNameTypeVar = currentRowClassName;
            if(currentRowClassNameType.contains(" ")){
                currentRowClassNameType = currentRowClassName.split(" ")[0];
                currentRowClassNameTypeVar = currentRowClassName.split(" ")[1];
            }
            //通过类型推导
            if(preRowClassName.equals(currentRowClassNameType)){
                this.getProviderMethodParamValueArr()[i] = invokeRowBean.getReturnClassValue();
            }else {
                //通过变量名推导，当前调用行的参数名与上一行的返回值是否存在has a关系，如getBySystemName(String systemName),上一行的返回值刚好是SystemBO
                ClassBean classBean = plantUmlContextBean.getClassBeanMap().get(preRowClassName);
                if(classBean == null){
                    continue;
                }
                for (FieldBean fieldBean : classBean.getFieldBeanList()){
                    String fieldName = fieldBean.getFieldName();
                    String fieldNameVar = fieldName;
                    String fieldNameType = fieldName;

                    if (fieldName.contains(" ")){
                        fieldNameVar = fieldName.split(" ")[1];
                        fieldNameType = fieldName.split(" ")[0];
                    }
                    if(fieldNameVar.equals(currentRowClassNameTypeVar) && fieldNameType.equals(currentRowClassNameType)){
                        this.getProviderMethodParamValueArr()[i] = invokeRowBean.getReturnClassValue()+"."+fieldBean.buildGetterMethodName();
                    }
                }
            }
        }

        return buildInvokeContent();
    }


    public void buildParamValueArr(){
        if(this.getProviderMethodParamTypeArr() == null || this.getProviderMethodParamTypeArr().length == 0){
            return;
        }
        this.setProviderMethodParamValueArr(new String[this.getProviderMethodParamTypeArr().length]);
    }
}
