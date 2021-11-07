package com.coderman.infosys.auth.adapter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.adapter.vo.AuthorityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:控制层
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@RestController
public class AuthorityOperationController {
	
	protected Logger logger = LoggerFactory.getLogger(AuthorityOperationController.class);



	/**
	 *
	 * @Description 给角色授权
	 * @param authorityVO
	 * @return ResultDataDto<AuthorityVO>
	 */
	@RequestMapping(value = "/authority/savetorole")
	public ResultDataDto<AuthorityVO> saveAuthority(@RequestBody AuthorityVO authorityVO){

		return null;
	}
}
