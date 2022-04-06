package com.lightsnail.app.user.crm.core.controller;

import lombok.extern.slf4j.Slf4j;

import com.lightsnail.app.user.crm.core.service.RoomPublishService;
import com.lightsnail.app.user.crm.core.vo.RoomPublishVO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:代理人-房源发布表控制层
* @Author:shenshuai
* @CreateTime:2020-11-17 00:02:53
* @version v1.0
*/
@RestController
@Slf4j
public class RoomPublishController extends BaseController{

	@Autowired
	private RoomPublishService roomPublishService;


	/**
     * @Description:新增代理人-房源发布表
     * @version v1.0
     * @param roomPublishVo
     * @return ResultDto
     */
    @RequestMapping(value = "/roomPublish/add",method = RequestMethod.POST)
    public ResultDto save(@RequestBody RoomPublishVO roomPublishVo){
		try {
			return roomPublishService.save(roomPublishVo);
		} catch (Exception e) {
			log.error("保存失败",e);
			return ResultDto.setErrorCodeMsg("保存失败");
		}

    }

	/**
	 * @Description:修改代理人-房源发布表
	 * @version v1.0
	 * @param roomPublishVo
	 * @return ResultDto
	 */
    @RequestMapping(value = "/roomPublish/update",method = RequestMethod.POST)
    public ResultDto update(@RequestBody RoomPublishVO roomPublishVo){
		try {
			return roomPublishService.update(roomPublishVo);
		} catch (Exception e) {
			log.error("修改失败",e);
			return ResultDto.setErrorCodeMsg("修改失败");
		}
	}

	/**
	 * @Description:根据id删除代理人-房源发布表
	 * @version v1.0
	 * @param id
	 * @return ResultDto
	 */
	@PostMapping("/roomPublish/delete")
	public ResultDto delete(@RequestParam(name = "id") Long id){
		return roomPublishService.delete(id);
	}

	/**
	 * @Description:根据ID获取代理人-房源发布表单条记录
	 * @version v1.0
	 * @param id
	 * @return ResultDataDto
	 */
	@GetMapping("/roomPublish/get")
	public ResultDataDto getById(@RequestParam(name = "id") Long id){
		try {
			return roomPublishService.getById(id);
		} catch (Exception e) {
			log.error("获取数据失败",e);
			return ResultDataDto.setErrorCodeMsg("获取数据失败");
		}
	}

	/**
	 * @Description:分页获取代理人-房源发布表记录
	 * @version v1.0
	 * @return ResultDataDto
	 */
	@GetMapping("/roomPublish/getpage")
	public ResultDataDto getPage(){
		//todo impl code
		return new ResultDataDto();
	}

	/**
	 * @Description:修改代理人-房源发布表状态
	 * @version v1.0
	 * @param id
     * @param status
	 * @return ResultDataDto
	 */
	@PostMapping("/roomPublish/changestatus")
	public ResultDto changeStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status){
		//todo impl code
		return new ResultDto();
	}

}
