package com.coderman.infosys.auth.api.facade;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.api.dto.CreateDataAuthorityRequestDTO;
import com.coderman.infosys.auth.api.dto.DataAuthorityResponseDTO;
import com.coderman.infosys.auth.api.dto.AuthorityResponseDTO;


/**
* @Description:接口
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
public interface DataAuthorityFacade{

	/**
	 *
	 * @Description 获取权限信息
	 * @param authCode
	 * @return ResultDataDto<DataAuthorityResponseDTO>
	 */
	ResultDataDto<DataAuthorityResponseDTO> getByCode(String authCode);

	/**
	 *
	 * @Description 保存权限信息
	 * @param createDataAuthorityRequestDTO
	 * @return ResultDataDto<DataAuthorityResponseDTO>
	 */
	ResultDataDto<DataAuthorityResponseDTO> saveAuthority(CreateDataAuthorityRequestDTO createDataAuthorityRequestDTO);
}