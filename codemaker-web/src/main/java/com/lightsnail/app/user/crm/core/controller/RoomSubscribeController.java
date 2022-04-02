package com.lightsnail.app.user.crm.core.controller;

import lombok.extern.slf4j.Slf4j;

import com.lightsnail.app.user.crm.core.service.RoomSubscribeService;
import com.lightsnail.app.user.crm.core.vo.RoomSubscribeVO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:代理人-预约看房记录表控制层
* @Author:shenshuai
* @CreateTime:2020-11-17 00:02:53
* @version v1.0
*/
@RestController
@Slf4j
public class RoomSubscribeController extends BaseController{

	@Autowired
	private RoomSubscribeService roomSubscribeService;


	/**
     * @Description:新增代理人-预约看房记录表
     * @version v1.0
     * @param roomSubscribeVo
     * @return ResultDto
     */
    @RequestMapping(value = "/roomSubscribe/add",method = RequestMethod.POST)
    public ResultDto save(@RequestBody RoomSubscribeVO roomSubscribeVo){
		try {
			return roomSubscribeService.save(roomSubscribeVo);
		} catch (Exception e) {
			log.error("保存失败",e);
			return ResultDto.setErrorCodeMsg("保存失败");
		}

    }

	/**
	 * @Description:修改代理人-预约看房记录表
	 * @version v1.0
	 * @param roomSubscribeVo
	 * @return ResultDto
	 */
    @RequestMapping(value = "/roomSubscribe/update",method = RequestMethod.POST)
    public ResultDto update(@RequestBody RoomSubscribeVO roomSubscribeVo){
		try {
			return roomSubscribeService.update(roomSubscribeVo);
		} catch (Exception e) {
			log.error("修改失败",e);
			return ResultDto.setErrorCodeMsg("修改失败");
		}
	}

	/**
	 * @Description:根据id删除代理人-预约看房记录表
	 * @version v1.0
	 * @param id
	 * @return ResultDto
	 */
	@PostMapping("/roomSubscribe/delete")
	public ResultDto delete(@RequestParam(name = "id") Long id){
		return roomSubscribeService.delete(id);
	}

	/**
	 * @Description:根据ID获取代理人-预约看房记录表单条记录
	 * @version v1.0
	 * @param id
	 * @return ResultDataDto
	 */
	@GetMapping("/roomSubscribe/get")
	public ResultDataDto getById(@RequestParam(name = "id") Long id){
		try {
			return roomSubscribeService.getById(id);
		} catch (Exception e) {
			log.error("获取数据失败",e);
			return ResultDataDto.setErrorCodeMsg("获取数据失败");
		}
	}

	/**
	 * @Description:分页获取代理人-预约看房记录表记录
	 * @version v1.0
	 * @return ResultDataDto
	 */
	@GetMapping("/roomSubscribe/getpage")
	public ResultDataDto getPage(){
		//todo impl code
		return new ResultDataDto();
	}

	/**
	 * @Description:修改代理人-预约看房记录表状态
	 * @version v1.0
	 * @param id
     * @param status
	 * @return ResultDataDto
	 */
	@PostMapping("/roomSubscribe/changestatus")
	public ResultDto changeStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status){
		//todo impl code
		return new ResultDto();
	}

}
