package com.coderman.infosys.auth.api.dto;


import lombok.Data;
import lombok.ToString;

/**
* @Description:权限类
* @Author:
* @CreateTime:2021-11-07 08:18:28
* @version v1.0
*/
@Data
@ToString
public class AuthorityResponseDTO {

	/** 主键ID **/
	private Long id;
	/** 权限编码标示 **/
	private String authCode;
	/** 权限类型 **/
	private Integer authorityType;
	/** 关联角色 **/
	private String roleCode;
	/** 关联角色组 **/
	private String roleGroupCode;
	/** 权限状态 **/
	private Integer status;
	/** 数据字段权限列表 **/
	/** private List<DataAuthorityBO> dataAuthorityBOList **/;
	/** 系统菜单权限列表 **/
	/** private List<SystemAuthorityBO> systemAuthorityBOList **/;
	/** 行政权限列表 **/
	/** private List<AdminAuthorityBO> adminAuthorityBOList **/;


}