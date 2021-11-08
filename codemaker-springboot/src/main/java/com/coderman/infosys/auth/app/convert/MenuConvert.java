package com.coderman.infosys.auth.app.convert;

import com.coderman.infosys.auth.domain.bo.MenuBO;
import java.util.List;
import com.coderman.infosys.auth.api.dto.UpdateMenuRequestDTO;
import com.coderman.infosys.auth.api.dto.CreateMenuRequestDTO;
import com.coderman.infosys.auth.api.dto.MenuResponseDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:MenuConvert接口
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@Mapper
public interface MenuConvert{
	MenuConvert INSTANCE = Mappers.getMapper(MenuConvert.class);

	/**
	 *
	 * @Description:
	 * @return MenuResponseDTO
	 */
	 MenuResponseDTO bo2dto(MenuBO menuBO);
	/**
	 *
	 * @Description:
	 * @return List<MenuResponseDTO>
	 */
	 List<MenuResponseDTO> menuBOs2dtoList(List<MenuBO> menuBOList);
	/**
	 *
	 * @Description:
	 * @return MenuBO
	 */
	 MenuBO dto2bo(UpdateMenuRequestDTO updateMenuRequestDTO);
	/**
	 *
	 * @Description:
	 * @return List<MenuBO>
	 */
	 List<MenuBO> updateMenuRequestDTOs2boList(List<UpdateMenuRequestDTO> updateMenuRequestDTOList);
	/**
	 *
	 * @Description:
	 * @return MenuBO
	 */
	 MenuBO dto2bo(CreateMenuRequestDTO createMenuRequestDTO);
	/**
	 *
	 * @Description:
	 * @return List<MenuBO>
	 */
	 List<MenuBO> createMenuRequestDTOs2boList(List<CreateMenuRequestDTO> createMenuRequestDTOList);
}