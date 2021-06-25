package com.lightsnail.schoolmanager.mapper;

import java.util.List;
import com.lightsnail.schoolmanager.entity.StudentEntity;


/**
 * @Description:mapperDAO接口
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 16:08:54
 * @version v1.0
 */
public interface StudentMapper{

	/**
	 * 
	 * @Title: save 
	 * @Description:新增或修改 
	 * @author: 
	 * @param @param studentEntity
	 * @return int
	 * @throws
	 */
	public int insert(StudentEntity studentEntity);

	/**
	 * 
	 * @Title: delete 
	 * @Description: 通过id删除数据
	 * @author: 
	 * @param @param id
	 * @return int
	 * @throws
	 */
	public int deleteById(Long id);

	/**
	 * 
	 * @Title: getById 
	 * @Description: 通过id查询
	 * @author: 
	 * @param @param id
	 * @return ResultDataDto<StudentEntity>    返回类型
	 * @throws
	 */
	public StudentEntity getById(Long id);

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<StudentEntity    返回类型
	 * @throws
	 */
	public List<StudentEntity>  getAll();

	/**
	*
	* @Title: update
	* @Description:新增或修改
	* @author:
	* @param @param studentEntity
	* @return int
	* @throws
	*/
	public int update(StudentEntity studentEntity);
	
}