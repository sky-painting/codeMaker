package com.lightsnail.schoolmanager.core.model.bo;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:代理人-房源发布表BO类
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 23:24:09
 * @version v1.0
 */
@Data
@ToString
public class RoomPublishBO{


	/** 代理人id，主键id **/
	private Long id;

	/** 发布人账号id **/
	private Long publisherAccountId;

	/** 真实房源id **/
	private Long roomSourceId;

	/** 发布的房源id **/
	private Long publishSourceId;

	/** 记录创建时间/发布时间 **/
	private Date createTime;

}