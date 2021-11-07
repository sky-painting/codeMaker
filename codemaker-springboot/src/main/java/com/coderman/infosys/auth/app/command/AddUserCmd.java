package com.coderman.infosys.auth.app.command;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:增加用户命令类
 * @Author:shenshuai
 * @CreateTime:2021-11-06 23:38:06
 * @version v1.0
 */
@Data
@ToString
public class AddUserCmd  extends  AbstractCmd{


    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 所属组织
     */
    private Long departmentId;


}