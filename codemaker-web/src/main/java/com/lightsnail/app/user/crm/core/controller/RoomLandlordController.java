package com.lightsnail.app.user.crm.core.controller;

import lombok.extern.slf4j.Slf4j;

import com.lightsnail.app.user.crm.core.service.RoomLandlordService;
import com.lightsnail.app.user.crm.core.vo.RoomLandlordVO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:房东表控制层
* @Author:fanchunshuai
* @CreateTime:2020-11-17 00:02:53
* @version v1.0
*/
@RestController
@Slf4j
public class RoomLandlordController extends BaseController{

	@Autowired
	private RoomLandlordService roomLandlordService;


	/**
     * @Description:新增房东表
     * @version v1.0
     * @param roomLandlordVo
     * @return ResultDto
     */
    @RequestMapping(value = "/roomLandlord/add",method = RequestMethod.POST)
    public ResultDto save(@RequestBody RoomLandlordVO roomLandlordVo){
		try {
			return roomLandlordService.save(roomLandlordVo);
		} catch (Exception e) {
			log.error("保存失败",e);
			return ResultDto.setErrorCodeMsg("保存失败");
		}

    }

	/**
	 * @Description:修改房东表
	 * @version v1.0
	 * @param roomLandlordVo
	 * @return ResultDto
	 */
    @RequestMapping(value = "/roomLandlord/update",method = RequestMethod.POST)
    public ResultDto update(@RequestBody RoomLandlordVO roomLandlordVo){
		try {
			return roomLandlordService.update(roomLandlordVo);
		} catch (Exception e) {
			log.error("修改失败",e);
			return ResultDto.setErrorCodeMsg("修改失败");
		}
	}

	/**
	 * @Description:根据id删除房东表
	 * @version v1.0
	 * @param id
	 * @return ResultDto
	 */
	@PostMapping("/roomLandlord/delete")
	public ResultDto delete(@RequestParam(name = "id") Long id){
		return roomLandlordService.delete(id);
	}

	/**
	 * @Description:根据ID获取房东表单条记录
	 * @version v1.0
	 * @param id
	 * @return ResultDataDto
	 */
	@GetMapping("/roomLandlord/get")
	public ResultDataDto getById(@RequestParam(name = "id") Long id){
		try {
			return roomLandlordService.getById(id);
		} catch (Exception e) {
			log.error("获取数据失败",e);
			return ResultDataDto.setErrorCodeMsg("获取数据失败");
		}
	}

	/**
	 * @Description:分页获取房东表记录
	 * @version v1.0
	 * @return ResultDataDto
	 */
	@GetMapping("/roomLandlord/getpage")
	public ResultDataDto getPage(){
		//todo impl code
		return new ResultDataDto();
	}

	/**
	 * @Description:修改房东表状态
	 * @version v1.0
	 * @param id
     * @param status
	 * @return ResultDataDto
	 */
	@PostMapping("/roomLandlord/changestatus")
	public ResultDto changeStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status){
		//todo impl code
		return new ResultDto();
	}

}
