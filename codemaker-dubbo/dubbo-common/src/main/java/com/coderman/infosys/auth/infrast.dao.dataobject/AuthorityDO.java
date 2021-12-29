package com.coderman.infosys.auth.infrast.dao.dataobject;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:抽象权限表DO类
 * @Author:shenshuai
 * @CreateTime:2021-12-24 23:17:12
 * @version v1.0
 */
@Data
@ToString
public class AuthorityDO{


	/**  主键 **/
	private Long id;

	/** 权限编码 **/
	private String authCode;

	/** 权限类型 **/
	private Integer authorityType;

	/** 关联角色编码 **/
	private String roleCode;

	/** 关联角色组 **/
	private String roleGroupCode;

	/** 权限状态 **/
	private Integer status;

	/** 创建时间 **/
	private Date dateCreate;

	/** 修改时间 **/
	private Date dateUpdate;

	/** 修改人 **/
	private Long updateUserId;

	/** 创建人 **/
	private Long createUserId;

}