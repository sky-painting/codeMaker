package com.coderman.infosys.auth.app.convert;

import com.coderman.infosys.auth.api.dto.UpdateAuthorityRequestDTO;
import com.coderman.infosys.auth.domain.bo.AuthorityBO;
import java.util.List;
import com.coderman.infosys.auth.api.dto.CreateAuthorityRequestDTO;
import com.coderman.infosys.auth.api.dto.AuthorityResponseDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:AuthorityConvert接口
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
	 * @return AuthorityResponseDTO
	 */
	 AuthorityResponseDTO bo2dto(AuthorityBO authorityBO);
	/**
	 *
	 * @Description:
	 * @return List<AuthorityResponseDTO>
	 */
	 List<AuthorityResponseDTO> authorityBOs2dtoList(List<AuthorityBO> authorityBOList);
	/**
	 *
	 * @Description:
	 * @return AuthorityBO
	 */
	 AuthorityBO dto2bo(UpdateAuthorityRequestDTO updateAuthorityRequestDTO);
	/**
	 *
	 * @Description:
	 * @return List<AuthorityBO>
	 */
	 List<AuthorityBO> createAuthorityRequestDTOs2boList(List<CreateAuthorityRequestDTO> createAuthorityRequestDTOList);
	/**
	 *
	 * @Description:
	 * @return AuthorityBO
	 */
	 AuthorityBO dto2bo(CreateAuthorityRequestDTO createAuthorityRequestDTO);
	/**
	 *
	 * @Description:
	 * @return List<AuthorityBO>
	 */
	 List<AuthorityBO> updateAuthorityRequestDTOs2boList(List<UpdateAuthorityRequestDTO> updateAuthorityRequestDTOList);
}