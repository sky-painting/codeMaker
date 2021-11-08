package com.coderman.infosys.auth.api.facade;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.api.dto.SystemAuthorityResponseDTO;
import com.coderman.infosys.auth.api.dto.AuthorityResponseDTO;


/**
* @Description:接口
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
public interface SystemAuthorityFacade{

	/**
	 *
	 * @Description 获取权限信息
	 * @param authCode
	 * @return ResultDataDto<SystemAuthorityResponseDTO>
	 */
	ResultDataDto<SystemAuthorityResponseDTO> getByCode(String authCode);
}