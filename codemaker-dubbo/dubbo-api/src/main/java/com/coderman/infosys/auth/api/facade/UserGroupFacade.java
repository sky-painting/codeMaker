package com.coderman.infosys.auth.api.facade;

import com.coderman.infosys.auth.api.dto.UserGroupResponseDTO;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.api.dto.CreateUserGroupRequestDTO;


/**
* @Description:接口
* @Author:
* @CreateTime:2021-11-07 08:18:28
* @version v1.0
*/
public interface UserGroupFacade{

	/**
	 *
	 * @Description 创建用户组
	 * @param createUserGroupRequestDTO
	 * @return ResultDataDto<UserGroupResponseDTO>
	 */
	ResultDataDto<UserGroupResponseDTO> createUserGroup(CreateUserGroupRequestDTO createUserGroupRequestDTO);
}