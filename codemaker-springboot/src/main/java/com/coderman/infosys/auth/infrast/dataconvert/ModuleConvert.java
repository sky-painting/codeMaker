package com.coderman.infosys.auth.infrast.dataconvert;

import com.coderman.infosys.auth.infrast.dao.dataobject.ModuleDO;
import java.util.List;
import com.coderman.infosys.auth.domain.bo.ModuleBO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:菜单/模块管理接口
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@Mapper
public interface ModuleConvert{
	ModuleConvert INSTANCE = Mappers.getMapper(ModuleConvert.class);

	/**
	 *
	 * @Description:
	 * @return ModuleBO
	 */
	 ModuleBO do2bo(ModuleDO moduleDO);
	/**
	 *
	 * @Description:
	 * @return List<ModuleBO>
	 */
	 List<ModuleBO> doList2boList(List<ModuleDO> moduleDOList);
	/**
	 *
	 * @Description:
	 * @return ModuleDO
	 */
	 ModuleDO bo2do(ModuleBO moduleBO);
	/**
	 *
	 * @Description:
	 * @return List<ModuleDO>
	 */
	 List<ModuleDO> boList2doList(List<ModuleBO> moduleBOList);
}