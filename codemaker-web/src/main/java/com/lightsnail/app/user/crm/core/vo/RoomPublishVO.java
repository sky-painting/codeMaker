package com.lightsnail.app.user.crm.core.vo;

import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;
/**
* @Description:代理人-房源发布表VO类
* @Author:fanchunshuai
* @CreateTime:2020-11-17 00:02:53
* @version v1.0
*/
@Data
@ToString
public class RoomPublishVO{


	/** 代理人id，主键id **/
	private Long id;

	/** 发布人账号id **/
	private Long publisherAccountId;

	/** 真实房源id **/
	private Long roomSourceId;

	/** 发布的房源id **/
	private Long publishSourceId;

	/** 记录创建时间/发布时间 **/
	private Date createTime;


}