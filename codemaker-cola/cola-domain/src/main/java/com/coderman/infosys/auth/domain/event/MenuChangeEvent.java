package com.coderman.infosys.auth.domain.event;

import com.coderman.infosys.auth.domain.bo.MenuBO;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:按钮信息变更事件类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:23:49
 * @version v1.0
 */
@Data
@ToString
public class MenuChangeEvent{

	/** 按钮code **/
    private String menuCode;
	/** 变更事件类型 **/
    private Integer changeEventType;
	/** 按钮内容 **/
    private MenuBO menuBO;


}