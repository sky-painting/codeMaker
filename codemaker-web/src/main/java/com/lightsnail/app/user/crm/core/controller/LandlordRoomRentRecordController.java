package com.lightsnail.app.user.crm.core.controller;

import lombok.extern.slf4j.Slf4j;

import com.lightsnail.app.user.crm.core.service.LandlordRoomRentRecordService;
import com.lightsnail.app.user.crm.core.vo.LandlordRoomRentRecordVO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:房源出租记录表控制层
* @Author:fanchunshuai
* @CreateTime:2020-11-17 00:02:54
* @version v1.0
*/
@RestController
@Slf4j
public class LandlordRoomRentRecordController extends BaseController{

	@Autowired
	private LandlordRoomRentRecordService landlordRoomRentRecordService;


	/**
     * @Description:新增房源出租记录表
     * @version v1.0
     * @param landlordRoomRentRecordVo
     * @return ResultDto
     */
    @RequestMapping(value = "/landlordRoomRentRecord/add",method = RequestMethod.POST)
    public ResultDto save(@RequestBody LandlordRoomRentRecordVO landlordRoomRentRecordVo){
		try {
			return landlordRoomRentRecordService.save(landlordRoomRentRecordVo);
		} catch (Exception e) {
			log.error("保存失败",e);
			return ResultDto.setErrorCodeMsg("保存失败");
		}

    }

	/**
	 * @Description:修改房源出租记录表
	 * @version v1.0
	 * @param landlordRoomRentRecordVo
	 * @return ResultDto
	 */
    @RequestMapping(value = "/landlordRoomRentRecord/update",method = RequestMethod.POST)
    public ResultDto update(@RequestBody LandlordRoomRentRecordVO landlordRoomRentRecordVo){
		try {
			return landlordRoomRentRecordService.update(landlordRoomRentRecordVo);
		} catch (Exception e) {
			log.error("修改失败",e);
			return ResultDto.setErrorCodeMsg("修改失败");
		}
	}

	/**
	 * @Description:根据id删除房源出租记录表
	 * @version v1.0
	 * @param id
	 * @return ResultDto
	 */
	@PostMapping("/landlordRoomRentRecord/delete")
	public ResultDto delete(@RequestParam(name = "id") Long id){
		return landlordRoomRentRecordService.delete(id);
	}

	/**
	 * @Description:根据ID获取房源出租记录表单条记录
	 * @version v1.0
	 * @param id
	 * @return ResultDataDto
	 */
	@GetMapping("/landlordRoomRentRecord/get")
	public ResultDataDto getById(@RequestParam(name = "id") Long id){
		try {
			return landlordRoomRentRecordService.getById(id);
		} catch (Exception e) {
			log.error("获取数据失败",e);
			return ResultDataDto.setErrorCodeMsg("获取数据失败");
		}
	}

	/**
	 * @Description:分页获取房源出租记录表记录
	 * @version v1.0
	 * @return ResultDataDto
	 */
	@GetMapping("/landlordRoomRentRecord/getpage")
	public ResultDataDto getPage(){
		//todo impl code
		return new ResultDataDto();
	}

	/**
	 * @Description:修改房源出租记录表状态
	 * @version v1.0
	 * @param id
     * @param status
	 * @return ResultDataDto
	 */
	@PostMapping("/landlordRoomRentRecord/changestatus")
	public ResultDto changeStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status){
		//todo impl code
		return new ResultDto();
	}

}
