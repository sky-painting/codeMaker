package com.coderman.infosys.auth.adapter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.adapter.vo.UserGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:控制层
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@RestController
public class UserGroupController {
	
	protected Logger logger = LoggerFactory.getLogger(UserGroupController.class);



	/**
	 *
	 * @Description 创建用户组
	 * @param userGroupVO
	 * @return ResultDataDto<UserGroupVO>
	 */
	@RequestMapping(value = "/usergroup/creategroup")
	public ResultDataDto<UserGroupVO> createUserGroup(@RequestBody UserGroupVO userGroupVO){

		return null;
	}
}
