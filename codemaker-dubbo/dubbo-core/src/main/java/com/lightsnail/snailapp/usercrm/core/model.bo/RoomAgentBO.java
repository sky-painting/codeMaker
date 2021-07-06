package com.lightsnail.schoolmanager.core.model.bo;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:代理人表BO类
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 23:24:09
 * @version v1.0
 */
@Data
@ToString
public class RoomAgentBO{


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