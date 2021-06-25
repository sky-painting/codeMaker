package com.lightsnail.schoolmanager.service;

import java.util.List;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.vo.TeacherVO;

/**
 * @Description:Service接口
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 16:08:53
 * @version v1.0
 */
public interface TeacherService{

	/**
	 *
	 * @Title: save
	 * @Description:新增
	 * @author:
	 * @param @param dto
	 * @param @param userId
	 * @return ResultDto    返回类型
	 * @throws
	 */
	public ResultDto save(TeacherVO dto) throws Exception;

	/**
	 * 
	 * @Title: delete 
	 * @Description: 通过id删除数据
	 * @author: 
	 * @param @param id
	 * @return ResultDto    返回类型 
	 * @throws
	 */
	public ResultDto delete(Long id);

	/**
	 * 
	 * @Title: getById 
	 * @Description: 通过id查询
	 * @author: 
	 * @param @param id
	 * @return ResultDataDto<TeacherVO>
	 * @throws
	 */
	public ResultDataDto<TeacherVO> getById(Long id) throws Exception;

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return ResultDataDto<List<TeacherVO>>
	 * @throws
	 */
	public ResultDataDto<List<TeacherVO>> getAll() throws Exception;

	/**
	*
	* @Title: update
	* @Description:修改
	* @author:
	* @param @param teacherEntity
	* @return int
	* @throws
	*/
	public ResultDto update(TeacherVO teacherVO) throws Exception;

}