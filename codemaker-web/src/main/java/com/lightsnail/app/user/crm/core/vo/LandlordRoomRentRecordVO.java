package com.lightsnail.app.user.crm.core.vo;

import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;
/**
* @Description:房源出租记录表VO类
* @Author:fanchunshuai
* @CreateTime:2020-11-17 00:02:54
* @version v1.0
*/
@Data
@ToString
public class LandlordRoomRentRecordVO{


	/** 主键id **/
	private Long id;

	/** 房东账号id **/
	private Long landlordAccountId;

	/** 房源id **/
	private Long roomSourceId;

	/** 代理人账号id **/
	private Long agentAccountId;

	/** 房源房屋序号索引 **/
	private Integer roomSourceIndex;

	/** 用户id **/
	private Long userId;

	/** 合同ID **/
	private Long contractId;

	/** 出租开始时间 **/
	private Date rentStartTime;

	/** 出租结束时间 **/
	private Date rentEndTime;

	/** 出租实际结束时间 **/
	private Date realEndTime;

	/** 记录创建时间 **/
	private Date createTime;

	/** 记录修改时间 **/
	private Date updateTime;


}