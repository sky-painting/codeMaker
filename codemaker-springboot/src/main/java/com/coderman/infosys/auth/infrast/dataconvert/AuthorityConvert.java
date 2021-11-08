package com.coderman.infosys.auth.infrast.dataconvert;

import com.coderman.infosys.auth.domain.bo.AuthorityBO;
import java.util.List;
import com.coderman.infosys.auth.infrast.dao.dataobject.AuthorityDO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:权限接口
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@Mapper
public interface AuthorityConvert{
	AuthorityConvert INSTANCE = Mappers.getMapper(AuthorityConvert.class);

	/**
	 *
	 * @Description:
	 * @return AuthorityBO
	 */
	 AuthorityBO do2bo(AuthorityDO authorityDO);
	/**
	 *
	 * @Description:
	 * @return List<AuthorityBO>
	 */
	 List<AuthorityBO> doList2boList(List<AuthorityDO> authorityDOList);
	/**
	 *
	 * @Description:
	 * @return AuthorityDO
	 */
	 AuthorityDO bo2do(AuthorityBO authorityBO);
	/**
	 *
	 * @Description:
	 * @return List<AuthorityDO>
	 */
	 List<AuthorityDO> boList2doList(List<AuthorityBO> authorityBOList);
}