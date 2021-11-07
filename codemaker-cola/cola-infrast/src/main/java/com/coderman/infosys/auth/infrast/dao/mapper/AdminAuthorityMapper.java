package com.coderman.infosys.auth.infrast.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.coderman.infosys.auth.infrast.dao.dataobject.AdminAuthorityDO;
import com.coderman.infosys.auth.infrast.dao.dataobject.AuthorityDO;


/**
* @Description:行政数据权限接口
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@Mapper
public interface AdminAuthorityMapper{

	/**
	 *
	 * @Description 保存数据记录
	 * @param adminAuthorityDO
	 * @return long
	 */
	long insert(@Param(value = "adminAuthorityDO") AdminAuthorityDO adminAuthorityDO);

	/**
	 *
	 * @Description 更新数据记录
	 * @param adminAuthorityDO
	 * @return int
	 */
	int update(@Param(value = "adminAuthorityDO") AdminAuthorityDO adminAuthorityDO);

	/**
	 *
	 * @Description 获取所有数据记录
	 * @return List<AdminAuthorityDO>
	 */
	List<AdminAuthorityDO> getAll();

	/**
	 *
	 * @Description 根据ID获取单条记录
	 * @param id
	 * @return AdminAuthorityDO
	 */
	AdminAuthorityDO getById(@Param(value = "id") Long id);

	/**
	 *
	 * @Description 根据ID删除单条记录
	 * @param id
	 * @return int
	 */
	int deleteById(@Param(value = "id") Long id);

}