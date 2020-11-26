package com.lightsnail.app.user.crm.core.controller;

import lombok.extern.slf4j.Slf4j;

import com.lightsnail.app.user.crm.core.service.AccountService;
import com.lightsnail.app.user.crm.core.vo.AccountVO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:合作用户表控制层
* @Author:fanchunshuai
* @CreateTime:2020-11-17 00:02:53
* @version v1.0
*/
@RestController
@Slf4j
public class AccountController extends BaseController{

	@Autowired
	private AccountService accountService;


	/**
     * @Description:新增合作用户表
     * @version v1.0
     * @param accountVo
     * @return ResultDto
     */
    @RequestMapping(value = "/account/add",method = RequestMethod.POST)
    public ResultDto save(@RequestBody AccountVO accountVo){
		try {
			return accountService.save(accountVo);
		} catch (Exception e) {
			log.error("保存失败",e);
			return ResultDto.setErrorCodeMsg("保存失败");
		}

    }

	/**
	 * @Description:修改合作用户表
	 * @version v1.0
	 * @param accountVo
	 * @return ResultDto
	 */
    @RequestMapping(value = "/account/update",method = RequestMethod.POST)
    public ResultDto update(@RequestBody AccountVO accountVo){
		try {
			return accountService.update(accountVo);
		} catch (Exception e) {
			log.error("修改失败",e);
			return ResultDto.setErrorCodeMsg("修改失败");
		}
	}

	/**
	 * @Description:根据id删除合作用户表
	 * @version v1.0
	 * @param id
	 * @return ResultDto
	 */
	@PostMapping("/account/delete")
	public ResultDto delete(@RequestParam(name = "id") Long id){
		return accountService.delete(id);
	}

	/**
	 * @Description:根据ID获取合作用户表单条记录
	 * @version v1.0
	 * @param id
	 * @return ResultDataDto
	 */
	@GetMapping("/account/get")
	public ResultDataDto getById(@RequestParam(name = "id") Long id){
		try {
			return accountService.getById(id);
		} catch (Exception e) {
			log.error("获取数据失败",e);
			return ResultDataDto.setErrorCodeMsg("获取数据失败");
		}
	}

	/**
	 * @Description:分页获取合作用户表记录
	 * @version v1.0
	 * @return ResultDataDto
	 */
	@GetMapping("/account/getpage")
	public ResultDataDto getPage(){
		//todo impl code
		return new ResultDataDto();
	}

	/**
	 * @Description:修改合作用户表状态
	 * @version v1.0
	 * @param id
     * @param status
	 * @return ResultDataDto
	 */
	@PostMapping("/account/changestatus")
	public ResultDto changeStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status){
		//todo impl code
		return new ResultDto();
	}

}
