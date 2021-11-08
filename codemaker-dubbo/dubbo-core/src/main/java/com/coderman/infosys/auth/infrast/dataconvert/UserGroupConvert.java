package com.coderman.infosys.auth.infrast.dataconvert;

import java.util.List;
import com.coderman.infosys.auth.domain.bo.UserGroupBO;
import com.coderman.infosys.auth.infrast.dao.dataobject.UserGroupDO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:用户组接口
* @Author:
* @CreateTime:2021-11-07 08:18:28
* @version v1.0
*/
@Mapper
public interface UserGroupConvert{
	UserGroupConvert INSTANCE = Mappers.getMapper(UserGroupConvert.class);

	/**
	 *
	 * @Description:
	 * @return UserGroupBO
	 */
	 UserGroupBO do2bo(UserGroupDO userGroupDO);
	/**
	 *
	 * @Description:
	 * @return List<UserGroupBO>
	 */
	 List<UserGroupBO> doList2boList(List<UserGroupDO> userGroupDOList);
	/**
	 *
	 * @Description:
	 * @return UserGroupDO
	 */
	 UserGroupDO bo2do(UserGroupBO userGroupBO);
	/**
	 *
	 * @Description:
	 * @return List<UserGroupDO>
	 */
	 List<UserGroupDO> boList2doList(List<UserGroupBO> userGroupBOList);
}