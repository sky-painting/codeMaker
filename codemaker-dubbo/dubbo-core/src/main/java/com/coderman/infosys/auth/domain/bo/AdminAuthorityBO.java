package com.coderman.infosys.auth.domain.bo;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:行政权限类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:18:27
 * @version v1.0
 */
@Data
@ToString
public class AdminAuthorityBO  extends  AuthorityBO{


    /**
     * 权限编码标示
     */
    private String authCode;

    /**
     * 组织部门
     */
    private Long departmentId;

    /**
     * 城市
     */
    private Long cityId;

    /**
     * 职位
     */
    private Long jobId;


}