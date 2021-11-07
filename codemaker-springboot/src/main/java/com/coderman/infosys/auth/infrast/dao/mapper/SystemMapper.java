package com.coderman.infosys.auth.infrast.dao.mapper;

import java.util.List;
import com.coderman.utils.commonbo.PageBO;
import com.coderman.infosys.auth.infrast.dao.dataobject.SystemDO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



/**
* @Description:系统表接口
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@Mapper
public interface SystemMapper{

		/**
	 *
	 * @Description 保存数据记录
	 * @param systemDO
	 * @return long
	 */
	long insert(@Param(value = "systemDO") SystemDO systemDO);

		/**
	 *
	 * @Description 更新数据记录
	 * @param systemDO
	 * @return int
	 */
	int update(@Param(value = "systemDO") SystemDO systemDO);

		/**
	 *
	 * @Description 获取所有数据记录
	 * @return List<SystemDO>
	 */
	List<SystemDO> getAll();

		/**
	 *
	 * @Description 根据ID获取单条记录
	 * @param id
	 * @return SystemDO
	 */
	SystemDO getById(@Param(value = "id") Long id);

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
	 * @param systemName
	 * @return List<SystemDO>
	 */
	List<SystemDO> getByName(@Param(value = "systemName") String systemName);

		/**
	 *
	 * @Description 查询
	 * @param pageBO
	 * @return List<SystemDO>
	 */
	List<SystemDO> getPageList(@Param(value = "pageBO") PageBO pageBO);

		/**
	 *
	 * @Description 查询
	 * @return List<SystemDO>
	 */
	List<SystemDO> queryByCondition();

		/**
	 *
	 * @Description 查询
	 * @param systemCode
	 * @return List<SystemDO>
	 */
	List<SystemDO> queryBySystemCode(@Param(value = "systemCode") String systemCode);

		/**
	 *
	 * @Description 查询
	 * @return int
	 */
	int updateSystem();

}