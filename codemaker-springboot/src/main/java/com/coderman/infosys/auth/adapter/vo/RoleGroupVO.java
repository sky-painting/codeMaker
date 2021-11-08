package com.coderman.infosys.auth.adapter.vo;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:角色组类
 * @Author:
 * @CreateTime:2021-11-06 23:38:06
 * @version v1.0
 */
@Data
@ToString
public class RoleGroupVO {

	/** 主键ID **/
    private Long id;
	/** 角色组名称 **/
    private String roleName;
	/** 角色组编码 **/
    private String roleCode;
	/** 角色状态 **/
    private Integer status;
	/** 组内角色列表 **/
    /** private List<RoleBO> roleList **/;


}