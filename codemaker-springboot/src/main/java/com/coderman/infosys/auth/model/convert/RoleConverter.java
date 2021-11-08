package com.coderman.infosys.auth.model.convert;

import java.util.List;
import com.coderman.infosys.auth.domain.bo.RoleBO;
import com.coderman.infosys.auth.adapter.vo.CreateRoleRequestVO;
import com.coderman.infosys.auth.adapter.vo.RoleVO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:RoleConvertervobo接口
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@Mapper
public interface RoleConverter{
	RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

	/**
	 *
	 * @Description:
	 * @return RoleVO
	 */
	 RoleVO bo2VO(RoleBO roleBO);
	/**
	 *
	 * @Description:
	 * @return List<RoleBO>
	 */
	 List<RoleBO> VOs2BOs(List<CreateRoleRequestVO> createRoleRequestVOList);
	/**
	 *
	 * @Description:
	 * @return RoleBO
	 */
	 RoleBO vo2bo(CreateRoleRequestVO createRoleRequestVO);
	/**
	 *
	 * @Description:
	 * @return List<RoleBO>
	 */
	 List<RoleBO> voList2boList(List<RoleVO> vOList);
	/**
	 *
	 * @Description:
	 * @return List<RoleVO>
	 */
	 List<RoleVO> BOs2VOs(List<RoleBO> roleBOList);
	/**
	 *
	 * @Description:
	 * @return RoleBO
	 */
	 RoleBO vo2bo(RoleVO roleVO);
}