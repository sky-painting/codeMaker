package com.coderman.infosys.auth.infrast.dataconvert;

import java.util.List;
import com.coderman.infosys.auth.domain.bo.RoleGroupBO;
import com.coderman.infosys.auth.infrast.dao.dataobject.RoleGroupDO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:角色组接口
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@Mapper
public interface RoleGroupConvert{
	RoleGroupConvert INSTANCE = Mappers.getMapper(RoleGroupConvert.class);

	/**
	 *
	 * @Description:
	 * @return RoleGroupBO
	 */
	 RoleGroupBO do2bo(RoleGroupDO roleGroupDO);
	/**
	 *
	 * @Description:
	 * @return List<RoleGroupBO>
	 */
	 List<RoleGroupBO> doList2boList(List<RoleGroupDO> roleGroupDOList);
	/**
	 *
	 * @Description:
	 * @return RoleGroupDO
	 */
	 RoleGroupDO bo2do(RoleGroupBO roleGroupBO);
	/**
	 *
	 * @Description:
	 * @return List<RoleGroupDO>
	 */
	 List<RoleGroupDO> boList2doList(List<RoleGroupBO> roleGroupBOList);
}