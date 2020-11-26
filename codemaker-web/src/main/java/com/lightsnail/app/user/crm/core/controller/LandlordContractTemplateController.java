package com.lightsnail.app.user.crm.core.controller;

import lombok.extern.slf4j.Slf4j;

import com.lightsnail.app.user.crm.core.service.LandlordContractTemplateService;
import com.lightsnail.app.user.crm.core.vo.LandlordContractTemplateVO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:房东合同模板表控制层
* @Author:fanchunshuai
* @CreateTime:2020-11-17 00:02:53
* @version v1.0
*/
@RestController
@Slf4j
public class LandlordContractTemplateController extends BaseController{

	@Autowired
	private LandlordContractTemplateService landlordContractTemplateService;


	/**
     * @Description:新增房东合同模板表
     * @version v1.0
     * @param landlordContractTemplateVo
     * @return ResultDto
     */
    @RequestMapping(value = "/landlordContractTemplate/add",method = RequestMethod.POST)
    public ResultDto save(@RequestBody LandlordContractTemplateVO landlordContractTemplateVo){
		try {
			return landlordContractTemplateService.save(landlordContractTemplateVo);
		} catch (Exception e) {
			log.error("保存失败",e);
			return ResultDto.setErrorCodeMsg("保存失败");
		}

    }

	/**
	 * @Description:修改房东合同模板表
	 * @version v1.0
	 * @param landlordContractTemplateVo
	 * @return ResultDto
	 */
    @RequestMapping(value = "/landlordContractTemplate/update",method = RequestMethod.POST)
    public ResultDto update(@RequestBody LandlordContractTemplateVO landlordContractTemplateVo){
		try {
			return landlordContractTemplateService.update(landlordContractTemplateVo);
		} catch (Exception e) {
			log.error("修改失败",e);
			return ResultDto.setErrorCodeMsg("修改失败");
		}
	}

	/**
	 * @Description:根据id删除房东合同模板表
	 * @version v1.0
	 * @param id
	 * @return ResultDto
	 */
	@PostMapping("/landlordContractTemplate/delete")
	public ResultDto delete(@RequestParam(name = "id") Long id){
		return landlordContractTemplateService.delete(id);
	}

	/**
	 * @Description:根据ID获取房东合同模板表单条记录
	 * @version v1.0
	 * @param id
	 * @return ResultDataDto
	 */
	@GetMapping("/landlordContractTemplate/get")
	public ResultDataDto getById(@RequestParam(name = "id") Long id){
		try {
			return landlordContractTemplateService.getById(id);
		} catch (Exception e) {
			log.error("获取数据失败",e);
			return ResultDataDto.setErrorCodeMsg("获取数据失败");
		}
	}

	/**
	 * @Description:分页获取房东合同模板表记录
	 * @version v1.0
	 * @return ResultDataDto
	 */
	@GetMapping("/landlordContractTemplate/getpage")
	public ResultDataDto getPage(){
		//todo impl code
		return new ResultDataDto();
	}

	/**
	 * @Description:修改房东合同模板表状态
	 * @version v1.0
	 * @param id
     * @param status
	 * @return ResultDataDto
	 */
	@PostMapping("/landlordContractTemplate/changestatus")
	public ResultDto changeStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status){
		//todo impl code
		return new ResultDto();
	}

}
