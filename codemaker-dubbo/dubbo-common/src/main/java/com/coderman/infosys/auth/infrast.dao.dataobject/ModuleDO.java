package com.coderman.infosys.auth.infrast.dao.dataobject;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:菜单模块表DO类
 * @Author:shenshuai
 * @CreateTime:2021-12-24 23:17:12
 * @version v1.0
 */
@Data
@ToString
public class ModuleDO{


	/**  主键 **/
	private Long id;

	/** 菜单名称 **/
	private String moduleName;

	/** 菜单编码 **/
	private String moduleCode;

	/** 系统编码 **/
	private String systemCode;

	/** 菜单链接 **/
	private String moduleUrl;

	/** 创建时间 **/
	private Date dateCreate;

	/** 修改时间 **/
	private Date dateUpdate;

	/** 修改人 **/
	private Long updateUserId;

	/** 创建人 **/
	private Long createUserId;

}