package com.coderman.infosys.auth.adapter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.adapter.vo.CreateSystemRequestVO;
import com.coderman.infosys.auth.adapter.vo.SystemVO;
import com.coderman.utils.response.PageVO;
import com.coderman.infosys.auth.adapter.vo.UpdateSystemRequestVO;
import com.coderman.infosys.auth.domain.bo.SystemBO;
import com.coderman.infosys.auth.domain.gataway.SystemModuleQueryGataWay;
import com.coderman.infosys.auth.model.convert.SystemConverter;
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
public class SystemController {
	
	protected Logger logger = LoggerFactory.getLogger(SystemController.class);

	@Autowired
	private SystemModuleQueryGataWay systemModuleQueryGataWay;
	@Autowired
	private SystemModuleRepository systemModuleRepository;


	/**
	 *
	 * @Description 分页获取系统信息
	 * @param systemVO
	 * @return ResultDataDto<PageVO<SystemVO>>
	 */
	@RequestMapping(value = "/system/pagelist")
	public ResultDataDto<PageVO<SystemVO>> getPageList(@RequestParam(value = "systemVO", required = true) SystemVO systemVO){
        List<SystemBO> systemBOList = systemModuleQueryGataWay.querySystemPage(null);
        List<SystemVO> systemVOList = SystemConverter.INSTANCE.bolist2voList(systemBOList);

		return null;
	}

	/**
	 *
	 * @Description 搜索获取系统信息
	 * @param search
	 * @return ResultDataDto<List<SystemVO>>
	 */
	@RequestMapping(value = "/system/search")
	public ResultDataDto<List<SystemVO>> getSearchList(@RequestParam(value = "search", required = true) String search){
        List<SystemBO> systemBOList = systemModuleQueryGataWay.queryByCondition(search);
        List<SystemVO> systemVOList = SystemConverter.INSTANCE.bolist2voList(systemBOList);

		return ResultDataDto.success(systemVOList);
	}

	/**
	 *
	 * @Description 新建系统信息
	 * @param systemVO
	 * @return ResultDataDto<SystemVO>
	 */
	@RequestMapping(value = "/system/save")
	public ResultDataDto<SystemVO> saveSystem(@RequestBody CreateSystemRequestVO systemVO){
        SystemBO systemBO = SystemConverter.INSTANCE.vo2bo(systemVO);
        List<SystemBO> systemBOList = systemModuleQueryGataWay.getSystemByName(systemBO.getSystemName());
        Long longVar = systemModuleRepository.saveSystem(systemBO);

		return null;
	}

	/**
	 *
	 * @Description 修改系统信息
	 * @param systemVO
	 * @return ResultDataDto<Boolean>
	 */
	@RequestMapping(value = "/system/update")
	public ResultDataDto<Boolean> updateSystem(@RequestBody UpdateSystemRequestVO systemVO){
        SystemBO systemBO = SystemConverter.INSTANCE.vo2bo(systemVO);
        Integer intVar = systemModuleRepository.updateSystem(systemBO);

		return null;
	}

	/**
	 *
	 * @Description 通过系统编号获取系统信息
	 * @param systemCode
	 * @return ResultDataDto<SystemVO>
	 */
	@RequestMapping(value = "/system/getbycode")
	public ResultDataDto<SystemVO> getByCode(@RequestParam(value = "systemCode", required = true) String systemCode){
        SystemBO systemBO = systemModuleQueryGataWay.queryBySystemCode(systemCode);
        SystemVO systemVO = SystemConverter.INSTANCE.bo2VO(systemBO);

		return ResultDataDto.success(systemVO);
	}
}
