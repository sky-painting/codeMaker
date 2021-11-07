package com.coderman.infosys.auth.infrast.dataconvert;

import com.coderman.infosys.auth.domain.bo.MenuBO;
import java.util.List;
import com.coderman.infosys.auth.infrast.dao.dataobject.MenuDO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:按钮管理接口
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
	 * @return MenuBO
	 */
	 MenuBO do2bo(MenuDO menuDO);
	/**
	 *
	 * @Description:
	 * @return List<MenuBO>
	 */
	 List<MenuBO> doList2boList(List<MenuDO> menuDOList);
	/**
	 *
	 * @Description:
	 * @return MenuDO
	 */
	 MenuDO bo2do(MenuBO menuBO);
	/**
	 *
	 * @Description:
	 * @return List<MenuDO>
	 */
	 List<MenuDO> boList2doList(List<MenuBO> menuBOList);
}