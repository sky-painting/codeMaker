package com.coderman.infosys.auth.adapter.vo;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:菜单/模块管理类
 * @Author:
 * @CreateTime:2021-11-06 23:38:07
 * @version v1.0
 */
@Data
@ToString
public class UpdateModuleRequestVO {

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
    /** private List<MenuBO> menuBOList **/;


}