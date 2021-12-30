package com.coderman.infosys.auth.infrast.dao.dataobject;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:按钮表DO类
 * @Author:shenshuai
 * @CreateTime:2021-12-24 23:17:12
 * @version v1.0
 */
@Data
@ToString
public class MenuDO{


	/**  主键 **/
	private Long id;

	/** 按钮名称 **/
	private String menuName;

	/** 按钮编码 **/
	private String menuCode;

	/** 所属菜单编码 **/
	private String moduleCode;

	/** 所属系统编码 **/
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