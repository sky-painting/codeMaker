package com.coderman.infosys.auth.infrast.dao.mapper;

import java.util.List;
import com.coderman.infosys.auth.infrast.dao.dataobject.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
* @Description:角色表接口
* @Author:
* @CreateTime:2021-12-24 23:17:12
* @version v1.0
*/
@Mapper
public interface RoleMapper{

		/**
	 *
	 * @Description 保存数据记录
	 * @param roleDO
	 * @return long
	 */
	long insert(@Param(value = "roleDO") RoleDO roleDO);

		/**
	 *
	 * @Description 更新数据记录
	 * @param roleDO
	 * @return int
	 */
	int update(@Param(value = "roleDO") RoleDO roleDO);

		/**
	 *
	 * @Description 获取所有数据记录
	 * @return List<RoleDO>
	 */
	List<RoleDO> getAll();

		/**
	 *
	 * @Description 根据ID获取单条记录
	 * @param id
	 * @return RoleDO
	 */
	RoleDO getById(@Param(value = "id") Long id);

		/**
	 *
	 * @Description 根据ID删除单条记录
	 * @param id
	 * @return int
	 */
	int deleteById(@Param(value = "id") Long id);

}