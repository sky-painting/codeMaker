package com.lightsnail.app.user.crm.core.entity;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:房东-房源表Entity类
 * @Author:shenshuai
 * @CreateTime:2020-11-17 00:02:53
 * @version v1.0
 */
@Data
@ToString
public class RealRoomSourceEntity{


	/** 出租房源表id **/
	private Long id;

	/** 房东账号id **/
	private Long landlordAccountId;

	/** 房源表主表id **/
	private Long landlordRoomSourceId;

	/** 房屋状态 **/
	private Integer roomStatus;

	/** 出租开始时间 **/
	private Date rentStartTime;

	/** 出租结束时间 **/
	private Date rentEndTime;

	/** 房屋类型，主卧，次卧，客厅，阳台,一居室,两居室,开间 **/
	private Integer roomFormType;

	/** 房屋编号 **/
	private String roomIndex;

	/** 房屋出租描述 **/
	private String roomDesc;

	/** 出租价格/月 **/
	private BigDecimal roomPrice;

	/** 出租形式(整租,合租) **/
	private Integer roomRentForm;

	/** 租金形式(月付,季度付,半年付,整年付) **/
	private Integer roomPayForm;

	/** 服务费（中介房源，与押金类似） **/
	private BigDecimal servicePrice;

}