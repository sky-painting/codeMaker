package com.lightsnail.snailapp.usercrm.app.command;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:增加用户命令类
 * @Author:fanchunshuai
 * @CreateTime:2021-07-06 17:11:46
 * @version v1.0
 */
@Data
@ToString
public class AddUserCmd  extends  AbstractCmd{

	/** 用户名 **/
    private String userName;
	/** 用户ID **/
    private Long userId;
	/** 所属组织 **/
    private Long departmentId;


}