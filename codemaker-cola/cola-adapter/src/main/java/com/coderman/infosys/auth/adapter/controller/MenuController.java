package com.coderman.infosys.auth.adapter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.infosys.auth.adapter.vo.MenuVO;
import java.util.List;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.adapter.vo.UpdateMenuRequestVO;
import com.coderman.infosys.auth.adapter.vo.CreateMenuRequestVO;
import com.coderman.infosys.auth.domain.bo.MenuBO;
import com.coderman.infosys.auth.domain.gataway.SystemModuleRepository;
import com.coderman.infosys.auth.model.convert.MenuConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:控制层
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@RestController
public class MenuController {
	
	protected Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private SystemModuleRepository systemModuleRepository;


	/**
	 *
	 * @Description 修改按钮信息
	 * @param updateMenuRequestVO
	 * @return ResultDataDto<Boolean>
	 */
	@RequestMapping(value = "/menu/updatemenu")
	public ResultDataDto<Boolean> updateMenu(@RequestBody UpdateMenuRequestVO updateMenuRequestVO){
        MenuBO menuBO = MenuConverter.INSTANCE.vo2bo(updateMenuRequestVO);
        Integer intVar = systemModuleRepository.updateMenu(menuBO);

		return null;
	}

	/**
	 *
	 * @Description 创建按钮信息
	 * @param createMenuRequestVO
	 * @return ResultDataDto<MenuVO>
	 */
	@RequestMapping(value = "/menu/createmenu")
	public ResultDataDto<MenuVO> createMenu(@RequestBody CreateMenuRequestVO createMenuRequestVO){
        MenuBO menuBO = MenuConverter.INSTANCE.vo2bo(createMenuRequestVO);
        Long longVar = systemModuleRepository.saveMenu(menuBO);

		return null;
	}

	/**
	 *
	 * @Description 获取按钮详情信息
	 * @param menuCode
	 * @return ResultDataDto<MenuVO>
	 */
	@RequestMapping(value = "/menu/getbycode")
	public ResultDataDto<MenuVO> getByCode(@RequestParam(value = "menuCode", required = true) String menuCode){

		return null;
	}

	/**
	 *
	 * @Description 获取按钮列表信息
	 * @param moduleCode
	 * @return ResultDataDto<List<MenuVO>>
	 */
	@RequestMapping(value = "/menu/pagelist")
	public ResultDataDto<List<MenuVO>> getPageList(@RequestParam(value = "moduleCode", required = true) String moduleCode ){

		return null;
	}
}
