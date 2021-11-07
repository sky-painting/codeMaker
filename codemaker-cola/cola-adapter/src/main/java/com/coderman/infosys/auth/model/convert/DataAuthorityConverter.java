package com.coderman.infosys.auth.model.convert;

import java.util.List;
import com.coderman.infosys.auth.domain.bo.AuthorityBO;
import com.coderman.infosys.auth.domain.bo.DataAuthorityBO;
import com.coderman.infosys.auth.adapter.vo.DataAuthorityResponseVO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:DataAuthorityConvertervobo接口
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@Mapper
public interface DataAuthorityConverter{
	DataAuthorityConverter INSTANCE = Mappers.getMapper(DataAuthorityConverter.class);

	/**
	 *
	 * @Description:
	 * @return List<DataAuthorityResponseVO>
	 */
	 List<DataAuthorityResponseVO> BOs2VOs(List<DataAuthorityBO> dataAuthorityBOList);
}