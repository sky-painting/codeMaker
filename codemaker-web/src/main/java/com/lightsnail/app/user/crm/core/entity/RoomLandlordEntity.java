package com.lightsnail.app.user.crm.core.entity;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:房东表Entity类
 * @Author:shenshuai
 * @CreateTime:2020-11-17 00:02:53
 * @version v1.0
 */
@Data
@ToString
public class RoomLandlordEntity{


	/** 房东id，主键id **/
	private Long id;

	/** 房东姓名 **/
	private String chineseName;

	/** 房东身份证号 **/
	private String cardNumber;

	/** 房东人身份证图片(正反面) **/
	private String cardNumberUrls;

	/** 房东电话 **/
	private String handphone;

	/** 审核状态 **/
	private Integer checkStatus;

}