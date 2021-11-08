package com.coderman.infosys.auth.adapter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.adapter.vo.CreateRoleRequestVO;
import com.coderman.infosys.auth.adapter.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:控制层
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@RestController
public class RoleController {
	
	protected Logger logger = LoggerFactory.getLogger(RoleController.class);



	/**
	 *
	 * @Description 创建角色
	 * @param createRoleRequestVO
	 * @return ResultDataDto<RoleVO>
	 */
	@RequestMapping(value = "/role/createrule")
	public ResultDataDto<RoleVO> createRole(@RequestBody CreateRoleRequestVO createRoleRequestVO){

		return null;
	}

	/**
	 *
	 * @Description 根据编码获取角色详情
	 * @param roleCode
	 * @return ResultDataDto<RoleVO>
	 */
	@RequestMapping(value = "/role/getbyrolecode")
	public ResultDataDto<RoleVO> getByRoleCode(@RequestParam(value = "roleCode", required = true) String roleCode){

		return null;
	}

	/**
	 *
	 * @Description 禁用角色
	 * @param roleCode
	 * @return ResultDataDto<Boolean>
	 */
	@RequestMapping(value = "/role/disable")
	public ResultDataDto<Boolean> disable(String roleCode){

		return null;
	}

	/**
	 *
	 * @Description 启用角色
	 * @param roleCode
	 * @return ResultDataDto<Boolean>
	 */
	@RequestMapping(value = "/role/enable")
	public ResultDataDto<Boolean> enable(String roleCode){

		return null;
	}
}
