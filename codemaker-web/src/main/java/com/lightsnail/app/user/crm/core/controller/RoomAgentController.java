package com.lightsnail.app.user.crm.core.controller;

import lombok.extern.slf4j.Slf4j;

import com.lightsnail.app.user.crm.core.service.RoomAgentService;
import com.lightsnail.app.user.crm.core.vo.RoomAgentVO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:代理人表控制层
* @Author:shenshuai
* @CreateTime:2020-11-17 00:02:53
* @version v1.0
*/
@RestController
@Slf4j
public class RoomAgentController extends BaseController{

	@Autowired
	private RoomAgentService roomAgentService;


	/**
     * @Description:新增代理人表
     * @version v1.0
     * @param roomAgentVo
     * @return ResultDto
     */
    @RequestMapping(value = "/roomAgent/add",method = RequestMethod.POST)
    public ResultDto save(@RequestBody RoomAgentVO roomAgentVo){
		try {
			return roomAgentService.save(roomAgentVo);
		} catch (Exception e) {
			log.error("保存失败",e);
			return ResultDto.setErrorCodeMsg("保存失败");
		}

    }

	/**
	 * @Description:修改代理人表
	 * @version v1.0
	 * @param roomAgentVo
	 * @return ResultDto
	 */
    @RequestMapping(value = "/roomAgent/update",method = RequestMethod.POST)
    public ResultDto update(@RequestBody RoomAgentVO roomAgentVo){
		try {
			return roomAgentService.update(roomAgentVo);
		} catch (Exception e) {
			log.error("修改失败",e);
			return ResultDto.setErrorCodeMsg("修改失败");
		}
	}

	/**
	 * @Description:根据id删除代理人表
	 * @version v1.0
	 * @param id
	 * @return ResultDto
	 */
	@PostMapping("/roomAgent/delete")
	public ResultDto delete(@RequestParam(name = "id") Long id){
		return roomAgentService.delete(id);
	}

	/**
	 * @Description:根据ID获取代理人表单条记录
	 * @version v1.0
	 * @param id
	 * @return ResultDataDto
	 */
	@GetMapping("/roomAgent/get")
	public ResultDataDto getById(@RequestParam(name = "id") Long id){
		try {
			return roomAgentService.getById(id);
		} catch (Exception e) {
			log.error("获取数据失败",e);
			return ResultDataDto.setErrorCodeMsg("获取数据失败");
		}
	}

	/**
	 * @Description:分页获取代理人表记录
	 * @version v1.0
	 * @return ResultDataDto
	 */
	@GetMapping("/roomAgent/getpage")
	public ResultDataDto getPage(){
		//todo impl code
		return new ResultDataDto();
	}

	/**
	 * @Description:修改代理人表状态
	 * @version v1.0
	 * @param id
     * @param status
	 * @return ResultDataDto
	 */
	@PostMapping("/roomAgent/changestatus")
	public ResultDto changeStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status){
		//todo impl code
		return new ResultDto();
	}

}
