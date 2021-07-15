package ${package}.dao.mapper;

import java.util.List;
import ${package}.dao.dataobject.${table.humpClassName}DO;


/**
 * @Description:${table.tableComment}mapperDAO接口
 * @Author:${author}
 * @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
public interface ${table.humpClassName}Mapper{

	/**
	 * 
	 * @Title: save 
	 * @Description:新增或修改 
	 * @author: 
	 * @param @param ${table.humpTableName}DO
	 * @return int
	 * @throws
	 */
	public int insert(${table.humpClassName}DO ${table.humpTableName}DO);

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
	 * @return ResultDataDto<${table.humpClassName}DO>    返回类型
	 * @throws
	 */
	public ${table.humpClassName}DO getById(Long id);

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return List<${table.humpClassName}DO    返回类型
	 * @throws
	 */
	public List<${table.humpClassName}DO>  getAll();

	/**
	*
	* @Title: update
	* @Description:新增或修改
	* @author:
	* @param @param ${table.humpTableName}DO
	* @return int
	* @throws
	*/
	public int update(${table.humpClassName}DO ${table.humpTableName}DO);
	
}