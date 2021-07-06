package com.lightsnail.schoolmanager.core.model.bo;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:代理人-预约看房记录表BO类
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 23:24:09
 * @version v1.0
 */
@Data
@ToString
public class RoomSubscribeBO{


	/** 主键id **/
	private Long id;

	/** 代理人账号id **/
	private Long agentAccountId;

	/** 代理人类型0:房东,1:代理人,2:中介 **/
	private Integer roomAgentType;

	/** 用户id **/
	private Long userId;

	/** 房源所属小区/看房小区 **/
	private String areaCode;

	/** 房源所在楼层单元地址 **/
	private String floorAddress;

	/** 提前N小时提醒 **/
	private Integer mindHourseBefore;

	/** 用户电话 **/
	private String handphone;

	/** 是否进行预约提醒 **/
	private Integer isMind;

	/** 预约时间 **/
	private Date subscribeTime;

	/** 看房结果 **/
	private String subscribeResult;

}