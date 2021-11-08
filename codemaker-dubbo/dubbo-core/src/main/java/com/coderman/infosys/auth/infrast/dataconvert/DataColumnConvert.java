package com.coderman.infosys.auth.infrast.dataconvert;

import com.coderman.infosys.auth.infrast.dao.dataobject.DataColumnConfigDO;
import java.util.List;
import com.coderman.infosys.auth.domain.bo.DataColumnBO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:数据字段实体接口
* @Author:
* @CreateTime:2021-11-07 08:18:28
* @version v1.0
*/
@Mapper
public interface DataColumnConvert{
	DataColumnConvert INSTANCE = Mappers.getMapper(DataColumnConvert.class);

	/**
	 *
	 * @Description:
	 * @return DataColumnBO
	 */
	 DataColumnBO do2bo(DataColumnConfigDO dataColumnConfigDO);
	/**
	 *
	 * @Description:
	 * @return List<DataColumnBO>
	 */
	 List<DataColumnBO> doList2boList(List<DataColumnConfigDO> dataColumnConfigDOList);
	/**
	 *
	 * @Description:
	 * @return DataColumnConfigDO
	 */
	 DataColumnConfigDO bo2do(DataColumnBO dataColumnBO);
	/**
	 *
	 * @Description:
	 * @return List<DataColumnConfigDO>
	 */
	 List<DataColumnConfigDO> boList2doList(List<DataColumnBO> dataColumnBOList);
}