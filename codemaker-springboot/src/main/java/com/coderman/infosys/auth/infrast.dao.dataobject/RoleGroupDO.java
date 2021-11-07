package com.coderman.infosys.auth.infrast.dao.dataobject;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:角色组DO类
 * @Author:shenshuai
 * @CreateTime:2021-11-06 23:38:07
 * @version v1.0
 */
@Data
@ToString
public class RoleGroupDO{


	/**  主键 **/
	private Long id;

	/** 角色组名 **/
	private String groupName;

	/** 角色组编码 **/
	private String groupCode;

	/** 角色列表 **/
	private String roleCodeList;

	/** 创建时间 **/
	private Date dateCreate;

	/** 修改时间 **/
	private Date dateUpdate;

	/** 修改人 **/
	private Long updateUserId;

	/** 创建人 **/
	private Long createUserId;

}