package com.coderman.infosys.auth.infrast.dao.dataobject;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:角色表DO类
 * @Author:shenshuai
 * @CreateTime:2021-12-24 23:17:12
 * @version v1.0
 */
@Data
@ToString
public class RoleDO{


	/**  主键 **/
	private Long id;

	/** 角色名称 **/
	private String roleName;

	/** 角色编码 **/
	private String roleCode;

	/**  角色组状态 **/
	private Long status;

	/** 创建时间 **/
	private Date dateCreate;

	/** 修改时间 **/
	private Date dateUpdate;

	/** 修改人 **/
	private Long updateUserId;

	/** 创建人 **/
	private Long createUserId;

}