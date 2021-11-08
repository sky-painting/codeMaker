package com.coderman.infosys.auth.infrast.dao.mapper;

import java.util.List;
import com.coderman.infosys.auth.infrast.dao.dataobject.DataAuthorityDO;
import com.coderman.infosys.auth.infrast.dao.dataobject.AuthorityDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
* @Description:数据字段权限接口
* @Author:
* @CreateTime:2021-11-07 08:18:28
* @version v1.0
*/
@Mapper
public interface DataAuthorityMapper{

		/**
	 *
	 * @Description 保存数据记录
	 * @param dataAuthorityDO
	 * @return long
	 */
	long insert(@Param(value = "dataAuthorityDO") DataAuthorityDO dataAuthorityDO);

		/**
	 *
	 * @Description 更新数据记录
	 * @param dataAuthorityDO
	 * @return int
	 */
	int update(@Param(value = "dataAuthorityDO") DataAuthorityDO dataAuthorityDO);

		/**
	 *
	 * @Description 获取所有数据记录
	 * @return List<DataAuthorityDO>
	 */
	List<DataAuthorityDO> getAll();

		/**
	 *
	 * @Description 根据ID获取单条记录
	 * @param id
	 * @return DataAuthorityDO
	 */
	DataAuthorityDO getById(@Param(value = "id") Long id);

		/**
	 *
	 * @Description 根据ID删除单条记录
	 * @param id
	 * @return int
	 */
	int deleteById(@Param(value = "id") Long id);

}