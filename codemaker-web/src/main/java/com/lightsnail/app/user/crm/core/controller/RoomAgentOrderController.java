package com.lightsnail.app.user.crm.core.controller;

import lombok.extern.slf4j.Slf4j;

import com.lightsnail.app.user.crm.core.service.RoomAgentOrderService;
import com.lightsnail.app.user.crm.core.vo.RoomAgentOrderVO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:代理人-签单表控制层
* @Author:fanchunshuai
* @CreateTime:2020-11-17 00:02:54
* @version v1.0
*/
@RestController
@Slf4j
public class RoomAgentOrderController extends BaseController{

	@Autowired
	private RoomAgentOrderService roomAgentOrderService;


	/**
     * @Description:新增代理人-签单表
     * @version v1.0
     * @param roomAgentOrderVo
     * @return ResultDto
     */
    @RequestMapping(value = "/roomAgentOrder/add",method = RequestMethod.POST)
    public ResultDto save(@RequestBody RoomAgentOrderVO roomAgentOrderVo){
		try {
			return roomAgentOrderService.save(roomAgentOrderVo);
		} catch (Exception e) {
			log.error("保存失败",e);
			return ResultDto.setErrorCodeMsg("保存失败");
		}

    }

	/**
	 * @Description:修改代理人-签单表
	 * @version v1.0
	 * @param roomAgentOrderVo
	 * @return ResultDto
	 */
    @RequestMapping(value = "/roomAgentOrder/update",method = RequestMethod.POST)
    public ResultDto update(@RequestBody RoomAgentOrderVO roomAgentOrderVo){
		try {
			return roomAgentOrderService.update(roomAgentOrderVo);
		} catch (Exception e) {
			log.error("修改失败",e);
			return ResultDto.setErrorCodeMsg("修改失败");
		}
	}

	/**
	 * @Description:根据id删除代理人-签单表
	 * @version v1.0
	 * @param id
	 * @return ResultDto
	 */
	@PostMapping("/roomAgentOrder/delete")
	public ResultDto delete(@RequestParam(name = "id") Long id){
		return roomAgentOrderService.delete(id);
	}

	/**
	 * @Description:根据ID获取代理人-签单表单条记录
	 * @version v1.0
	 * @param id
	 * @return ResultDataDto
	 */
	@GetMapping("/roomAgentOrder/get")
	public ResultDataDto getById(@RequestParam(name = "id") Long id){
		try {
			return roomAgentOrderService.getById(id);
		} catch (Exception e) {
			log.error("获取数据失败",e);
			return ResultDataDto.setErrorCodeMsg("获取数据失败");
		}
	}

	/**
	 * @Description:分页获取代理人-签单表记录
	 * @version v1.0
	 * @return ResultDataDto
	 */
	@GetMapping("/roomAgentOrder/getpage")
	public ResultDataDto getPage(){
		//todo impl code
		return new ResultDataDto();
	}

	/**
	 * @Description:修改代理人-签单表状态
	 * @version v1.0
	 * @param id
     * @param status
	 * @return ResultDataDto
	 */
	@PostMapping("/roomAgentOrder/changestatus")
	public ResultDto changeStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status){
		//todo impl code
		return new ResultDto();
	}

}
