package com.coderman.infosys.auth.infrast.dao.dataobject;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:行政数据权限DO类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:23:49
 * @version v1.0
 */
@Data
@ToString
public class AdminAuthorityDO{


	/**  主键 **/
	private Long id;

	/** 权限标示 **/
	private String authCode;

	/** 组织部门ID **/
	private Long departmentId;

	/** 城市权限 **/
	private Long cityId;

	/** 创建时间 **/
	private Date dateCreate;

	/** 修改时间 **/
	private Date dateUpdate;

	/** 修改人 **/
	private Long updateUserId;

	/** 创建人 **/
	private Long createUserId;

}