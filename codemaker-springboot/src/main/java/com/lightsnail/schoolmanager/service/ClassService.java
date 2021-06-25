package com.lightsnail.schoolmanager.service;

import java.util.List;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.vo.ClassVO;

/**
 * @Description:Service接口
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 16:08:54
 * @version v1.0
 */
public interface ClassService{

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
	public ResultDto save(ClassVO dto) throws Exception;

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
	 * @return ResultDataDto<ClassVO>
	 * @throws
	 */
	public ResultDataDto<ClassVO> getById(Long id) throws Exception;

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return ResultDataDto<List<ClassVO>>
	 * @throws
	 */
	public ResultDataDto<List<ClassVO>> getAll() throws Exception;

	/**
	*
	* @Title: update
	* @Description:修改
	* @author:
	* @param @param classEntity
	* @return int
	* @throws
	*/
	public ResultDto update(ClassVO classVO) throws Exception;

}