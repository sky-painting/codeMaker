package com.lightsnail.schoolmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lightsnail.schoolmanager.service.TeacherService;
import com.lightsnail.schoolmanager.vo.TeacherVO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* @Description:控制层
* @Author:fanchunshuai
* @CreateTime:2021-06-25 16:08:53
* @version v1.0
*/
@RestController
public class TeacherController extends BaseController{
	
	protected Logger logger = LoggerFactory.getLogger(TeacherController.class);

	@Autowired
	private TeacherService teacherService;


	/**
     * @Description:新增
     * @version v1.0
     * @param teacherVO
     * @return ResultDto
     */
    @RequestMapping(value = "/teacher/add",method = RequestMethod.POST)
    public ResultDto add(@RequestBody TeacherVO teacherVO){
        //todo impl code
		return new ResultDto();
    }

	/**
	 * @Description:修改
	 * @version v1.0
	 * @param teacherVO
	 * @return ResultDto
	 */
    @RequestMapping(value = "/teacher/update",method = RequestMethod.POST)
	public ResultDto update(@RequestBody TeacherVO teacherVO){
        //todo impl code
		return new ResultDto();
	}

	/**
	 * @Description:根据id删除
	 * @version v1.0
	 * @param id
	 * @return ResultDto
	 */
	@PostMapping("/teacher/delete")
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
	@GetMapping("/teacher/get")
	public ResultDataDto getById(@RequestParam(name = "id") Long id){
		//todo impl code
		return new ResultDataDto();
	}

	/**
	 * @Description:分页获取记录
	 * @version v1.0
	 * @return ResultDataDto
	 */
	@GetMapping("/teacher/getpage")
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
	@PostMapping("/teacher/changestatus")
	public ResultDto changeStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status){
		//todo impl code
		return new ResultDto();
	}

}
