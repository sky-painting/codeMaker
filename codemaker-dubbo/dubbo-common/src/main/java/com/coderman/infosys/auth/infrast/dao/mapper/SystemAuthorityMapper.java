package com.coderman.infosys.auth.infrast.dao.mapper;

import java.util.List;
import com.coderman.infosys.auth.infrast.dao.dataobject.SystemAuthorityDO;
import com.coderman.infosys.auth.infrast.dao.dataobject.AuthorityDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
* @Description:系统菜单权限表接口
* @Author:
* @CreateTime:2021-12-24 23:17:12
* @version v1.0
*/
@Mapper
public interface SystemAuthorityMapper{

		/**
	 *
	 * @Description 保存数据记录
	 * @param systemAuthorityDO
	 * @return long
	 */
	long insert(@Param(value = "systemAuthorityDO") SystemAuthorityDO systemAuthorityDO);

		/**
	 *
	 * @Description 更新数据记录
	 * @param systemAuthorityDO
	 * @return int
	 */
	int update(@Param(value = "systemAuthorityDO") SystemAuthorityDO systemAuthorityDO);

		/**
	 *
	 * @Description 获取所有数据记录
	 * @return List<SystemAuthorityDO>
	 */
	List<SystemAuthorityDO> getAll();

		/**
	 *
	 * @Description 根据ID获取单条记录
	 * @param id
	 * @return SystemAuthorityDO
	 */
	SystemAuthorityDO getById(@Param(value = "id") Long id);

		/**
	 *
	 * @Description 根据ID删除单条记录
	 * @param id
	 * @return int
	 */
	int deleteById(@Param(value = "id") Long id);

}