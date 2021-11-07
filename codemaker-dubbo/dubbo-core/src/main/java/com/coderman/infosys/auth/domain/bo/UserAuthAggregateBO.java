package com.coderman.infosys.auth.domain.bo;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:用户权限聚合根类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:18:27
 * @version v1.0
 */
@Data
@ToString
public class UserAuthAggregateBO  extends  AuthAggregateBO{


    /**
     * 用户ID
     */
    private Long userId;


}