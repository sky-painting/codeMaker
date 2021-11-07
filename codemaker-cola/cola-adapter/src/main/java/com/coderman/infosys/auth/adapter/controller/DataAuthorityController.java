package com.coderman.infosys.auth.adapter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.adapter.vo.DataAuthorityResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:控制层
* @Author:
* @CreateTime:2021-11-07 08:23:49
* @version v1.0
*/
@RestController
public class DataAuthorityController {
	
	protected Logger logger = LoggerFactory.getLogger(DataAuthorityController.class);



	/**
	 *
	 * @Description 获取权限信息
	 * @param authCode
	 * @return ResultDataDto<DataAuthorityResponseVO>
	 */
	@RequestMapping(value = "/dataauthority/getbycode")
	public ResultDataDto<DataAuthorityResponseVO> getByCode(@RequestParam(value = "authCode", required = true) String authCode){

		return null;
	}
}
