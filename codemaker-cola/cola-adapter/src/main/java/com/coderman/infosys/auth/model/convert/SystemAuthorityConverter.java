package com.coderman.infosys.auth.model.convert;

import com.coderman.infosys.auth.domain.bo.SystemAuthorityBO;
import com.coderman.infosys.auth.domain.bo.AuthorityBO;
import java.util.List;
import com.coderman.infosys.auth.adapter.vo.SystemAuthorityVO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:SystemAuthorityConvertervobo接口
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@Mapper
public interface SystemAuthorityConverter{
	SystemAuthorityConverter INSTANCE = Mappers.getMapper(SystemAuthorityConverter.class);

	/**
	 *
	 * @Description:
	 * @return SystemAuthorityBO
	 */
	 SystemAuthorityBO vo2bo(SystemAuthorityVO systemAuthorityVO);
	/**
	 *
	 * @Description:
	 * @return SystemAuthorityVO
	 */
	 SystemAuthorityVO bo2VO(SystemAuthorityBO systemAuthorityBO);
	/**
	 *
	 * @Description:
	 * @return List<SystemAuthorityVO>
	 */
	 List<SystemAuthorityVO> BOs2VOs(List<SystemAuthorityBO> systemAuthorityBOList);
	/**
	 *
	 * @Description:
	 * @return List<SystemAuthorityBO>
	 */
	 List<SystemAuthorityBO> vos2bos(List<SystemAuthorityVO> vOList);
}