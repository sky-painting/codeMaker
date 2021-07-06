package com.lightsnail.snailapp.usercrm.domain.bo;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:系统菜单权限类
 * @Author:fanchunshuai
 * @CreateTime:2021-07-06 17:11:46
 * @version v1.0
 */
@Data
@ToString
public class SystemAuthorityBO  extends  AuthorityBO{

	/** 系统标示 **/
    private String systemName;
	/** 功能 **/
    private String funcName;
	/** 菜单 **/
    private String categoryName;
	/** 按钮 **/
    private String menuName;


}