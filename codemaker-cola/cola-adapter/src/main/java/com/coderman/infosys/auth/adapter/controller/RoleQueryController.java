package com.coderman.infosys.auth.adapter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.PageVO;
import com.coderman.infosys.auth.adapter.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:控制层
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@RestController
public class RoleQueryController {
	
	protected Logger logger = LoggerFactory.getLogger(RoleQueryController.class);



	/**
	 *
	 * @Description 分页获取用户组信息
	 * @param pageVO
	 * @return ResultDataDto<PageVO<RoleVO>>
	 */
	@RequestMapping(value = "/role/pagelist")
	public ResultDataDto<PageVO<RoleVO>> getPageList(@RequestParam(value = "pageVO", required = true) PageVO pageVO){

		return null;
	}
}
