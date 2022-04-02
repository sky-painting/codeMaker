package com.tianhua.codemaker.enums;

/**
 * Description:
 * date: 2022/1/25
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum CodeSegmentEnum {
    VALIDATE("validate","校验标示")
    ;

    private String tag;
    private String desc;
    CodeSegmentEnum(String tag, String desc){
        this.tag = tag;
        this.desc = desc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
