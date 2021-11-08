package com.coderman.infosys.auth.api.facade;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.api.dto.CreateAuthorityRequestDTO;
import com.coderman.infosys.auth.api.dto.AuthorityResponseDTO;


/**
* @Description:接口
* @Author:
* @CreateTime:2021-11-07 08:18:28
* @version v1.0
*/
public interface AuthorityFacade{

	/**
	 *
	 * @Description 判断用户有没有访问某系统的权限
	 * @param userId
	 * @param systemCode
	 * @return ResultDataDto<AuthorityResponseDTO>
	 */
	ResultDataDto<AuthorityResponseDTO> checkUserSystemAuth(Long userId,String systemCode);

	/**
	 *
	 * @Description 根据用户和系统获取系统权限信息
	 * @param userId
	 * @param systemCode
	 * @return ResultDataDto<AuthorityResponseDTO>
	 */
	ResultDataDto<AuthorityResponseDTO> getUserSystemAuth(Long userId,String systemCode);

	/**
	 *
	 * @Description 根据权限code获取权限信息
	 * @param authCode
	 * @return ResultDataDto<AuthorityResponseDTO>
	 */
	ResultDataDto<AuthorityResponseDTO> getByCode(String authCode);

	/**
	 *
	 * @Description 保存权限信息
	 * @param createAuthorityRequestDTO
	 * @return ResultDataDto<AuthorityResponseDTO>
	 */
	ResultDataDto<AuthorityResponseDTO> saveAuthority(CreateAuthorityRequestDTO createAuthorityRequestDTO);
}