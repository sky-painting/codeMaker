package com.coderman.infosys.auth.app.convert;

import java.util.List;
import com.coderman.infosys.auth.api.dto.UpdateSystemRequestDTO;
import com.coderman.infosys.auth.domain.bo.SystemBO;
import com.coderman.infosys.auth.api.dto.CreateSystemRequestDTO;
import com.coderman.infosys.auth.api.dto.SystemDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:SystemConvert接口
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@Mapper
public interface SystemConvert{
	SystemConvert INSTANCE = Mappers.getMapper(SystemConvert.class);

	/**
	 *
	 * @Description:
	 * @return List<SystemBO>
	 */
	 List<SystemBO> systemDTOs2boList(List<SystemDTO> systemDTOList);
	/**
	 *
	 * @Description:
	 * @return SystemBO
	 */
	 SystemBO dto2bo(CreateSystemRequestDTO createSystemRequestDTO);
	/**
	 *
	 * @Description:
	 * @return List<SystemBO>
	 */
	 List<SystemBO> createSystemRequestDTOs2boList(List<CreateSystemRequestDTO> createSystemRequestDTOList);
	/**
	 *
	 * @Description:
	 * @return SystemBO
	 */
	 SystemBO dto2bo(UpdateSystemRequestDTO updateSystemRequestDTO);
	/**
	 *
	 * @Description:
	 * @return SystemBO
	 */
	 SystemBO dto2bo(SystemDTO systemDTO);
	/**
	 *
	 * @Description:
	 * @return SystemDTO
	 */
	 SystemDTO bo2dto(SystemBO systemBO);
	/**
	 *
	 * @Description:
	 * @return List<SystemDTO>
	 */
	 List<SystemDTO> systemBOs2dtoList(List<SystemBO> systemBOList);
	/**
	 *
	 * @Description:
	 * @return List<SystemBO>
	 */
	 List<SystemBO> updateSystemRequestDTOs2boList(List<UpdateSystemRequestDTO> updateSystemRequestDTOList);
}