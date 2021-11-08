package com.coderman.infosys.auth.domain.bo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

 /**
 * @Description:权限聚合根类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:23:49
 * @version v1.0
 */
@Data
@ToString
public class AuthAggregateBO {


	/** 系统权 **/
    public List<SystemBO> systemBOList;

	/** 菜单权 **/
    public List<ModuleBO> moduleBOList;

	/** 按钮权 **/
    public List<MenuBO> menuBOList;

	/** 城市权 **/
    public List<String> cityIdList;

	/** 部门权 **/
    public List<Long> departmentIdList;

	/** 数据字段权 **/
    public List<DataColumnBO> dataColumnBOList;


}