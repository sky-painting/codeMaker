package com.lightsnail.schoolmanager.api.facade;

import java.util.List;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.api.dto.ClassDTO;

/**
 * @Description:Facade接口
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 16:19:52
 * @version v1.0
 */
public interface ClassFacade{

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
	public ResultDto save(ClassDTO dto) throws Exception;

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
	 * @return ResultDataDto<ClassDTO>
	 * @throws
	 */
	public ResultDataDto<ClassDTO> getById(Long id) throws Exception;

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return ResultDataDto<List<ClassDTO>>
	 * @throws
	 */
	public ResultDataDto<List<ClassDTO>> getAll() throws Exception;

	/**
	*
	* @Title: update
	* @Description:修改
	* @author:
	* @param @param classDTO
	* @return int
	* @throws
	*/
	public ResultDto update(ClassDTO classDTO) throws Exception;

}