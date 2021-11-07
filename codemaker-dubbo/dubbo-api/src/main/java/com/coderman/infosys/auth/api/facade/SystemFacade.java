package com.coderman.infosys.auth.api.facade;

import com.coderman.utils.response.PageDTO;
import java.util.List;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.api.dto.UpdateSystemRequestDTO;
import com.coderman.infosys.auth.api.dto.SystemDTO;


/**
* @Description:接口
* @Author:
* @CreateTime:2021-11-07 08:18:28
* @version v1.0
*/
public interface SystemFacade{

	/**
	 *
	 * @Description 注册系统
	 * @param systemDTO
	 * @return ResultDataDto<SystemDTO>
	 */
	ResultDataDto<SystemDTO> registSystem(SystemDTO systemDTO);

	/**
	 *
	 * @Description 分页获取系统信息
	 * @param pageDto
	 * @return ResultDataDto<PageDTO<SystemDTO>>
	 */
	ResultDataDto<PageDTO<SystemDTO>> getPageList(PageDTO pageDto);

	/**
	 *
	 * @Description 获取单条系统信息
	 * @param systemCode
	 * @return ResultDataDto<SystemDTO>
	 */
	ResultDataDto<SystemDTO> getBySystemCode(String systemCode);

	/**
	 *
	 * @Description 修改系统信息
	 * @param dto
	 * @return ResultDataDto<Boolean>
	 */
	ResultDataDto<Boolean> updateSystem(UpdateSystemRequestDTO dto);

	/**
	 *
	 * @Description 搜索获取系统信息
	 * @param search
	 * @return ResultDataDto<List<SystemDTO>>
	 */
	ResultDataDto<List<SystemDTO>> getSearchList(String search);
}