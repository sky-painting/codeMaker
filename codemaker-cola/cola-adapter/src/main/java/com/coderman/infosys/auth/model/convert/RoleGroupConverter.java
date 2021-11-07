package com.coderman.infosys.auth.model.convert;

import com.coderman.infosys.auth.adapter.vo.RoleGroupVO;
import java.util.List;
import com.coderman.infosys.auth.domain.bo.RoleGroupBO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:RoleGroupConvertervobo接口
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@Mapper
public interface RoleGroupConverter{
	RoleGroupConverter INSTANCE = Mappers.getMapper(RoleGroupConverter.class);

	/**
	 *
	 * @Description:
	 * @return RoleGroupBO
	 */
	 RoleGroupBO vo2bo(RoleGroupVO roleGroupVO);
	/**
	 *
	 * @Description:
	 * @return RoleGroupVO
	 */
	 RoleGroupVO bo2VO(RoleGroupBO roleGroupBO);
	/**
	 *
	 * @Description:
	 * @return List<RoleGroupBO>
	 */
	 List<RoleGroupBO> VOs2BOs(List<RoleGroupVO> vOList);
	/**
	 *
	 * @Description:
	 * @return List<RoleGroupVO>
	 */
	 List<RoleGroupVO> BOs2VOs(List<RoleGroupBO> roleGroupBOList);
}