package com.coderman.infosys.auth.domain.enums;

import lombok.Getter;

 /**
 * @Description:权限类型类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:23:49
 * @version v1.0
 */
@Getter
public enum AuthorityTypeEnum{

    /**
     *
     */
    DATA(1,"数据字段权限"),
    FUNCATION(2,"菜单功能权限"),
    ADMINISTRATION(3,"行政权限"),
    CUSTOMIZE(4,"自定义权限"),
    ;

	/** 权限类型code **/
    private Integer code;
	/** 权限类型描述 **/
    private String desc;


    AuthorityTypeEnum(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }



    /**
     *
     * @Description:static
     * @return AuthorityTypeEnum
     */
     public static AuthorityTypeEnum  getByCode(Integer code){
        return null;
     }

}