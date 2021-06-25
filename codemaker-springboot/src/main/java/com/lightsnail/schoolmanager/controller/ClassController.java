package com.lightsnail.schoolmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lightsnail.schoolmanager.service.ClassService;
import com.lightsnail.schoolmanager.vo.ClassVO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:控制层
* @Author:fanchunshuai
* @CreateTime:2021-06-25 16:08:54
* @version v1.0
*/
@RestController
public class ClassController extends BaseController{
	
	protected Logger logger = LoggerFactory.getLogger(ClassController.class);

	@Autowired
	private ClassService classService;


	/**
     * @Description:新增
     * @version v1.0
     * @param classVO
     * @return ResultDto
     */
    @RequestMapping(value = "/class/add",method = RequestMethod.POST)
    public ResultDto add(@RequestBody ClassVO classVO){
        //todo impl code
		return new ResultDto();
    }

	/**
	 * @Description:修改
	 * @version v1.0
	 * @param classVO
	 * @return ResultDto
	 */
    @RequestMapping(value = "/class/update",method = RequestMethod.POST)
	public ResultDto update(@RequestBody ClassVO classVO){
        //todo impl code
		return new ResultDto();
	}

	/**
	 * @Description:根据id删除
	 * @version v1.0
	 * @param id
	 * @return ResultDto
	 */
	@PostMapping("/class/delete")
	public ResultDto delete(@RequestParam(name = "id") Long id){
		//todo impl code
		return new ResultDto();
	}

	/**
	 * @Description:根据ID获取单条记录
	 * @version v1.0
	 * @param id
	 * @return ResultDataDto
	 */
	@GetMapping("/class/get")
	public ResultDataDto getById(@RequestParam(name = "id") Long id){
		//todo impl code
		return new ResultDataDto();
	}

	/**
	 * @Description:分页获取记录
	 * @version v1.0
	 * @return ResultDataDto
	 */
	@GetMapping("/class/getpage")
	public ResultDataDto getPage(){
		//todo impl code
		return new ResultDataDto();
	}

	/**
	 * @Description:修改状态
	 * @version v1.0
	 * @param id
     * @param status
	 * @return ResultDataDto
	 */
	@PostMapping("/class/changestatus")
	public ResultDto changeStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status){
		//todo impl code
		return new ResultDto();
	}

}
