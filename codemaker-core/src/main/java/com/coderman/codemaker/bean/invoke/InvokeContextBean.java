package com.coderman.codemaker.bean.invoke;

import com.coderman.codemaker.bean.plantuml.AbstractClassBean;
import com.coderman.codemaker.bean.plantuml.MethodBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.enums.dynamic.InvokeSceneTypeEnum;
import com.coderman.codemaker.enums.dynamic.ReadWriteTypeEnum;
import org.apache.commons.lang3.StringUtils;


/**
 * Description: 调用时序上下文
 * date: 2021/10/16
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class InvokeContextBean {
    /**
     * 需要绘制方法内容的方法对象
     */
    private MethodBean methodBean;

    /**
     * plantuml 调用流程图中的被调用类
     */
    private String providerClassName;

    /**
     * plantuml 调用流程图中的被调用方法
     */
    private String providerClassMethod;


    /**
     * plantuml 调用流程图中的被调用方法返回值
     */
    private String providerClassMethodReturn;


    /**
     * plantuml 调用流程图中的调用方法
     */
    private String invokerMethod;


    /**
     * 调用方向
     * 如 应用层->领域层,应用层->基础设施层,应用层->应用层,领域层->领域层
     *
     */
    private String InvokeSceneType;

    /**
     * 需要绘制方法内容的方法类型
     */
    private String methodRWType;


    /**
     * 全局对象
     */
    private PlantUmlContextBean plantUmlContextBean;

    /**
     * 调用类对象
     */
    private AbstractClassBean invokerClassBean;

    /**
     * 当前调用方调用提供方的调用行内容
     */
    private String currentInvokeRowContent;


    /**
     * 当前调用的调用行bean
     */
    private InvokeRowBean currentInvokeRowBean;

    public InvokeRowBean getCurrentInvokeRowBean() {
        return currentInvokeRowBean;
    }

    public void setCurrentInvokeRowBean(InvokeRowBean currentInvokeRowBean) {
        this.currentInvokeRowBean = currentInvokeRowBean;
    }

    public String getCurrentInvokeRowContent() {
        return currentInvokeRowContent;
    }

    public void setCurrentInvokeRowContent(String currentInvokeRowContent) {
        this.currentInvokeRowContent = currentInvokeRowContent;
    }

    public AbstractClassBean getInvokerClassBean() {
        return invokerClassBean;
    }

    public void setInvokerClassBean(AbstractClassBean invokerClassBean) {
        this.invokerClassBean = invokerClassBean;
    }

    public String getProviderClassMethodReturn() {
        return providerClassMethodReturn;
    }

    public void setProviderClassMethodReturn(String providerClassMethodReturn) {
        this.providerClassMethodReturn = providerClassMethodReturn;
    }

    public String getInvokeSceneType() {
        return InvokeSceneType;
    }

    public void setInvokeSceneType(String invokeSceneType) {
        InvokeSceneType = invokeSceneType;
    }

    public String getMethodRWType() {
        return methodRWType;
    }

    public void setMethodRWType(String methodRWType) {
        this.methodRWType = methodRWType;
    }

    public MethodBean getMethodBean() {
        return methodBean;
    }

    public void setMethodBean(MethodBean methodBean) {
        this.methodBean = methodBean;
    }


    public String getInvokerMethod() {
        return invokerMethod;
    }

    public void setInvokerMethod(String invokerMethod) {
        this.invokerMethod = invokerMethod;
    }

    public String getProviderClassName() {
        return providerClassName;
    }

    public void setProviderClassName(String providerClassName) {
        this.providerClassName = providerClassName;
    }

    public String getProviderClassMethod() {
        return providerClassMethod;
    }

    public void setProviderClassMethod(String providerClassMethod) {
        this.providerClassMethod = providerClassMethod;
    }

    public PlantUmlContextBean getPlantUmlContextBean() {
        return plantUmlContextBean;
    }

    public void setPlantUmlContextBean(PlantUmlContextBean plantUmlContextBean) {
        this.plantUmlContextBean = plantUmlContextBean;
    }

    /**
     * 根据调用者和被调用者以及当前需要绘制的方法构判断调用场景和读写类型
     * @param invokeClassName
     * @param providerClassName
     */
    public void buildInvokeScene(String invokeClassName,String providerClassName){
        String invokeScene = InvokeSceneTypeEnum.getInvokeScene(invokeClassName, providerClassName);
        if(StringUtils.isNotEmpty(invokeScene)){
            this.setInvokeSceneType(invokeScene);
        }

        if(StringUtils.isNotEmpty(this.getInvokerMethod())){
           String rwType = ReadWriteTypeEnum.getCodeByMethod(this.getInvokerMethod());
           this.setMethodRWType(rwType);
        }
    }

    /**
     * 构建当前调用行bean
     * @param currentInvokeRowContent
     */
    public void buildCurrentInvokeRow(String currentInvokeRowContent){
        this.setCurrentInvokeRowContent(currentInvokeRowContent);
        /**
         * 如果有返回值
         */
        InvokeRowBean invokeRowBean = new InvokeRowBean();
        String invokeBody = currentInvokeRowContent;
        if(currentInvokeRowContent.contains("=")){
            String [] rowContentArr = currentInvokeRowContent.split("=");
            String returnBody = rowContentArr[0];
            invokeBody = rowContentArr[1];
            invokeRowBean.setReturnClassName(returnBody.trim().split(" ")[0]);
            invokeRowBean.setReturnClassValue(returnBody.trim().split(" ")[1]);
        }
        String [] providerArr = invokeBody.split("\\.");
        invokeRowBean.setProviderClassName(providerArr[0]);
        String methodContent = providerArr[1];
        if(methodContent.contains("()")){
            invokeRowBean.setProviderMethodName(methodContent);
            this.setCurrentInvokeRowBean(invokeRowBean);
            return;
        }else {
            String [] methodArr = methodContent.split("\\(");
            invokeRowBean.setProviderMethodName(methodArr[0]);
            String [] paramValueArr = methodArr[1].replace(")","").split(",");
            invokeRowBean.setProviderMethodParamValueArr(paramValueArr);
            this.setCurrentInvokeRowBean(invokeRowBean);
            return;
        }
    }

}
