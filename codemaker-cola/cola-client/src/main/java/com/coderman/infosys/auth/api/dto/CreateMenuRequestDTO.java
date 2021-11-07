package com.coderman.infosys.auth.api.dto;


import lombok.Data;
import lombok.ToString;

/**
* @Description:按钮管理类
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@Data
@ToString
public class CreateMenuRequestDTO {

	/** 主键ID **/
	private Long id;
	/** 按钮名称 **/
	private String menuName;
	/** 按钮编码 **/
	private String menuCode;
	/** 按钮链接 **/
	private String menuUrl;
	/** 系统编码 **/
	private String systemCode;
	/** 菜单编码 **/
	private String moduleCode;


}