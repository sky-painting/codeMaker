package com.lightsnail.schoolmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lightsnail.schoolmanager.service.ClassTeacherService;
import com.lightsnail.schoolmanager.vo.ClassTeacherVO;

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
public class ClassTeacherController extends BaseController{
	
	protected Logger logger = LoggerFactory.getLogger(ClassTeacherController.class);

	@Autowired
	private ClassTeacherService classTeacherService;


	/**
     * @Description:新增
     * @version v1.0
     * @param classTeacherVO
     * @return ResultDto
     */
    @RequestMapping(value = "/classTeacher/add",method = RequestMethod.POST)
    public ResultDto add(@RequestBody ClassTeacherVO classTeacherVO){
        //todo impl code
		return new ResultDto();
    }

	/**
	 * @Description:修改
	 * @version v1.0
	 * @param classTeacherVO
	 * @return ResultDto
	 */
    @RequestMapping(value = "/classTeacher/update",method = RequestMethod.POST)
	public ResultDto update(@RequestBody ClassTeacherVO classTeacherVO){
        //todo impl code
		return new ResultDto();
	}

	/**
	 * @Description:根据id删除
	 * @version v1.0
	 * @param id
	 * @return ResultDto
	 */
	@PostMapping("/classTeacher/delete")
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
	@GetMapping("/classTeacher/get")
	public ResultDataDto getById(@RequestParam(name = "id") Long id){
		//todo impl code
		return new ResultDataDto();
	}

	/**
	 * @Description:分页获取记录
	 * @version v1.0
	 * @return ResultDataDto
	 */
	@GetMapping("/classTeacher/getpage")
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
	@PostMapping("/classTeacher/changestatus")
	public ResultDto changeStatus(@RequestParam(name = "id") Long id,@RequestParam(name = "status") int status){
		//todo impl code
		return new ResultDto();
	}

}
