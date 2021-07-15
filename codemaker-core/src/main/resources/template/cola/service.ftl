package ${package}.service;

import java.util.List;

import ${package}.model.bo.${table.humpClassName}BO;

/**
 * @Description:${table.tableComment}Service接口
 * @Author:${author}
 * @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
public interface ${table.humpClassName}Service{

	/**
	 *
	 * @Title: save
	 * @Description:新增
	 * @author:
	 * @param @param bo
	 * @param @param userId
	 * @return ResultDto    返回类型
	 * @throws
	 */
	public ${table.humpClassName}BO save(${table.humpClassName}BO bo) throws Exception;

	/**
	 * 
	 * @Title: delete 
	 * @Description: 通过id删除数据
	 * @author: 
	 * @param @param id
	 * @return ResultDto    返回类型 
	 * @throws
	 */
	public boolean delete(Long id);

	/**
	 * 
	 * @Title: getById 
	 * @Description: 通过id查询
	 * @author: 
	 * @param @param id
	 * @return ResultDataDto<${table.humpClassName}BO>
	 * @throws
	 */
	public ${table.humpClassName}BO getById(Long id) throws Exception;

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<${table.humpClassName}BO>
	 * @throws
	 */
	public List<${table.humpClassName}BO> getAll() throws Exception;

	/**
	*
	* @Title: update
	* @Description:修改
	* @author:
	* @param @param ${table.humpTableName}BO
	* @return int
	* @throws
	*/
	public boolean update(${table.humpClassName}BO ${table.humpTableName}BO) throws Exception;

}