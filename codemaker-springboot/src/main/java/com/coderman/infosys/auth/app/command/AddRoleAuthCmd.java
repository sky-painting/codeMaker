package com.coderman.infosys.auth.app.command;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:对角色权限类
 * @Author:shenshuai
 * @CreateTime:2021-11-06 23:38:06
 * @version v1.0
 */
@Data
@ToString
public class AddRoleAuthCmd  extends  AbstractCmd{


    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限数据内容
     */
    private String json;


}