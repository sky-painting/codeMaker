package com.coderman.infosys.auth.adapter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.adapter.vo.SystemAuthorityVO;
import com.coderman.infosys.auth.adapter.vo.AuthorityVO;
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
public class SystemAuthorityController {
	
	protected Logger logger = LoggerFactory.getLogger(SystemAuthorityController.class);



	/**
	 *
	 * @Description 获取权限信息
	 * @param authCode
	 * @return ResultDataDto<SystemAuthorityVO>
	 */
	@RequestMapping(value = "/systemauthority/getbycode")
	public ResultDataDto<SystemAuthorityVO> getByCode(@RequestParam(value = "authCode", required = true) String authCode){

		return null;
	}

	/**
	 *
	 * @Description 分页获取权限信息
	 * @return ResultDataDto<PageVO<SystemAuthorityVO>>
	 */
	@RequestMapping(value = "/systemauthority/pagelist")
	public ResultDataDto<PageVO<SystemAuthorityVO>> getPageList(){

		return null;
	}
}
