package com.lightsnail.app.user.crm.core.vo;

import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;
/**
* @Description:合作用户表VO类
* @Author:shenshuai
* @CreateTime:2020-11-17 00:02:53
* @version v1.0
*/
@Data
@ToString
public class AccountVO{


	/** 账号id **/
	private Long id;

	/** 账号昵称 **/
	private String nickName;

	/** 密码 **/
	private String passWord;

	/** 账号类型（代理人，房东，招商用户） **/
	private Integer accountType;

	/** 代理人，房东，招商用户id  **/
	private Long memberId;

	/** 账号状态 **/
	private Integer status;

	/** 电话号码 **/
	private String handphone;


}