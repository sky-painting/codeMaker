package com.coderman.infosys.auth.infrast.dataconvert;

import java.util.List;
import com.coderman.infosys.auth.domain.bo.SystemBO;
import com.coderman.infosys.auth.infrast.dao.dataobject.SystemDO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:系统管理接口
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@Mapper
public interface SystemConvert{
	SystemConvert INSTANCE = Mappers.getMapper(SystemConvert.class);

	/**
	 *
	 * @Description:
	 * @return SystemBO
	 */
	 SystemBO do2bo(SystemDO systemDO);
	/**
	 *
	 * @Description:
	 * @return List<SystemBO>
	 */
	 List<SystemBO> doList2boList(List<SystemDO> systemDOList);
	/**
	 *
	 * @Description:
	 * @return SystemDO
	 */
	 SystemDO bo2do(SystemBO systemBO);
	/**
	 *
	 * @Description:
	 * @return List<SystemDO>
	 */
	 List<SystemDO> boList2doList(List<SystemBO> systemBOList);
}