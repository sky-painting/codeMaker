package com.lightsnail.snailapp.usercrm.app.command;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:增加用户权限类
 * @Author:fanchunshuai
 * @CreateTime:2021-07-06 17:11:46
 * @version v1.0
 */
@Data
@ToString
public class AddUserAuthCmd  extends  AbstractCmd{

	/** 用户ID **/
    private Long userId;
	/** 权限类型 **/
    private int authorityType;
	/** 权限数据内容 **/
    private String json;


}