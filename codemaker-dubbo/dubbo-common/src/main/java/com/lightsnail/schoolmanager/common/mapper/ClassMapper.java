package com.lightsnail.schoolmanager.common.mapper;

import java.util.List;
import com.lightsnail.schoolmanager.common.dataobject.ClassDO;


/**
 * @Description:mapperDAO接口
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 16:19:52
 * @version v1.0
 */
public interface ClassMapper{

	/**
	 * 
	 * @Title: save 
	 * @Description:新增或修改 
	 * @author: 
	 * @param @param classDO
	 * @return int
	 * @throws
	 */
	public int insert(ClassDO classDO);

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
	 * @return ResultDataDto<ClassDO>    返回类型
	 * @throws
	 */
	public ClassDO getById(Long id);

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<ClassDO    返回类型
	 * @throws
	 */
	public List<ClassDO>  getAll();

	/**
	*
	* @Title: update
	* @Description:新增或修改
	* @author:
	* @param @param classDO
	* @return int
	* @throws
	*/
	public int update(ClassDO classDO);
	
}