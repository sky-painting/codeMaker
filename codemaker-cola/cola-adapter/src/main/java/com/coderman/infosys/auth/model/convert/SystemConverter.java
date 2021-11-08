package com.coderman.infosys.auth.model.convert;

import java.util.List;
import com.coderman.infosys.auth.adapter.vo.CreateSystemRequestVO;
import com.coderman.infosys.auth.adapter.vo.SystemVO;
import com.coderman.infosys.auth.domain.bo.SystemBO;
import com.coderman.infosys.auth.adapter.vo.UpdateSystemRequestVO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:SystemConvertervobo接口
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@Mapper
public interface SystemConverter{
	SystemConverter INSTANCE = Mappers.getMapper(SystemConverter.class);

	/**
	 *
	 * @Description:
	 * @return List<SystemBO>
	 */
	 List<SystemBO> voList2BoList(List<UpdateSystemRequestVO> updateSystemRequestVOList);
	/**
	 *
	 * @Description:
	 * @return SystemBO
	 */
	 SystemBO vo2bo(CreateSystemRequestVO createSystemRequestVO);
	/**
	 *
	 * @Description:
	 * @return SystemVO
	 */
	 SystemVO bo2VO(SystemBO systemBO);
	/**
	 *
	 * @Description:
	 * @return List<SystemVO>
	 */
	 List<SystemVO> bolist2voList(List<SystemBO> systemBOList);
	/**
	 *
	 * @Description:
	 * @return List<SystemBO>
	 */
	 List<SystemBO> voList2boList(List<SystemVO> vOList);
	/**
	 *
	 * @Description:
	 * @return List<SystemBO>
	 */
	 List<SystemBO> volist2boList(List<CreateSystemRequestVO> createSystemRequestVOList);
	/**
	 *
	 * @Description:
	 * @return SystemBO
	 */
	 SystemBO vo2bo(SystemVO systemVO);
	/**
	 *
	 * @Description:
	 * @return SystemBO
	 */
	 SystemBO vo2bo(UpdateSystemRequestVO updateSystemRequestVO);
}