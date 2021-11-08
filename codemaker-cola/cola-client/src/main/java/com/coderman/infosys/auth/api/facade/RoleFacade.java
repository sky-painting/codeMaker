package com.coderman.infosys.auth.api.facade;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.api.dto.RoleResponseDTO;


/**
* @Description:接口
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
public interface RoleFacade{

	/**
	 *
	 * @Description 获取角色详情
	 * @param roleCode
	 * @return ResultDataDto<RoleResponseDTO>
	 */
	ResultDataDto<RoleResponseDTO> getByRoleCode(String roleCode);
}