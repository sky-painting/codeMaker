package com.coderman.infosys.auth.adapter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.PageVO;
import com.coderman.infosys.auth.adapter.vo.UserGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:控制层
* @Author:
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@RestController
public class UserGroupQueryController {
	
	protected Logger logger = LoggerFactory.getLogger(UserGroupQueryController.class);



	/**
	 *
	 * @Description 分页获取用户组信息
	 * @return ResultDataDto<PageVO<UserGroupVO>>
	 */
	@RequestMapping(value = "/usergroup/pagelist")
	public ResultDataDto<PageVO<UserGroupVO>> getPageList(){

		return null;
	}
}
