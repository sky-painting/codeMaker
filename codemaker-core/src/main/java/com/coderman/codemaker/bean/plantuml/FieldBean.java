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
}
