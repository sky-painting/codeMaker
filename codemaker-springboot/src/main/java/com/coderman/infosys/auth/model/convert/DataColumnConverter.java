package com.coderman.infosys.auth.model.convert;

import com.coderman.infosys.auth.adapter.vo.DataColumnVO;
import java.util.List;
import com.coderman.infosys.auth.adapter.vo.DataColumnResponseVO;
import com.coderman.infosys.auth.domain.bo.DataColumnBO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:DataColumnConvertervobo接口
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@Mapper
public interface DataColumnConverter{
	DataColumnConverter INSTANCE = Mappers.getMapper(DataColumnConverter.class);

	/**
	 *
	 * @Description:
	 * @return DataColumnVO
	 */
	 DataColumnVO bo2VO(DataColumnBO dataColumnBO);
	/**
	 *
	 * @Description:
	 * @return List<DataColumnBO>
	 */
	 List<DataColumnBO> voList2BoList(List<DataColumnVO> vOList);
	/**
	 *
	 * @Description:
	 * @return List<DataColumnResponseVO>
	 */
	 List<DataColumnResponseVO> BOs2VOs(List<DataColumnBO> dataColumnBOList);
	/**
	 *
	 * @Description:
	 * @return DataColumnBO
	 */
	 DataColumnBO vo2bo(DataColumnVO dataColumnVO);
	/**
	 *
	 * @Description:
	 * @return List<DataColumnVO>
	 */
	 List<DataColumnVO> boList2voList(List<DataColumnBO> dataColumnBOList);
}