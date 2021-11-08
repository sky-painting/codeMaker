package com.coderman.infosys.auth.infrast.dao.dataobject;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:系统菜单权限表DO类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:23:49
 * @version v1.0
 */
@Data
@ToString
public class SystemAuthorityDO{


	/**  主键 **/
	private Long id;

	/** 权限标示 **/
	private String authCode;

	/** 系统编码 **/
	private String systemCode;

	/** 菜单编码 **/
	private String moduleCode;

	/** 按钮编码 **/
	private String menuCode;

	/** 创建时间 **/
	private Date dateCreate;

	/** 创建人 **/
	private Long createUserId;

}