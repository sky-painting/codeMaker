package com.coderman.infosys.auth.app.convert;

import com.coderman.infosys.auth.domain.bo.SystemAuthorityBO;
import com.coderman.infosys.auth.domain.bo.AuthorityBO;
import java.util.List;
import com.coderman.infosys.auth.api.dto.UpdateSystemAuthorityRequestDTO;
import com.coderman.infosys.auth.api.dto.SystemAuthorityResponseDTO;
import com.coderman.infosys.auth.api.dto.CreateSystemAuthorityRequestDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:SystemAuthorityConvert接口
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@Mapper
public interface SystemAuthorityConvert{
	SystemAuthorityConvert INSTANCE = Mappers.getMapper(SystemAuthorityConvert.class);

	/**
	 *
	 * @Description:
	 * @return SystemAuthorityResponseDTO
	 */
	 SystemAuthorityResponseDTO bo2dto(SystemAuthorityBO systemAuthorityBO);
	/**
	 *
	 * @Description:
	 * @return SystemAuthorityBO
	 */
	 SystemAuthorityBO dto2bo(CreateSystemAuthorityRequestDTO createSystemAuthorityRequestDTO);
	/**
	 *
	 * @Description:
	 * @return List<SystemAuthorityBO>
	 */
	 List<SystemAuthorityBO> createSystemAuthorityRequestDTOs2boList(List<CreateSystemAuthorityRequestDTO> createSystemAuthorityRequestDTOList);
	/**
	 *
	 * @Description:
	 * @return List<SystemAuthorityBO>
	 */
	 List<SystemAuthorityBO> updateSystemAuthorityRequestDTOs2boList(List<UpdateSystemAuthorityRequestDTO> updateSystemAuthorityRequestDTOList);
	/**
	 *
	 * @Description:
	 * @return SystemAuthorityBO
	 */
	 SystemAuthorityBO dto2bo(UpdateSystemAuthorityRequestDTO updateSystemAuthorityRequestDTO);
	/**
	 *
	 * @Description:
	 * @return List<SystemAuthorityResponseDTO>
	 */
	 List<SystemAuthorityResponseDTO> systemAuthorityBOs2dtoList(List<SystemAuthorityBO> systemAuthorityBOList);
}