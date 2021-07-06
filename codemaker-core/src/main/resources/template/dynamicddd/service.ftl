package ${package};

import java.util.List;

import ${package}.core.model.bo.${table.humpClassName}BO;

/**
 * @Description:${table.tableComment}Service接口
 * @Author:${class.author}
 * @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
public interface ${table.humpClassName}Service{

	/**
	 *
	 * @Title: save
	 * @Description:新增
	 * @author:
	 * @param @param dto
	 * @param @param userId
	 * @return ${table.humpClassName}BO    返回类型
	 * @throws
	 */
	public ${table.humpClassName}BO save(${table.humpClassName}BO ${table.humpTableName}BO) throws Exception;

	/**
	 * 
	 * @Title: delete 
	 * @Description: 通过id删除数据
	 * @author: 
	 * @param @param id
	 * @return ResultDto    返回类型 
	 * @throws
	 */
	public Boolean delete(Long id);

	/**
	 * 
	 * @Title: getById 
	 * @Description: 通过id查询
	 * @author: 
	 * @param @param id
	 * @return ${table.humpClassName}BO
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
	* @return ${table.humpClassName}BO
	* @throws
	*/
	public ${table.humpClassName}BO update(${table.humpClassName}BO ${table.humpTableName}BO) throws Exception;

}