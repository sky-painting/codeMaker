package com.coderman.infosys.auth.app.command;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:更新用户权限类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:23:49
 * @version v1.0
 */
@Data
@ToString
public class UpdateUserAuthCmd  extends  AbstractCmd{


	/** 用户ID **/
    private Long userId;

	/** 角色ID **/
    private Long roleId;

	/** 权限数据内容 **/
    private String json;


}