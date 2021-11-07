package com.coderman.infosys.auth.domain.event;

import com.coderman.infosys.auth.domain.bo.RoleBO;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:角色授权变更事件类
 * @Author:shenshuai
 * @CreateTime:2021-11-06 23:38:06
 * @version v1.0
 */
@Data
@ToString
public class RoleAuthChangeEvent{

	/** 角色code **/
    private String roleCode;
	/** 权限code **/
    private String authCode;
	/** 变更事件类型 **/
    private Integer changeEventType;
	/** 角色内容 **/
    private RoleBO roleBO;


}