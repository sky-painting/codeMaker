package com.coderman.infosys.auth.app.convert;

import java.util.List;
import com.coderman.infosys.auth.domain.bo.AuthorityBO;
import com.coderman.infosys.auth.api.dto.UpdateDataAuthorityRequestDTO;
import com.coderman.infosys.auth.api.dto.CreateDataAuthorityRequestDTO;
import com.coderman.infosys.auth.api.dto.DataAuthorityResponseDTO;
import com.coderman.infosys.auth.domain.bo.DataAuthorityBO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:DataAuthorityConvert接口
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@Mapper
public interface DataAuthorityConvert{
	DataAuthorityConvert INSTANCE = Mappers.getMapper(DataAuthorityConvert.class);

	/**
	 *
	 * @Description:
	 * @return List<DataAuthorityBO>
	 */
	 List<DataAuthorityBO> createDataAuthorityRequestDTOs2boList(List<CreateDataAuthorityRequestDTO> createDataAuthorityRequestDTOList);
	/**
	 *
	 * @Description:
	 * @return List<DataAuthorityBO>
	 */
	 List<DataAuthorityBO> updateDataAuthorityRequestDTOs2boList(List<UpdateDataAuthorityRequestDTO> updateDataAuthorityRequestDTOList);
	/**
	 *
	 * @Description:
	 * @return DataAuthorityBO
	 */
	 DataAuthorityBO dto2bo(CreateDataAuthorityRequestDTO createDataAuthorityRequestDTO);
	/**
	 *
	 * @Description:
	 * @return DataAuthorityBO
	 */
	 DataAuthorityBO dto2bo(UpdateDataAuthorityRequestDTO updateDataAuthorityRequestDTO);
	/**
	 *
	 * @Description:
	 * @return DataAuthorityResponseDTO
	 */
	 DataAuthorityResponseDTO bo2dto(DataAuthorityBO dataAuthorityBO);
	/**
	 *
	 * @Description:
	 * @return List<DataAuthorityResponseDTO>
	 */
	 List<DataAuthorityResponseDTO> dataAuthorityBOs2dtoList(List<DataAuthorityBO> dataAuthorityBOList);
}