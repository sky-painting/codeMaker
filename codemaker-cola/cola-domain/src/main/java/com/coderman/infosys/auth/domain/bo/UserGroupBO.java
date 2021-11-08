package com.coderman.infosys.auth.domain.bo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

 /**
 * @Description:用户组类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:23:49
 * @version v1.0
 */
@Data
@ToString
public class UserGroupBO {


	/** 主键ID **/
    private Long id;

	/** 组名 **/
    private String groupName;

	/** 组编码 **/
    private String groupCode;

	/** 组内用户列表 **/
    private List<UserBO> userList;

	/** 用户组状态 **/
    private Integer status;

	/** 用户组对应的角色列表 **/
    private List<RoleBO> roleList;

	/** 用户组对应角色组列表 **/
    private List<RoleGroupBO> roleGroupList;


}