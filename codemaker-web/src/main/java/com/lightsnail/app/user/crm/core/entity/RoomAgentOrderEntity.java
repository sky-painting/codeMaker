package com.lightsnail.app.user.crm.core.entity;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:代理人-签单表Entity类
 * @Author:fanchunshuai
 * @CreateTime:2020-11-17 00:02:53
 * @version v1.0
 */
@Data
@ToString
public class RoomAgentOrderEntity{


	/** 主键id **/
	private Long id;

	/** 代理人账号id **/
	private Long agentAccountId;

	/** 房东账号id **/
	private Long landlordAccountId;

	/** 房源id **/
	private Long roomSourceId;

	/** 记录创建时间 **/
	private Date createTime;

	/** 签单时间 **/
	private Date orderTime;

	/** 佣金额度/元 **/
	private Integer tipMoney;

	/** 结算形式/一单一结，月结 **/
	private Integer settlementForm;

}