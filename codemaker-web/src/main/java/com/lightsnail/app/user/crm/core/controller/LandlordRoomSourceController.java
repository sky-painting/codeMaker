package com.lightsnail.app.user.crm.core.controller;

import lombok.extern.slf4j.Slf4j;

import com.lightsnail.app.user.crm.core.service.LandlordRoomSourceService;
import com.lightsnail.app.user.crm.core.vo.LandlordRoomSourceVO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:房东-房源表控制层
* @Author:shenshuai
* @CreateTime:2020-11-17 00:02:53
* @version v1.0
*/
@RestController
@Slf4j
public class LandlordRoomSourceController extends BaseController{

	@Autowired
	private LandlordRoomSourceService landlordRoomSourceService;


	/**
     * @Description:新增房东-房源表
     * @version v1.0
     * @param landlordRoomSourceVo
     * @return ResultDto
     */
    @RequestMapping(value = "/landlordRoomSource/add",method = RequestMethod.POST)
    public ResultDto save(@RequestBody LandlordRoomSourceVO landlordRoomSourceVo){
		try {
			return landlordRoomSourceService.save(landlordRoomSourceVo);
		} catch (Exception e) {
			log.error("保存失败",e);
			return ResultDto.setErrorCodeMsg("保存失败");
		}

    }

	/**
	 * @Description:修改房东-房源表
	 * @version v1.0
	 * @param landlordRoomSourceVo
	 * @return ResultDto
	 */
    @RequestMapping(value = "/landlordRoomSource/update",method = RequestMethod.POST)
    public ResultDto update(@RequestBody LandlordRoomSourceVO landlordRoomSourceVo){
		try {
			return landlordRoomSourceService.update(landlordRoomSourceVo);
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
	@PostMapping("/landlordRoomSource/delete")
	public ResultDto delete(@RequestParam(name = "id") Long id){
		return landlordRoomSourceService.delete(id);
	}

	/**
	 * @Description:根据ID获取房东-房源表单条记录
	 * @version v1.0
	 * @param id
	 * @return ResultDataDto
	 */
	@GetMapping("/landlordRoomSource/get")
	public ResultDataDto getById(@RequestParam(name = "id") Long id){
		try {
			return landlordRoomSourceService.getById(id);
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
	@GetMapping("/landlordRoomSource/getpage")
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
	@PostMapping("/landlordRoomSource/changestatus")
	public ResultDto changeStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status){
		//todo impl code
		return new ResultDto();
	}

}
