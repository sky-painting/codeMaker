package com.coderman.codemaker.bean.apidoc;

import java.util.List;

/**
 * Description: 支持http rpc的接口文档模型
 * date: 2021/11/22
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ApiDocBean {

    /**
     * 接口名称
     * controller--method   a/b/c
     * rpc---method
     */
    private String interfaceName;

    /**
     * 参数列表
     */
    private List<ApiParamBean> paramBeanList;

    /**
     * 接口文档描述
     */
    private String interfaceDesc;

    /**
     * 返回值类信息
     */
    private String returnClass;


    /**
     * 入参json 案例
     */
    private String paramJsonDemo;


    /**
     * 出参json 案例
     */
    private String returnJsonDemo;

    /**
     * http接口的访问路径
     */
    private String httpPath;

    public String getHttpPath() {
        return httpPath;
    }

    public void setHttpPath(String httpPath) {
        this.httpPath = httpPath;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<ApiParamBean> getParamBeanList() {
        return paramBeanList;
    }

    public void setParamBeanList(List<ApiParamBean> paramBeanList) {
        this.paramBeanList = paramBeanList;
    }

    public String getInterfaceDesc() {
        return interfaceDesc;
    }

    public void setInterfaceDesc(String interfaceDesc) {
        this.interfaceDesc = interfaceDesc;
    }

    public String getReturnClass() {
        return returnClass;
    }

    public void setReturnClass(String returnClass) {
        this.returnClass = returnClass;
    }

    public String getParamJsonDemo() {
        return paramJsonDemo;
    }

    public void setParamJsonDemo(String paramJsonDemo) {
        this.paramJsonDemo = paramJsonDemo;
    }

    public String getReturnJsonDemo() {
        return returnJsonDemo;
    }

    public void setReturnJsonDemo(String returnJsonDemo) {
        this.returnJsonDemo = returnJsonDemo;
    }



}
