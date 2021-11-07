package com.coderman.infosys.auth.infrast.dao.mapper;

import java.util.List;
import com.coderman.infosys.auth.infrast.dao.dataobject.MenuDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
* @Description:按钮表接口
* @Author:
* @CreateTime:2021-11-07 08:18:28
* @version v1.0
*/
@Mapper
public interface MenuMapper{

		/**
	 *
	 * @Description 保存数据记录
	 * @param menuDO
	 * @return long
	 */
	long insert(@Param(value = "menuDO") MenuDO menuDO);

		/**
	 *
	 * @Description 更新数据记录
	 * @param menuDO
	 * @return int
	 */
	int update(@Param(value = "menuDO") MenuDO menuDO);

		/**
	 *
	 * @Description 获取所有数据记录
	 * @return List<MenuDO>
	 */
	List<MenuDO> getAll();

		/**
	 *
	 * @Description 根据ID获取单条记录
	 * @param id
	 * @return MenuDO
	 */
	MenuDO getById(@Param(value = "id") Long id);

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
	 * @param moduleCode
	 * @return List<MenuDO>
	 */
	List<MenuDO> getListByModuleCode(@Param(value = "moduleCode") String moduleCode);

}