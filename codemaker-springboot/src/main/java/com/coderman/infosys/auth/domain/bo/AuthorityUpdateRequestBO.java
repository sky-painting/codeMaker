package com.coderman.infosys.auth.domain.bo;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:权限更新请求实体类
 * @Author:shenshuai
 * @CreateTime:2021-11-06 23:38:06
 * @version v1.0
 */
@Data
@ToString
public class AuthorityUpdateRequestBO {


    /**
     * 权限编码标示
     */
    private String authCode;


}