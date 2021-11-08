package com.coderman.infosys.auth.domain.bo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

 /**
 * @Description:菜单/模块管理类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:23:49
 * @version v1.0
 */
@Data
@ToString
public class ModuleBO {


	/** 主键ID **/
    private Long id;

	/** 菜单名称 **/
    private String moduleName;

	/** 菜单编码 **/
    private String moduleCode;

	/** 菜单链接 **/
    private String moduleUrl;

	/** 系统标示 **/
    private String systemCode;

	/** 按钮列表 **/
    private List<MenuBO> menuBOList;


}