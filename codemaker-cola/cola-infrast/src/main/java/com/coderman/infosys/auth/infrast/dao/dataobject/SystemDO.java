package com.coderman.infosys.auth.infrast.dao.dataobject;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:系统表DO类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:23:49
 * @version v1.0
 */
@Data
@ToString
public class SystemDO{


	/**  主键 **/
	private Long id;

	/** 系统名称 **/
	private String systemName;

	/** 系统编码 **/
	private String systemCode;

	/** 系统域名 **/
	private String systemUrl;

	/** 系统图标 **/
	private String systemPicurl;

	/** 系统技术负责人 **/
	private Long tecAdminUserId;

	/** 系统业务负责人 **/
	private Long busAdminUserId;

	/** 系统所属部门 **/
	private Long departmentId;

	/** 创建时间 **/
	private Date dateCreate;

	/** 创建人 **/
	private Long createUserId;

}