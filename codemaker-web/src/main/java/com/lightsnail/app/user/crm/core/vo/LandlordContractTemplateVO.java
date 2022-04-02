package com.lightsnail.app.user.crm.core.vo;

import java.util.Date;
import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;
/**
* @Description:房东合同模板表VO类
* @Author:shenshuai
* @CreateTime:2020-11-17 00:02:53
* @version v1.0
*/
@Data
@ToString
public class LandlordContractTemplateVO{


	/** 房源表id **/
	private Long id;

	/** 房东账号id **/
	private Long landlordAccountId;

	/** 合同模板id **/
	private Long contractTemplateId;

	/** 记录创建时间 **/
	private Date createTime;


}