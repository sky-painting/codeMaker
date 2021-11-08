package com.coderman.infosys.auth.app.convert;

import java.util.List;
import com.coderman.infosys.auth.api.dto.UpdateModuleRequestDTO;
import com.coderman.infosys.auth.api.dto.ModuleResponseDTO;
import com.coderman.infosys.auth.domain.bo.ModuleBO;
import com.coderman.infosys.auth.api.dto.CreateModuleRequestDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:ModuleConvert接口
* @Author:
* @CreateTime:2021-11-07 08:18:28
* @version v1.0
*/
@Mapper
public interface ModuleConvert{
	ModuleConvert INSTANCE = Mappers.getMapper(ModuleConvert.class);

	/**
	 *
	 * @Description:
	 * @return ModuleResponseDTO
	 */
	 ModuleResponseDTO bo2dto(ModuleBO moduleBO);
	/**
	 *
	 * @Description:
	 * @return List<ModuleResponseDTO>
	 */
	 List<ModuleResponseDTO> moduleBOs2dtoList(List<ModuleBO> moduleBOList);
	/**
	 *
	 * @Description:
	 * @return ModuleBO
	 */
	 ModuleBO dto2bo(CreateModuleRequestDTO createModuleRequestDTO);
	/**
	 *
	 * @Description:
	 * @return ModuleBO
	 */
	 ModuleBO dto2bo(UpdateModuleRequestDTO updateModuleRequestDTO);
	/**
	 *
	 * @Description:
	 * @return List<ModuleBO>
	 */
	 List<ModuleBO> createModuleRequestDTOs2boList(List<CreateModuleRequestDTO> createModuleRequestDTOList);
	/**
	 *
	 * @Description:
	 * @return List<ModuleBO>
	 */
	 List<ModuleBO> updateModuleRequestDTOs2boList(List<UpdateModuleRequestDTO> updateModuleRequestDTOList);
}