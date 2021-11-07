package com.coderman.infosys.auth.infrast.dataconvert;

import com.coderman.infosys.auth.domain.bo.AuthorityBO;
import java.util.List;
import com.coderman.infosys.auth.infrast.dao.dataobject.DataAuthorityDO;
import com.coderman.infosys.auth.infrast.dao.dataobject.AuthorityDO;
import com.coderman.infosys.auth.domain.bo.DataAuthorityBO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:数据字段权限接口
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@Mapper
public interface DataAuthorityConvert{
	DataAuthorityConvert INSTANCE = Mappers.getMapper(DataAuthorityConvert.class);

	/**
	 *
	 * @Description:
	 * @return DataAuthorityBO
	 */
	 DataAuthorityBO do2bo(DataAuthorityDO dataAuthorityDO);
	/**
	 *
	 * @Description:
	 * @return List<DataAuthorityBO>
	 */
	 List<DataAuthorityBO> doList2boList(List<DataAuthorityDO> dataAuthorityDOList);
	/**
	 *
	 * @Description:
	 * @return DataAuthorityDO
	 */
	 DataAuthorityDO bo2do(DataAuthorityBO dataAuthorityBO);
	/**
	 *
	 * @Description:
	 * @return List<DataAuthorityDO>
	 */
	 List<DataAuthorityDO> boList2doList(List<DataAuthorityBO> dataAuthorityBOList);
}