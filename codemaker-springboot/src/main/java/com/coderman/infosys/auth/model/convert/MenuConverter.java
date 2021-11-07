package com.coderman.infosys.auth.model.convert;

import com.coderman.infosys.auth.adapter.vo.MenuVO;
import com.coderman.infosys.auth.domain.bo.MenuBO;
import java.util.List;
import com.coderman.infosys.auth.adapter.vo.UpdateMenuRequestVO;
import com.coderman.infosys.auth.adapter.vo.CreateMenuRequestVO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* @Description:MenuConvertervobo接口
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@Mapper
public interface MenuConverter{
	MenuConverter INSTANCE = Mappers.getMapper(MenuConverter.class);

	/**
	 *
	 * @Description:
	 * @return MenuBO
	 */
	 MenuBO vo2bo(UpdateMenuRequestVO updateMenuRequestVO);
	/**
	 *
	 * @Description:
	 * @return List<MenuBO>
	 */
	 List<MenuBO> volist2boList(List<CreateMenuRequestVO> createMenuRequestVOList);
	/**
	 *
	 * @Description:
	 * @return List<MenuBO>
	 */
	 List<MenuBO> voList2boList(List<MenuVO> vOList);
	/**
	 *
	 * @Description:
	 * @return MenuVO
	 */
	 MenuVO bo2VO(MenuBO menuBO);
	/**
	 *
	 * @Description:
	 * @return List<MenuVO>
	 */
	 List<MenuVO> BOs2VOs(List<MenuBO> menuBOList);
	/**
	 *
	 * @Description:
	 * @return MenuBO
	 */
	 MenuBO vo2bo(CreateMenuRequestVO createMenuRequestVO);
	/**
	 *
	 * @Description:
	 * @return MenuBO
	 */
	 MenuBO vo2bo(MenuVO menuVO);
	/**
	 *
	 * @Description:
	 * @return List<MenuBO>
	 */
	 List<MenuBO> voList2boList(List<UpdateMenuRequestVO> updateMenuRequestVOList);
}