package com.coderman.infosys.auth.domain.event;

import com.coderman.infosys.auth.domain.bo.RoleBO;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:角色变更事件类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:18:28
 * @version v1.0
 */
@Data
@ToString
public class RoleChangeEvent{

	/** 角色code **/
    private String roleCode;
	/** 变更事件类型 **/
    private Integer changeEventType;
	/** 角色内容 **/
    private RoleBO roleBO;


}