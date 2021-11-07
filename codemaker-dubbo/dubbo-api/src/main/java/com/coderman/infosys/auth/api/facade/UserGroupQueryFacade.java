package com.coderman.infosys.auth.api.facade;

import com.coderman.infosys.auth.api.dto.UserGroupResponseDTO;
import com.coderman.utils.response.ResultDataDto;


/**
* @Description:接口
* @Author:
* @CreateTime:2021-11-07 08:18:28
* @version v1.0
*/
public interface UserGroupQueryFacade{

	/**
	 *
	 * @Description 获取用户组信息
	 * @param groupCode
	 * @return ResultDataDto<UserGroupResponseDTO>
	 */
	ResultDataDto<UserGroupResponseDTO> getByGroupCode(String groupCode);
}