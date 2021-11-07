package com.coderman.infosys.auth.api.dto;


import lombok.Data;
import lombok.ToString;

/**
* @Description:系统管理类
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@Data
@ToString
public class CreateSystemRequestDTO {

	/** 主键ID **/
	private Long id;
	/** 系统名称 **/
	private String systemName;
	/** 系统标示 **/
	private String systemCode;
	/** 系统域名 **/
	private String systemUrl;
	/** 系统图标 **/
	private String systemPicUrl;
	/** 系统技术负责人 **/
	private Long tecAdminUserId;
	/** 系统业务负责人 **/
	private Long busAdminUserId;
	/** 系统当前所在部门 **/
	private Long departmentId;
	/** 系统模块列表 **/
	/** private List<ModuleBO> moduleBOList **/;


}