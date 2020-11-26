package com.lightsnail.app.user.crm.core.controller;

import lombok.extern.slf4j.Slf4j;

import com.lightsnail.app.user.crm.core.service.RealRoomSourceService;
import com.lightsnail.app.user.crm.core.vo.RealRoomSourceVO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:房东-房源表控制层
* @Author:fanchunshuai
* @CreateTime:2020-11-17 00:02:53
* @version v1.0
*/
@RestController
@Slf4j
public class RealRoomSourceController extends BaseController{

	@Autowired
	private RealRoomSourceService realRoomSourceService;


	/**
     * @Description:新增房东-房源表
     * @version v1.0
     * @param realRoomSourceVo
     * @return ResultDto
     */
    @RequestMapping(value = "/realRoomSource/add",method = RequestMethod.POST)
    public ResultDto save(@RequestBody RealRoomSourceVO realRoomSourceVo){
		try {
			return realRoomSourceService.save(realRoomSourceVo);
		} catch (Exception e) {
			log.error("保存失败",e);
			return ResultDto.setErrorCodeMsg("保存失败");
		}

    }

	/**
	 * @Description:修改房东-房源表
	 * @version v1.0
	 * @param realRoomSourceVo
	 * @return ResultDto
	 */
    @RequestMapping(value = "/realRoomSource/update",method = RequestMethod.POST)
    public ResultDto update(@RequestBody RealRoomSourceVO realRoomSourceVo){
		try {
			return realRoomSourceService.update(realRoomSourceVo);
		} catch (Exception e) {
			log.error("修改失败",e);
			return ResultDto.setErrorCodeMsg("修改失败");
		}
	}

	/**
	 * @Description:根据id删除房东-房源表
	 * @version v1.0
	 * @param id
	 * @return ResultDto
	 */
	@PostMapping("/realRoomSource/delete")
	public ResultDto delete(@RequestParam(name = "id") Long id){
		return realRoomSourceService.delete(id);
	}

	/**
	 * @Description:根据ID获取房东-房源表单条记录
	 * @version v1.0
	 * @param id
	 * @return ResultDataDto
	 */
	@GetMapping("/realRoomSource/get")
	public ResultDataDto getById(@RequestParam(name = "id") Long id){
		try {
			return realRoomSourceService.getById(id);
		} catch (Exception e) {
			log.error("获取数据失败",e);
			return ResultDataDto.setErrorCodeMsg("获取数据失败");
		}
	}

	/**
	 * @Description:分页获取房东-房源表记录
	 * @version v1.0
	 * @return ResultDataDto
	 */
	@GetMapping("/realRoomSource/getpage")
	public ResultDataDto getPage(){
		//todo impl code
		return new ResultDataDto();
	}

	/**
	 * @Description:修改房东-房源表状态
	 * @version v1.0
	 * @param id
     * @param status
	 * @return ResultDataDto
	 */
	@PostMapping("/realRoomSource/changestatus")
	public ResultDto changeStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status){
		//todo impl code
		return new ResultDto();
	}

}
