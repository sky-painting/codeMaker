package com.coderman.infosys.auth.domain.msgbody;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:接收用户状态变更消息类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:18:28
 * @version v1.0
 */
@Data
@ToString
public class UserStatusChangeMsgBody{

	/** 用户ID **/
    private Long userId;
	/** 状态类型 **/
    private Integer statusType;


}