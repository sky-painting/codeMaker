package com.lightsnail.schoolmanager.core.model.bo;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:招商服务注册表BO类
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 23:24:09
 * @version v1.0
 */
@Data
@ToString
public class AttractInvestmentBO{


	/** 主键id **/
	private Long id;

	/** 招商对象名称 **/
	private String chineseName;

	/** 联系电话 **/
	private String handphone;

	/** 招商类型 **/
	private Integer investmentType;

	/** 公司地址 **/
	private String companyAddress;

	/** 合作开始时间 **/
	private Date startTime;

	/** 合作结束时间 **/
	private Date endTime;

	/** 是否开通账号 **/
	private Integer isOpenaccount;

	/** 会员账号id **/
	private Long accountId;

	/** 记录创建时间 **/
	private Date createTime;

}