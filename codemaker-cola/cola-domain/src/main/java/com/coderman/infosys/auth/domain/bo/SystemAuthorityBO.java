package com.coderman.infosys.auth.domain.bo;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:系统菜单权限类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:23:49
 * @version v1.0
 */
@Data
@ToString
public class SystemAuthorityBO  extends  AuthorityBO{


	/** 主键ID **/
    private Long id;

	/** 权限编码标示 **/
    private String authCode;

	/** 业务数据系统标示 **/
    private String systemName;

	/** 功能 **/
    private String funcName;

	/** 菜单 **/
    private String categoryName;

	/** 按钮 **/
    private String menuName;


}