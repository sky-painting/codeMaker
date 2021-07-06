package com.lightsnail.schoolmanager.core.model.bo;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:房东合同模板表BO类
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 23:24:09
 * @version v1.0
 */
@Data
@ToString
public class LandlordContractTemplateBO{


	/** 房源表id **/
	private Long id;

	/** 房东账号id **/
	private Long landlordAccountId;

	/** 合同模板id **/
	private Long contractTemplateId;

	/** 记录创建时间 **/
	private Date createTime;

}