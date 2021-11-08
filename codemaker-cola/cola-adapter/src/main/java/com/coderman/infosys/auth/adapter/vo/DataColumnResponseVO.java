package com.coderman.infosys.auth.adapter.vo;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:数据字段实体类
 * @Author:
 * @CreateTime:2021-11-07 08:23:49
 * @version v1.0
 */
@Data
@ToString
public class DataColumnResponseVO {

	/** 主键ID **/
    private Long id;
	/** 主键ID **/
    private Long id;
	/** 数据库业务系统名称 **/
    private String busDataBase;
	/** 数据库表名称 **/
    private String tableName;
	/** 数据库表描述 **/
    private String tableDesc;
	/** 数据库字段名称 **/
    private String columnName;
	/** 数据库字段描述 **/
    private String columnDesc;
	/** 业务字段名称 **/
    private String fieldName;


}