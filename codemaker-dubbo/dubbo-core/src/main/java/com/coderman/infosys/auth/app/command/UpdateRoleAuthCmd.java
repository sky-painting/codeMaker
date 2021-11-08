package com.coderman.infosys.auth.app.command;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:更新角色权限类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:18:28
 * @version v1.0
 */
@Data
@ToString
public class UpdateRoleAuthCmd  extends  AbstractCmd{


    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 权限数据内容
     */
    private String json;


}