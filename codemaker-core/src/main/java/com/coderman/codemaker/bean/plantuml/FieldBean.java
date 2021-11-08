package com.coderman.codemaker.bean.plantuml;

import com.coderman.codemaker.bean.GlobalConstant;
import com.coderman.codemaker.enums.VisibilityEnum;

/**
 * Description:
 * date: 2021/6/28
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public class FieldBean {
    /**
     * 属性名称
     */
    private String fieldName;
    /**
     * 访问权限
     */
    private String visibility;

    /**
     * 属性描述
     */
    private String desc;

    /**
     * do对应class下的数据库表字段名
     */
    private String dbColumnName;


    /**
     * 属性类型
     */
    private String fieldType;

    /**
     * 属性简单名称
     */
    private String fieldSimpleName;

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldSimpleName() {
        return fieldSimpleName;
    }

    public void setFieldSimpleName(String fieldSimpleName) {
        this.fieldSimpleName = fieldSimpleName;
    }

    public FieldBean(){

    }

    public FieldBean(String fieldName,String desc,String dbColumnName ){
        this(fieldName,desc,dbColumnName, VisibilityEnum.PRIVATE.getVisibility());
    }

    public FieldBean(String fieldName,String desc,String dbColumnName,String visibility ){
        this.fieldName = fieldName;
        this.desc = desc;
        this.dbColumnName = dbColumnName;
        this.visibility = visibility;
    }

    public String getDbColumnName() {
        return dbColumnName;
    }

    public void setDbColumnName(String dbColumnName) {
        this.dbColumnName = dbColumnName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
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


    public boolean isTableKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.TABLE_KEY)
                || this.fieldName.toLowerCase().contains(GlobalConstant.TABLE_KEY);
    }

    public boolean isDtoKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.DTO_KEY_LIST)
                || this.fieldName.toLowerCase().contains(GlobalConstant.DTO_KEY_LIST);
    }
    public boolean isFacadeKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.FACADE_KEY)
                || this.fieldName.toLowerCase().contains(GlobalConstant.FACADE_KEY);
    }

    public boolean isVoKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.VO_KEY_LIST)
                || this.fieldName.toLowerCase().contains(GlobalConstant.VO_KEY_LIST);
    }

    public boolean isControllerKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.CONTROLLER_KEY)
                || this.fieldName.toLowerCase().contains(GlobalConstant.CONTROLLER_KEY);
    }

    public boolean isCopyToRpcClientKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.COPY2RPC_CLIENT_KEY)
                || this.fieldName.toLowerCase().contains(GlobalConstant.COPY2RPC_CLIENT_KEY);
    }

    public boolean isInvokeFileKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.INVOKE_FILE_KEY)
                || this.fieldName.toLowerCase().contains(GlobalConstant.INVOKE_FILE_KEY);
    }

    /**
     * 构建属性注释
     * @param desc
     */
    public void buildDesc(String desc){
        if(desc.startsWith(VisibilityEnum.PUBLIC.getTag())
                || desc.startsWith(VisibilityEnum.PRIVATE.getTag())
                || desc.startsWith(VisibilityEnum.PROTECT.getTag())){
            String newDesc = desc.substring(1,desc.length()-1);
            this.setDesc(newDesc);
        }else {
            this.setDesc(desc);
        }
    }


    public FieldBean copySelf(){
        FieldBean fieldBean = new FieldBean();
        fieldBean.setFieldName(this.getFieldName());
        fieldBean.setVisibility(this.getVisibility());
        fieldBean.setDesc(this.getDesc());
        return fieldBean;
    }


    /**
     * 构建属性的getter方法名
     * @return
     */
    public String buildGetterMethodName(){
        String simpleFieldName = this.getFieldName();
        if(fieldName.contains(" ")){
            simpleFieldName = fieldName.split(" ")[1];
        }
        String prefix = "get";
        //判断是否是boolean类型
        if(this.getFieldName().toLowerCase().contains("boolean")){
            prefix = "is";
        }
        return  prefix+simpleFieldName.substring(0,1).toUpperCase()+simpleFieldName.substring(1)+"()";
    }

    public void buildFieldDetail(){
        if(this.getFieldName().contains(" ")){
            String [] fieldArr = this.getFieldName().split(" ");
            this.setFieldType(fieldArr[0]);
            this.setFieldSimpleName(fieldArr[1]);
        }
    }

    @Override
    public String toString() {
        return "FieldBean{" +
                "fieldName='" + fieldName + '\'' +
                ", desc='" + desc + '\'' +
                ", dbColumnName='" + dbColumnName + '\'' +
                '}';
    }
}
