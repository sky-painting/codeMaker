package com.coderman.infosys.auth.adapter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.infosys.auth.adapter.vo.RoleGroupVO;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:控制层
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@RestController
public class RoleGroupController {
	
	protected Logger logger = LoggerFactory.getLogger(RoleGroupController.class);



	/**
	 *
	 * @Description 分页获取用户组信息
	 * @param pageVO
	 * @return ResultDataDto<PageVO<RoleGroupVO>>
	 */
	@RequestMapping(value = "/role/pagelist")
	public ResultDataDto<PageVO<RoleGroupVO>> getPageList(@RequestParam(value = "pageVO", required = true) PageVO pageVO){

		return null;
	}
}
