package com.coderman.infosys.auth.app.command;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:增加用户组命令类
 * @Author:shenshuai
 * @CreateTime:2021-11-06 23:38:06
 * @version v1.0
 */
@Data
@ToString
public class AddUserGroupCmd  extends  AbstractCmd{


    /**
     * 组名
     */
    private String groupName;

    /**
     * 用户ID
     */
    private Long userId;


}