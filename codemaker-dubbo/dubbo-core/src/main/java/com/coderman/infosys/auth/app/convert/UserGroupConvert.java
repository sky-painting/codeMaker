package com.coderman.infosys.auth.app.convert;

import com.coderman.infosys.auth.api.dto.UserGroupResponseDTO;
import java.util.List;
import com.coderman.infosys.auth.api.dto.CreateUserGroupRequestDTO;
import com.coderman.infosys.auth.domain.bo.UserGroupBO;
import com.coderman.infosys.auth.api.dto.UpdateUserGroupRequestDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:UserGroupConvert接口
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
	 * @return List<UserGroupBO>
	 */
	 List<UserGroupBO> updateUserGroupRequestDTOs2boList(List<UpdateUserGroupRequestDTO> updateUserGroupRequestDTOList);
	/**
	 *
	 * @Description:
	 * @return UserGroupResponseDTO
	 */
	 UserGroupResponseDTO bo2dto(UserGroupBO userGroupBO);
	/**
	 *
	 * @Description:
	 * @return UserGroupBO
	 */
	 UserGroupBO dto2bo(UpdateUserGroupRequestDTO updateUserGroupRequestDTO);
	/**
	 *
	 * @Description:
	 * @return List<UserGroupResponseDTO>
	 */
	 List<UserGroupResponseDTO> userGroupBOs2dtoList(List<UserGroupBO> userGroupBOList);
	/**
	 *
	 * @Description:
	 * @return UserGroupBO
	 */
	 UserGroupBO dto2bo(CreateUserGroupRequestDTO createUserGroupRequestDTO);
	/**
	 *
	 * @Description:
	 * @return List<UserGroupBO>
	 */
	 List<UserGroupBO> createUserGroupRequestDTOs2boList(List<CreateUserGroupRequestDTO> createUserGroupRequestDTOList);
}