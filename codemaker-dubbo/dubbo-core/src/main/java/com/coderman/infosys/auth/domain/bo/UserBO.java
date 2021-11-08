package com.coderman.infosys.auth.domain.bo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

 /**
 * @Description:用户信息类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:18:27
 * @version v1.0
 */
@Data
@ToString
public class UserBO {


    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户对应角色列表
     */
    private List<RoleBO> userList;

    /**
     * 用户对应角色组列表
     */
    private List<RoleGroupBO> userList;


}