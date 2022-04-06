package com.lightsnail.app.user.crm.core.controller;

import lombok.extern.slf4j.Slf4j;

import com.lightsnail.app.user.crm.core.service.AttractInvestmentService;
import com.lightsnail.app.user.crm.core.vo.AttractInvestmentVO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:招商服务注册表控制层
* @Author:shenshuai
* @CreateTime:2020-11-17 00:02:53
* @version v1.0
*/
@RestController
@Slf4j
public class AttractInvestmentController extends BaseController{

	@Autowired
	private AttractInvestmentService attractInvestmentService;


	/**
     * @Description:新增招商服务注册表
     * @version v1.0
     * @param attractInvestmentVo
     * @return ResultDto
     */
    @RequestMapping(value = "/attractInvestment/add",method = RequestMethod.POST)
    public ResultDto save(@RequestBody AttractInvestmentVO attractInvestmentVo){
		try {
			return attractInvestmentService.save(attractInvestmentVo);
		} catch (Exception e) {
			log.error("保存失败",e);
			return ResultDto.setErrorCodeMsg("保存失败");
		}

    }

	/**
	 * @Description:修改招商服务注册表
	 * @version v1.0
	 * @param attractInvestmentVo
	 * @return ResultDto
	 */
    @RequestMapping(value = "/attractInvestment/update",method = RequestMethod.POST)
    public ResultDto update(@RequestBody AttractInvestmentVO attractInvestmentVo){
		try {
			return attractInvestmentService.update(attractInvestmentVo);
		} catch (Exception e) {
			log.error("修改失败",e);
			return ResultDto.setErrorCodeMsg("修改失败");
		}
	}

	/**
	 * @Description:根据id删除招商服务注册表
	 * @version v1.0
	 * @param id
	 * @return ResultDto
	 */
	@PostMapping("/attractInvestment/delete")
	public ResultDto delete(@RequestParam(name = "id") Long id){
		return attractInvestmentService.delete(id);
	}

	/**
	 * @Description:根据ID获取招商服务注册表单条记录
	 * @version v1.0
	 * @param id
	 * @return ResultDataDto
	 */
	@GetMapping("/attractInvestment/get")
	public ResultDataDto getById(@RequestParam(name = "id") Long id){
		try {
			return attractInvestmentService.getById(id);
		} catch (Exception e) {
			log.error("获取数据失败",e);
			return ResultDataDto.setErrorCodeMsg("获取数据失败");
		}
	}

	/**
	 * @Description:分页获取招商服务注册表记录
	 * @version v1.0
	 * @return ResultDataDto
	 */
	@GetMapping("/attractInvestment/getpage")
	public ResultDataDto getPage(){
		//todo impl code
		return new ResultDataDto();
	}

	/**
	 * @Description:修改招商服务注册表状态
	 * @version v1.0
	 * @param id
     * @param status
	 * @return ResultDataDto
	 */
	@PostMapping("/attractInvestment/changestatus")
	public ResultDto changeStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status){
		//todo impl code
		return new ResultDto();
	}

}
