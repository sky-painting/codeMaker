package com.coderman.infosys.auth.infrast.dao.mapper;

import java.util.List;
import com.coderman.infosys.auth.infrast.dao.dataobject.UserGroupDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
* @Description:用户组接口
* @Author:
* @CreateTime:2021-11-07 08:18:28
* @version v1.0
*/
@Mapper
public interface UserGroupMapper{

		/**
	 *
	 * @Description 保存数据记录
	 * @param userGroupDO
	 * @return long
	 */
	long insert(@Param(value = "userGroupDO") UserGroupDO userGroupDO);

		/**
	 *
	 * @Description 更新数据记录
	 * @param userGroupDO
	 * @return int
	 */
	int update(@Param(value = "userGroupDO") UserGroupDO userGroupDO);

		/**
	 *
	 * @Description 获取所有数据记录
	 * @return List<UserGroupDO>
	 */
	List<UserGroupDO> getAll();

		/**
	 *
	 * @Description 根据ID获取单条记录
	 * @param id
	 * @return UserGroupDO
	 */
	UserGroupDO getById(@Param(value = "id") Long id);

		/**
	 *
	 * @Description 根据ID删除单条记录
	 * @param id
	 * @return int
	 */
	int deleteById(@Param(value = "id") Long id);

}