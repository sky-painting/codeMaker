package com.tianhua.codemaker.bean.plantuml;

import com.alibaba.fastjson.annotation.JSONField;
import com.tianhua.codemaker.bean.GlobalConstant;
import com.tianhua.codemaker.enums.VisibilityEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * date: 2021/6/28
 *
 * @author shenshuai
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

    /**
     * 注解
      */
    private String annotation;

    /**
     * plantUMl类图的原始数据
     */
    private String originFieldStr;

    /**
     * vo/dto/do等模型属性上的扩展注解标示
     */
    private List<String> annotationTagList = new ArrayList<>();

    public List<String> getAnnotationTagList() {
        return annotationTagList;
    }

    public void setAnnotationTagList(List<String> annotationTagList) {
        this.annotationTagList = annotationTagList;
    }

    public String getOriginFieldStr() {
        return originFieldStr;
    }

    public void setOriginFieldStr(String originFieldStr) {
        this.originFieldStr = originFieldStr;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

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

    /**
     * 是否关联具体数据表
     * @return
     */
    @JSONField(serialize = false)
    public boolean isTableKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.TABLE_KEY)
                || this.fieldName.toLowerCase().contains(GlobalConstant.TABLE_KEY);
    }

    /**
     * 是否需要派生dto
     * @return
     */
    @JSONField(serialize = false)
    public boolean isDtoKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.DTO_KEY_LIST)
                || this.fieldName.toLowerCase().contains(GlobalConstant.DTO_KEY_LIST);
    }

    /**
     * 是否需要派生dto
     * @return
     */
    @JSONField(serialize = false)
    public boolean isFacadeKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.FACADE_KEY)
                || this.fieldName.toLowerCase().contains(GlobalConstant.FACADE_KEY);
    }

    @JSONField(serialize = false)
    public boolean isVoKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.VO_KEY_LIST)
                || this.fieldName.toLowerCase().contains(GlobalConstant.VO_KEY_LIST);
    }

    @JSONField(serialize = false)
    public boolean isControllerKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.CONTROLLER_KEY)
                || this.fieldName.toLowerCase().contains(GlobalConstant.CONTROLLER_KEY);
    }

    @JSONField(serialize = false)
    public boolean isCopyToRpcClientKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.COPY2RPC_CLIENT_KEY)
                || this.fieldName.toLowerCase().contains(GlobalConstant.COPY2RPC_CLIENT_KEY);
    }

    @JSONField(serialize = false)
    public boolean isInvokeFileKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.INVOKE_FILE_KEY)
                || this.fieldName.toLowerCase().contains(GlobalConstant.INVOKE_FILE_KEY);
    }


    @JSONField(serialize = false)
    public boolean isContextKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.CONTEXT_KEY)
                || this.fieldName.toLowerCase().contains(GlobalConstant.CONTEXT_KEY);
    }

    @JSONField(serialize = false)
    public boolean isQueryDtoKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.QUERY_DTO_KEY)
                || this.fieldName.toLowerCase().contains(GlobalConstant.QUERY_DTO_KEY);
    }

    @JSONField(serialize = false)
    public boolean isQueryVoKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.QUERY_VO_KEY)
                || this.fieldName.toLowerCase().contains(GlobalConstant.QUERY_VO_KEY);
    }

    @JSONField(serialize = false)
    public boolean isExportAclKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.EXPORT_ACL_KEY)
                || this.fieldName.toLowerCase().contains(GlobalConstant.EXPORT_ACL_KEY);
    }

    @JSONField(serialize = false)
    public boolean isValidateKey(){
        return this.desc.toLowerCase().contains(GlobalConstant.VALIDATE_KEY)
                || this.fieldName.toLowerCase().contains(GlobalConstant.VALIDATE_KEY);
    }

    /**
     * 根据条件判断是否是简单类型--非扩展字段信息判断
     * @return
     */
    @JSONField(serialize = false)
    public boolean isSimpleField(){
       return !this.isTableKey()
                && !this.isDtoKey()
                && !this.isFacadeKey()
                && !this.isVoKey()
                && !this.isControllerKey()
                && !this.isInvokeFileKey()
                && !this.isContextKey()
                && !this.isQueryVoKey()
                && !this.isQueryDtoKey()
                && !this.isExportAclKey()
                && !this.isValidateKey()
                && !this.isMethod();
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

    public String getSimpleName(){
        buildFieldDetail();
        return this.getFieldSimpleName();
    }

    @JSONField(serialize = false)
    public boolean isMethod(){
        return this.desc.toLowerCase().contains("/")
                || this.fieldName.toLowerCase().contains("(");
    }
    /**
     *       queryDtoKey:SystemQueryDTO String systemName,String systemCode,Long departmentId, Date startDate,Date endDate
     *       queryVoKey:SystemQueryVO String systemName,String systemCode,Long departmentId, Date startDate,Date endDate
     *
     */

    /**
     * 从原始数据中构建查询请求对象
     * @return
     */
    public ClassBean buildQueryClass(){
        String queryFieldStr = this.getOriginFieldStr().split(":")[1];
        String [] fieldStrArr = queryFieldStr.split(",");
        ClassBean classBean = new ClassBean();

        List<FieldBean> fieldBeanList = new ArrayList<>();

        for (int i = 0;i < fieldStrArr.length;i++){
            String  [] fieldArr = fieldStrArr[i].trim().split(" ");
            List<String> fieldStrList = new ArrayList<>();
            for (String fieldStr : fieldArr){
                if(StringUtils.isNotEmpty(fieldStr.trim())){
                    fieldStrList.add(fieldStr);
                }
            }
            if(fieldStrList.size() == 3) {
                classBean.setClassName(fieldStrList.get(0));
                FieldBean fieldBean = new FieldBean();
                fieldBean.setDesc("");
                fieldBean.setFieldName(fieldStrList.get(1)+" "+fieldStrList.get(2));
                fieldBean.buildFieldDetail();
                fieldBeanList.add(fieldBean);
            }else {
                FieldBean fieldBean = new FieldBean();
                fieldBean.setDesc("");
                fieldBean.setFieldName(fieldStrList.get(0)+" "+fieldStrList.get(1));
                fieldBean.buildFieldDetail();
                fieldBeanList.add(fieldBean);
            }
        }
        classBean.setFieldBeanList(fieldBeanList);
        return classBean;
    }

    public void addAnnotationTag(String annotation){
        if(this.annotationTagList.contains(annotation)){
            return;
        }
        this.annotationTagList.add(annotation);
    }
}
