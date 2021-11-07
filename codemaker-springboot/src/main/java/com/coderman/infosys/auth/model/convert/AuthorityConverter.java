package com.coderman.infosys.auth.model.convert;

import java.util.List;
import com.coderman.infosys.auth.domain.bo.AuthorityBO;
import com.coderman.infosys.auth.adapter.vo.AuthorityVO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:AuthorityConvertervobo接口
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@Mapper
public interface AuthorityConverter{
	AuthorityConverter INSTANCE = Mappers.getMapper(AuthorityConverter.class);

	/**
	 *
	 * @Description:
	 * @return List<AuthorityBO>
	 */
	 List<AuthorityBO> volist2boList(List<AuthorityVO> vOList);
	/**
	 *
	 * @Description:
	 * @return AuthorityBO
	 */
	 AuthorityBO vo2bo(AuthorityVO authorityVO);
	/**
	 *
	 * @Description:
	 * @return List<AuthorityVO>
	 */
	 List<AuthorityVO> BOs2VOs(List<AuthorityBO> authorityBOList);
	/**
	 *
	 * @Description:
	 * @return AuthorityVO
	 */
	 AuthorityVO bo2VO(AuthorityBO authorityBO);
}