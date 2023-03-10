package com.coderman.infosys.auth.infrast.dao.dataobject;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:数据字段管理表DO类
 * @Author:shenshuai
 * @CreateTime:2021-12-24 23:17:12
 * @version v1.0
 */
@Data
@ToString
public class DataColumnConfigDO{


	/**  主键 **/
	private Long id;

	/** 数据库业务系统名称 **/
	private String busDatabase;

	/** 表名称 **/
	private String tableName;

	/** 表描述 **/
	private String tableDesc;

	/** 字段名称 **/
	private String columnName;

	/** 字段描述 **/
	private String columnDesc;

	/** 业务模块 **/
	private String busModel;

	/** 业务属性名称 **/
	private String fieldName;

	/** 创建时间 **/
	private Date dateCreate;

	/** 修改时间 **/
	private Date dateUpdate;

	/** 修改人 **/
	private Long updateUserId;

	/** 创建人 **/
	private Long createUserId;

}