package com.coderman.infosys.auth.app.convert;

import java.util.List;
import com.coderman.infosys.auth.domain.bo.RoleBO;
import com.coderman.infosys.auth.api.dto.CreateRoleRequestDTO;
import com.coderman.infosys.auth.api.dto.RoleResponseDTO;
import com.coderman.infosys.auth.api.dto.UpdateRoleRequestDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:RoleConvert接口
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@Mapper
public interface RoleConvert{
	RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

	/**
	 *
	 * @Description:
	 * @return List<RoleBO>
	 */
	 List<RoleBO> createRoleRequestDTOs2boList(List<CreateRoleRequestDTO> createRoleRequestDTOList);
	/**
	 *
	 * @Description:
	 * @return RoleBO
	 */
	 RoleBO dto2bo(UpdateRoleRequestDTO updateRoleRequestDTO);
	/**
	 *
	 * @Description:
	 * @return RoleResponseDTO
	 */
	 RoleResponseDTO bo2dto(RoleBO roleBO);
	/**
	 *
	 * @Description:
	 * @return List<RoleResponseDTO>
	 */
	 List<RoleResponseDTO> roleBOs2dtoList(List<RoleBO> roleBOList);
	/**
	 *
	 * @Description:
	 * @return List<RoleBO>
	 */
	 List<RoleBO> updateRoleRequestDTOs2boList(List<UpdateRoleRequestDTO> updateRoleRequestDTOList);
	/**
	 *
	 * @Description:
	 * @return RoleBO
	 */
	 RoleBO dto2bo(CreateRoleRequestDTO createRoleRequestDTO);
}