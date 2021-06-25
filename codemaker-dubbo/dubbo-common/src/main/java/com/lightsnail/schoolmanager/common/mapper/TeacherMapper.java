package com.lightsnail.schoolmanager.common.mapper;

import java.util.List;
import com.lightsnail.schoolmanager.common.dataobject.TeacherDO;


/**
 * @Description:mapperDAO接口
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 16:19:51
 * @version v1.0
 */
public interface TeacherMapper{

	/**
	 * 
	 * @Title: save 
	 * @Description:新增或修改 
	 * @author: 
	 * @param @param teacherDO
	 * @return int
	 * @throws
	 */
	public int insert(TeacherDO teacherDO);

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
	 * @return ResultDataDto<TeacherDO>    返回类型
	 * @throws
	 */
	public TeacherDO getById(Long id);

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<TeacherDO    返回类型
	 * @throws
	 */
	public List<TeacherDO>  getAll();

	/**
	*
	* @Title: update
	* @Description:新增或修改
	* @author:
	* @param @param teacherDO
	* @return int
	* @throws
	*/
	public int update(TeacherDO teacherDO);
	
}