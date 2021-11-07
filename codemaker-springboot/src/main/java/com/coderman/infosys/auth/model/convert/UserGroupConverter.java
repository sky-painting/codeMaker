package com.coderman.infosys.auth.model.convert;

import java.util.List;
import com.coderman.infosys.auth.domain.bo.UserGroupBO;
import com.coderman.infosys.auth.adapter.vo.UserGroupVO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:UserGroupConvertervobo接口
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@Mapper
public interface UserGroupConverter{
	UserGroupConverter INSTANCE = Mappers.getMapper(UserGroupConverter.class);

	/**
	 *
	 * @Description:
	 * @return List<UserGroupVO>
	 */
	 List<UserGroupVO> BOs2VOs(List<UserGroupBO> userGroupBOList);
	/**
	 *
	 * @Description:
	 * @return UserGroupVO
	 */
	 UserGroupVO bo2VO(UserGroupBO userGroupBO);
	/**
	 *
	 * @Description:
	 * @return UserGroupBO
	 */
	 UserGroupBO vo2bo(UserGroupVO userGroupVO);
	/**
	 *
	 * @Description:
	 * @return List<UserGroupBO>
	 */
	 List<UserGroupBO> VOs2BOs(List<UserGroupVO> vOList);
}