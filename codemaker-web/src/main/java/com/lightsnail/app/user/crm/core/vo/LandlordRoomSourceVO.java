package com.lightsnail.app.user.crm.core.vo;

import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;
/**
* @Description:房东-房源表VO类
* @Author:shenshuai
* @CreateTime:2020-11-17 00:02:53
* @version v1.0
*/
@Data
@ToString
public class LandlordRoomSourceVO{


	/** 房源表id **/
	private Long id;

	/** 房东账号id **/
	private Long landlordAccountId;

	/** 房源所属行政区域id **/
	private Integer areaId;

	/** 房源所属小区 **/
	private String areaCode;

	/** 房源所在楼层单元地址 **/
	private String floorAddress;

	/** 房东电话 **/
	private String handphone;

	/** 账号创建时间 **/
	private Date createTime;

	/** 户型类型 **/
	private Integer roomFormType;

	/** 可出租房间数量 **/
	private Integer sourceNumbers;

	/** 房屋面积 **/
	private BigDecimal sizeInfo;

	/** 客厅数目 **/
	private Integer livingRoomNum;

	/** 厨房数目 **/
	private Integer kitchenNum;

	/** 卫生间数目 **/
	private Integer toiletNum;

	/** 出租要求 **/
	private String requireInfo;

	/** 装修情况，精装修，简装修，毛坯房 **/
	private Integer decorateInfo;

	/** 支持的房屋设备 **/
	private String supportEquipments;

	/** 水费/吨 **/
	private BigDecimal moneyWater;

	/** 电费/度 **/
	private BigDecimal moneyThunder;

	/** 网费/月 **/
	private BigDecimal moneyInternet;

	/** 物业费/月 **/
	private BigDecimal moneyManage;

	/** 停车费/月 **/
	private BigDecimal moneyCarPark;


}