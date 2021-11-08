package com.coderman.infosys.auth.app.convert;

import com.coderman.infosys.auth.api.dto.UpdateRoleGroupRequestDTO;
import java.util.List;
import com.coderman.infosys.auth.api.dto.RoleGroupResponseDTO;
import com.coderman.infosys.auth.domain.bo.RoleGroupBO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:RoleGroupConvert接口
* @Author:
* @CreateTime:2021-11-06 23:38:07
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
	 RoleGroupBO dto2bo(UpdateRoleGroupRequestDTO updateRoleGroupRequestDTO);
	/**
	 *
	 * @Description:
	 * @return RoleGroupResponseDTO
	 */
	 RoleGroupResponseDTO bo2dto(RoleGroupBO roleGroupBO);
	/**
	 *
	 * @Description:
	 * @return List<RoleGroupResponseDTO>
	 */
	 List<RoleGroupResponseDTO> roleGroupBOs2dtoList(List<RoleGroupBO> roleGroupBOList);
	/**
	 *
	 * @Description:
	 * @return List<RoleGroupBO>
	 */
	 List<RoleGroupBO> updateRoleGroupRequestDTOs2boList(List<UpdateRoleGroupRequestDTO> updateRoleGroupRequestDTOList);
}