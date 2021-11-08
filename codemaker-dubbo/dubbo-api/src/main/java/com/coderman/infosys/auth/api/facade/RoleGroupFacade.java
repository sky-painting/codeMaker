package com.coderman.infosys.auth.api.facade;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.api.dto.RoleGroupResponseDTO;


/**
* @Description:接口
* @Author:
* @CreateTime:2021-11-07 08:18:28
* @version v1.0
*/
public interface RoleGroupFacade{

	/**
	 *
	 * @Description 获取学生信息
	 * @param number
	 * @return ResultDataDto<RoleGroupResponseDTO>
	 */
	ResultDataDto<RoleGroupResponseDTO> getByNumber(String number);
}