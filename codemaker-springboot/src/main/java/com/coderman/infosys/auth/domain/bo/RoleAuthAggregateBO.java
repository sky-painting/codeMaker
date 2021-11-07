package com.coderman.infosys.auth.domain.bo;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:角色权限聚合根类
 * @Author:shenshuai
 * @CreateTime:2021-11-06 23:38:06
 * @version v1.0
 */
@Data
@ToString
public class RoleAuthAggregateBO  extends  AuthAggregateBO{


    /**
     * 角色ID
     */
    private Long roleId;


}