package com.coderman.infosys.auth.infrast.dao.mapper;

import java.util.List;
import com.coderman.infosys.auth.infrast.dao.dataobject.UserRoleMappingDO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



/**
* @Description:用户角色关联表接口
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@Mapper
public interface UserRoleMappingMapper{

		/**
	 *
	 * @Description 保存数据记录
	 * @param userRoleMappingDO
	 * @return long
	 */
	long insert(@Param(value = "userRoleMappingDO") UserRoleMappingDO userRoleMappingDO);

		/**
	 *
	 * @Description 更新数据记录
	 * @param userRoleMappingDO
	 * @return int
	 */
	int update(@Param(value = "userRoleMappingDO") UserRoleMappingDO userRoleMappingDO);

		/**
	 *
	 * @Description 获取所有数据记录
	 * @return List<UserRoleMappingDO>
	 */
	List<UserRoleMappingDO> getAll();

		/**
	 *
	 * @Description 根据ID获取单条记录
	 * @param id
	 * @return UserRoleMappingDO
	 */
	UserRoleMappingDO getById(@Param(value = "id") Long id);

		/**
	 *
	 * @Description 根据ID删除单条记录
	 * @param id
	 * @return int
	 */
	int deleteById(@Param(value = "id") Long id);

}