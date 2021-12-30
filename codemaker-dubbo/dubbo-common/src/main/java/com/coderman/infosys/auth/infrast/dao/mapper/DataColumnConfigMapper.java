package com.coderman.infosys.auth.infrast.dao.mapper;

import com.coderman.infosys.auth.infrast.dao.dataobject.DataColumnConfigDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
* @Description:数据字段管理表接口
* @Author:
* @CreateTime:2021-12-24 23:17:12
* @version v1.0
*/
@Mapper
public interface DataColumnConfigMapper{

		/**
	 *
	 * @Description 保存数据记录
	 * @param dataColumnConfigDO
	 * @return long
	 */
	long insert(@Param(value = "dataColumnConfigDO") DataColumnConfigDO dataColumnConfigDO);

		/**
	 *
	 * @Description 更新数据记录
	 * @param dataColumnConfigDO
	 * @return int
	 */
	int update(@Param(value = "dataColumnConfigDO") DataColumnConfigDO dataColumnConfigDO);

		/**
	 *
	 * @Description 获取所有数据记录
	 * @return List<DataColumnConfigDO>
	 */
	List<DataColumnConfigDO> getAll();

		/**
	 *
	 * @Description 根据ID获取单条记录
	 * @param id
	 * @return DataColumnConfigDO
	 */
	DataColumnConfigDO getById(@Param(value = "id") Long id);

		/**
	 *
	 * @Description 根据ID删除单条记录
	 * @param id
	 * @return int
	 */
	int deleteById(@Param(value = "id") Long id);

}