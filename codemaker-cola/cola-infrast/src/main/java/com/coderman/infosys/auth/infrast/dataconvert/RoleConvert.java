package com.coderman.infosys.auth.infrast.dataconvert;

import java.util.List;
import com.coderman.infosys.auth.domain.bo.RoleBO;
import com.coderman.infosys.auth.infrast.dao.dataobject.RoleDO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:角色接口
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@Mapper
public interface RoleConvert{
	RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

	/**
	 *
	 * @Description:
	 * @return RoleBO
	 */
	 RoleBO do2bo(RoleDO roleDO);
	/**
	 *
	 * @Description:
	 * @return List<RoleBO>
	 */
	 List<RoleBO> doList2boList(List<RoleDO> roleDOList);
	/**
	 *
	 * @Description:
	 * @return RoleDO
	 */
	 RoleDO bo2do(RoleBO roleBO);
	/**
	 *
	 * @Description:
	 * @return List<RoleDO>
	 */
	 List<RoleDO> boList2doList(List<RoleBO> roleBOList);
}