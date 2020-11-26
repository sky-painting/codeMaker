package com.lightsnail.app.user.crm.core.vo;

import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;
/**
* @Description:代理人表VO类
* @Author:fanchunshuai
* @CreateTime:2020-11-17 00:02:53
* @version v1.0
*/
@Data
@ToString
public class RoomAgentVO{


	/** 代理人id，主键id **/
	private Long id;

	/** 代理人姓名 **/
	private String chineseName;

	/** 代理人身份证号 **/
	private String cardNumber;

	/** 代理人身份证图片(正反面) **/
	private String cardNumberUrls;

	/** 代理人电话 **/
	private String handphone;

	/** 代理小区列表 **/
	private String areaCodes;


}