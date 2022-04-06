package com.tianhua.codemaker.enums;

/**
 * Description:
 * date: 2022/3/30
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
public enum ConfigFileEnum {

    PROPERTIES("properties"),
    YML("yml"),
    CONFIG("config"),
    SH("sh"),
    ;

    private String fileExtName;
    ConfigFileEnum(String fileExtName){
        this.fileExtName = fileExtName;
    }

    public String getFileExtName() {
        return fileExtName;
    }
}
