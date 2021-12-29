package com.coderman.infosys.auth.infrast.dao.mapper;

import java.util.List;
import com.coderman.infosys.auth.infrast.dao.dataobject.RoleGroupDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
* @Description:角色组接口
* @Author:
* @CreateTime:2021-12-24 23:17:12
* @version v1.0
*/
@Mapper
public interface RoleGroupMapper{

		/**
	 *
	 * @Description 保存数据记录
	 * @param roleGroupDO
	 * @return long
	 */
	long insert(@Param(value = "roleGroupDO") RoleGroupDO roleGroupDO);

		/**
	 *
	 * @Description 更新数据记录
	 * @param roleGroupDO
	 * @return int
	 */
	int update(@Param(value = "roleGroupDO") RoleGroupDO roleGroupDO);

		/**
	 *
	 * @Description 获取所有数据记录
	 * @return List<RoleGroupDO>
	 */
	List<RoleGroupDO> getAll();

		/**
	 *
	 * @Description 根据ID获取单条记录
	 * @param id
	 * @return RoleGroupDO
	 */
	RoleGroupDO getById(@Param(value = "id") Long id);

		/**
	 *
	 * @Description 根据ID删除单条记录
	 * @param id
	 * @return int
	 */
	int deleteById(@Param(value = "id") Long id);

}