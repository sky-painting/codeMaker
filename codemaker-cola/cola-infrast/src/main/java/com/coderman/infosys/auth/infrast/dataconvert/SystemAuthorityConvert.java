package com.coderman.infosys.auth.infrast.dataconvert;

import com.coderman.infosys.auth.domain.bo.SystemAuthorityBO;
import com.coderman.infosys.auth.domain.bo.AuthorityBO;
import java.util.List;
import com.coderman.infosys.auth.infrast.dao.dataobject.AuthorityDO;
import com.coderman.infosys.auth.infrast.dao.dataobject.SystemAuthorityDO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:系统菜单权限接口
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@Mapper
public interface SystemAuthorityConvert{
	SystemAuthorityConvert INSTANCE = Mappers.getMapper(SystemAuthorityConvert.class);

	/**
	 *
	 * @Description:
	 * @return SystemAuthorityBO
	 */
	 SystemAuthorityBO do2bo(SystemAuthorityDO systemAuthorityDO);
	/**
	 *
	 * @Description:
	 * @return List<SystemAuthorityBO>
	 */
	 List<SystemAuthorityBO> doList2boList(List<SystemAuthorityDO> systemAuthorityDOList);
	/**
	 *
	 * @Description:
	 * @return SystemAuthorityDO
	 */
	 SystemAuthorityDO bo2do(SystemAuthorityBO systemAuthorityBO);
	/**
	 *
	 * @Description:
	 * @return List<SystemAuthorityDO>
	 */
	 List<SystemAuthorityDO> boList2doList(List<SystemAuthorityBO> systemAuthorityBOList);
}