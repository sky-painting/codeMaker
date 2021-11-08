package com.coderman.infosys.auth.adapter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.adapter.vo.CreateModuleRequestVO;
import com.coderman.infosys.auth.adapter.vo.ModuleVO;
import com.coderman.utils.response.PageVO;
import com.coderman.infosys.auth.api.dto.CreateModuleRequestDTO;
import com.coderman.infosys.auth.adapter.vo.UpdateModuleRequestVO;
import com.coderman.infosys.auth.domain.bo.ModuleBO;
import com.coderman.infosys.auth.model.convert.ModuleConverter;
import com.coderman.infosys.auth.domain.gataway.SystemModuleQueryGataWay;
import com.coderman.infosys.auth.domain.gataway.SystemModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:控制层
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@RestController
public class ModuleController {
	
	protected Logger logger = LoggerFactory.getLogger(ModuleController.class);

	@Autowired
	private SystemModuleQueryGataWay systemModuleQueryGataWay;
	@Autowired
	private SystemModuleRepository systemModuleRepository;


	/**
	 *
	 * @Description 创建菜单信息
	 * @param createRequest
	 * @return ResultDataDto<List<ModuleVO>>
	 */
	@RequestMapping(value = "/module/createmodule")
	public ResultDataDto<List<ModuleVO>> createModule(@RequestBody CreateModuleRequestVO createRequest){
        ModuleBO moduleBO = ModuleConverter.INSTANCE.vo2bo(createRequest);
        ModuleBO moduleBO = systemModuleQueryGataWay.getModuleByCode(moduleBO.getModuleCode());
        Long longVar = systemModuleRepository.saveModule(moduleBO);

		return null;
	}

	/**
	 *
	 * @Description 创建菜单信息
	 * @param updateRequest
	 * @return ResultDataDto<List<ModuleVO>>
	 */
	@RequestMapping(value = "/module/updatemodule")
	public ResultDataDto<List<ModuleVO>> updateModule(@RequestBody UpdateModuleRequestVO updateRequest){
        ModuleBO moduleBO = ModuleConverter.INSTANCE.vo2bo(updateRequest);
        Integer intVar = systemModuleRepository.updateModule(moduleBO);

		return null;
	}

	/**
	 *
	 * @Description 获取系统菜单列表信息
	 * @param systemCode
	 * @return ResultDataDto<List<ModuleVO>>
	 */
	@RequestMapping(value = "/module/getbysystemcode")
	public ResultDataDto<List<ModuleVO>> getBySystemCode(@RequestParam(value = "systemCode", required = true) String systemCode){

		return null;
	}

	/**
	 *
	 * @Description 获取菜单信息
	 * @param moduleCode
	 * @return ResultDataDto<ModuleVO>
	 */
	@RequestMapping(value = "/module/getbycode")
	public ResultDataDto<ModuleVO> getByModuleCode(@RequestParam(value = "moduleCode", required = true) String moduleCode){
        ModuleBO moduleBO = systemModuleQueryGataWay.getModuleByCode(moduleCode);
        ModuleVO moduleVO = ModuleConverter.INSTANCE.bo2VO(moduleBO);

		return ResultDataDto.success(moduleVO);
	}

	/**
	 *
	 * @Description 分页获取菜单信息
	 * @return ResultDataDto<PageVO<ModuleVO>>
	 */
	@RequestMapping(value = "/module/pagelist")
	public ResultDataDto<PageVO<ModuleVO>> getPageList(){

		return null;
	}

	/**
	 *
	 * @Description 导入菜单信息
	 * @param createRequest
	 * @return ResultDataDto<Boolean>
	 */
	@RequestMapping(value = "/module/import")
	public ResultDataDto<Boolean> importModuleList(@RequestBody List<CreateModuleRequestDTO> createRequest){

		return null;
	}

	/**
	 *
	 * @Description 导出菜单信息
	 * @param systemCode
	 * @return ResultDataDto<Boolean>
	 */
	@RequestMapping(value = "/module/export")
	public ResultDataDto<Boolean> exportModuleList(@RequestParam(value = "systemCode", required = true) String systemCode){

		return null;
	}
}
