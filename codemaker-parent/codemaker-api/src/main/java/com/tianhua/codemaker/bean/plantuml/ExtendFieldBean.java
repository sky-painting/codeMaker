package com.tianhua.codemaker.bean.plantuml;

/**
 * Description:从plantUMl中解析出的扩展属性上下文
 * date: 2021/10/21
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class ExtendFieldBean {
    /**
     * 需要解析的调用时序图文件列表
     */
    private String[] invokeFileKey;
    /**
     * bo对应的表名称
     */
    private String tableKey;
    /**
     * bo对应的facade接口名称列表
     */
    private String[] facadeKey;
    /**
     * bo对应的controller接口名称列表
     */
    private String[] controllerKey;
    /**
     * bo对应的dto对象列表
     */
    private String[] dtoKeyList;

    /**
     * bo对应的vo对象列表
     */
    private String[] voKeyList;

    /**
     * bo对应的校验对象列表
     */
    private String [] validateKeyList;

    public String[] getValidateKeyList() {
        return validateKeyList;
    }

    public void setValidateKeyList(String[] validateKeyList) {
        this.validateKeyList = validateKeyList;
    }

    public String[] getInvokeFileKey() {
        return invokeFileKey;
    }

    public void setInvokeFileKey(String[] invokeFileKey) {
        this.invokeFileKey = invokeFileKey;
    }

    public String getTableKey() {
        return tableKey;
    }

    public void setTableKey(String tableKey) {
        this.tableKey = tableKey;
    }

    public String[] getFacadeKey() {
        return facadeKey;
    }

    public void setFacadeKey(String[] facadeKey) {
        this.facadeKey = facadeKey;
    }

    public String[] getControllerKey() {
        return controllerKey;
    }

    public void setControllerKey(String[] controllerKey) {
        this.controllerKey = controllerKey;
    }

    public String[] getDtoKeyList() {
        return dtoKeyList;
    }

    public void setDtoKeyList(String[] dtoKeyList) {
        this.dtoKeyList = dtoKeyList;
    }

    public String[] getVoKeyList() {
        return voKeyList;
    }

    public void setVoKeyList(String[] voKeyList) {
        this.voKeyList = voKeyList;
    }

    public void buildTableKey(String tableKey){
        this.setTableKey(tableKey.replace("String","").replace("string","").trim());
    }

    public void buildDtoKeyArr(String dtoKey){
        this.setDtoKeyList(dtoKey.replace("String","").replace("string","").trim().split(","));
    }

    public void buildFacadeKeyArr(String facadeKey){
        this.setFacadeKey(facadeKey.replace("String","").replace("string","").trim().split(","));
    }

    public void buildVoKeyArr(String voKeyList){
        this.setVoKeyList(voKeyList.replace("String","").replace("string","").trim().split(","));
    }

    public void buildControllerKeyArr(String controllerKeyList){
        this.setControllerKey(controllerKeyList.replace("String","").replace("string","").trim().split(","));
    }

    public void buildInvokeFileKeyArr(String invokeFileKey){
        this.setInvokeFileKey(invokeFileKey.replace("String","").replace("string","").trim().split(","));
    }

    public void buildValidteKeyArr(String invokeFileKey){
        this.setValidateKeyList(invokeFileKey.replace("String","").replace("string","").trim().split(","));
    }
}
