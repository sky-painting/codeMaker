package com.lightsnail.snailapp.usercrm.app.command;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:增加用户组命令类
 * @Author:fanchunshuai
 * @CreateTime:2021-07-07 00:05:11
 * @version v1.0
 */
@Data
@ToString
public class AddUserGroupCmd  extends  AbstractCmd{

	/** 组名 **/
    private String groupName;
	/** 用户ID **/
    private Long userId;


}