package com.lightsnail.schoolmanager.core.model.bo;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:房东表BO类
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 23:24:09
 * @version v1.0
 */
@Data
@ToString
public class RoomLandlordBO{


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