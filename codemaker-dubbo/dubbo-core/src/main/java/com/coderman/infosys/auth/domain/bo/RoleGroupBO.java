package com.coderman.infosys.auth.domain.bo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

 /**
 * @Description:角色组类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:18:27
 * @version v1.0
 */
@Data
@ToString
public class RoleGroupBO {


    /**
     * 主键ID
     */
    private Long id;

    /**
     * 角色组名称
     */
    private String roleName;

    /**
     * 角色组编码
     */
    private String roleCode;

    /**
     * 组内角色列表
     */
    private List<RoleBO> roleList;

    /**
     * 角色状态
     */
    private Integer status;


}