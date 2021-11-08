package com.coderman.infosys.auth.infrast.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.coderman.infosys.auth.infrast.dao.dataobject.ModuleDO;
import java.util.List;


/**
* @Description:菜单模块表接口
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@Mapper
public interface ModuleMapper{

	/**
	 *
	 * @Description 保存数据记录
	 * @param moduleDO
	 * @return long
	 */
	long insert(@Param(value = "moduleDO") ModuleDO moduleDO);

	/**
	 *
	 * @Description 更新数据记录
	 * @param moduleDO
	 * @return int
	 */
	int update(@Param(value = "moduleDO") ModuleDO moduleDO);

	/**
	 *
	 * @Description 获取所有数据记录
	 * @return List<ModuleDO>
	 */
	List<ModuleDO> getAll();

	/**
	 *
	 * @Description 根据ID获取单条记录
	 * @param id
	 * @return ModuleDO
	 */
	ModuleDO getById(@Param(value = "id") Long id);

	/**
	 *
	 * @Description 根据ID删除单条记录
	 * @param id
	 * @return int
	 */
	int deleteById(@Param(value = "id") Long id);

	/**
	 *
	 * @Description 查询
	 * @param moduleName
	 * @return List<ModuleDO>
	 */
	List<ModuleDO> getByName(@Param(value = "moduleName") String moduleName);

}