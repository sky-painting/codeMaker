package com.tianhua.codemaker.bean.invoke;

import com.tianhua.codemaker.bean.plantuml.AbstractClassBean;
import com.tianhua.codemaker.bean.plantuml.MethodBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.enums.dynamic.InvokeSceneTypeEnum;
import com.tianhua.codemaker.enums.dynamic.ReadWriteTypeEnum;
import org.apache.commons.lang3.StringUtils;


/**
 * Description: 调用时序上下文
 * date: 2021/10/16
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class InvokeContextBean {
    /**
     * 需要绘制方法内容的方法对象
     * 调用方的方法
     */
    private MethodBean invokerMethodBean;

    /**
     * plantuml 调用流程图中的被调用类
     */
    private String providerClassName;

    /**
     * plantuml 调用流程图中的被调用方法
     */
    private MethodBean providerClassMethod;

    /**
     * plantuml 调用流程图中的调用方法
     */
    private String invokerMethod;


    /**
     * 调用方向
     * 如 应用层->领域层,应用层->基础设施层,应用层->应用层,领域层->领域层
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
     * 被调用类对象
     */
    private AbstractClassBean providerClassBean;

    /**
     * 当前调用方调用提供方的调用行内容
     */
    private String currentInvokeRowContent;


    /**
     * 当前调用的调用行bean
     */
    private InvokeRowBean currentInvokeRowBean;

    public MethodBean getProviderClassMethod() {
        return providerClassMethod;
    }

    public void setProviderClassMethod(MethodBean providerClassMethod) {
        this.providerClassMethod = providerClassMethod;
    }

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

    public MethodBean getInvokerMethodBean() {
        return invokerMethodBean;
    }

    public void setInvokerMethodBean(MethodBean invokerMethodBean) {
        this.invokerMethodBean = invokerMethodBean;
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


    public PlantUmlContextBean getPlantUmlContextBean() {
        return plantUmlContextBean;
    }

    public void setPlantUmlContextBean(PlantUmlContextBean plantUmlContextBean) {
        this.plantUmlContextBean = plantUmlContextBean;
    }

    /**
     * 根据调用者和被调用者以及当前需要绘制的方法构判断调用场景和读写类型
     *
     * @param invokeClassName
     * @param providerClassName
     */
    public void buildInvokeScene(String invokeClassName, String providerClassName) {
        String invokeScene = InvokeSceneTypeEnum.getInvokeScene(invokeClassName, providerClassName);
        if (StringUtils.isNotEmpty(invokeScene)) {
            this.setInvokeSceneType(invokeScene);
        }

        if (StringUtils.isNotEmpty(this.getInvokerMethod())) {
            String rwType = ReadWriteTypeEnum.getCodeByMethod(this.getInvokerMethod());
            this.setMethodRWType(rwType);
        }
    }

    /**
     * 根据调用内容构建调用场景
     *
     * @param invokeContent
     */
    public void buildInvokeSceneV2(String invokeContent) {
        String invokeSceneStr = invokeContent.split(":")[0].trim();

        String invokeScene = InvokeSceneTypeEnum.getInvokeSceneV2(invokeSceneStr);
        if (StringUtils.isNotEmpty(invokeScene)) {
            this.setInvokeSceneType(invokeScene);
        }

        if (StringUtils.isNotEmpty(this.getInvokerMethod())) {
            String rwType = ReadWriteTypeEnum.getCodeByMethod(this.getInvokerMethod());
            this.setMethodRWType(rwType);
        }
    }


    /**
     * 构建当前调用行bean
     *
     * @param currentInvokeRowContent
     */
    public void buildCurrentInvokeRow(String currentInvokeRowContent) {
        this.setCurrentInvokeRowContent(currentInvokeRowContent);
        /**
         * 如果有返回值
         */
        InvokeRowBean invokeRowBean = new InvokeRowBean();
        if (currentInvokeRowContent.contains("=")) {
            String[] rowContentArr = currentInvokeRowContent.split("=");
            String returnBody = rowContentArr[0];
            invokeRowBean.setReturnClassName(returnBody.trim().split(" ")[0].trim());
            invokeRowBean.setReturnClassValue(returnBody.trim().split(" ")[1].trim());
        }
        String classNameVar = this.getProviderClassName().substring(0,1).toLowerCase()+ this.getProviderClassName().substring(1);
        invokeRowBean.setProviderClassNameVar(classNameVar);

        invokeRowBean.setProviderClassName(this.getProviderClassName());
        invokeRowBean.setProviderMethodName(this.getProviderClassMethod().getSimplMethodName());
        invokeRowBean.setReturnClassName(this.getProviderClassMethod().getReturnClass());
        this.getProviderClassMethod().buildParamArr();
        invokeRowBean.setProviderMethodParamTypeArr(this.getProviderClassMethod().getParamArr());
        invokeRowBean.buildParamValueArr();

        this.setCurrentInvokeRowBean(invokeRowBean);
    }

    @Override
    public String toString() {
        return "InvokeContextBean{" +
                "invokerMethodBean=" + invokerMethodBean +
                ", providerClassName='" + providerClassName + '\'' +
                ", invokerMethod='" + invokerMethod + '\'' +
                ", InvokeSceneType='" + InvokeSceneType + '\'' +
                ", methodRWType='" + methodRWType + '\'' +
                ", invokerClassBean=" + invokerClassBean +
                ", currentInvokeRowContent='" + currentInvokeRowContent + '\'' +
                ", currentInvokeRowBean=" + currentInvokeRowBean +
                '}';
    }


    public AbstractClassBean getProviderClassBean() {
        return providerClassBean;
    }

    public void setProviderClassBean(AbstractClassBean providerClassBean) {
        this.providerClassBean = providerClassBean;
    }
}
