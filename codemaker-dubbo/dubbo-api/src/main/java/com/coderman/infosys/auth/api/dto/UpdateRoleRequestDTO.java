package com.coderman.infosys.auth.api.dto;


import lombok.Data;
import lombok.ToString;

/**
* @Description:角色类
* @Author:
* @CreateTime:2021-11-07 08:18:28
* @version v1.0
*/
@Data
@ToString
public class UpdateRoleRequestDTO {

	/** 主键ID **/
	private Long id;
	/** 角色名称 **/
	private String roleName;
	/** 角色编码 **/
	private String roleCode;
	/** 角色状态 **/
	private Integer status;
	/** 角色对应用户列表 **/
	/** private List<UserBO> userList **/;


}