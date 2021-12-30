package ${package}.mapper;

import java.util.List;
import ${package}.entity.${table.humpClassName}Entity;


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
	 * @param @param ${table.humpTableName}Entity
	 * @return int
	 * @throws
	 */
	public int insert(${table.humpClassName}Entity ${table.humpTableName}Entity);

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
	 * @return ResultDataDto<${table.humpClassName}Entity>    返回类型
	 * @throws
	 */
	public ${table.humpClassName}Entity getById(Long id);

	/**
	 *
	 * @Title: getAll
	 * @Description:查询所有数据
	 * @author:
	 * @return List<${table.humpClassName}Entity    返回类型
	 * @throws
	 */
	public List<${table.humpClassName}Entity>  getAll();

	/**
	*
	* @Title: update
	* @Description:新增或修改
	* @author:
	* @param @param ${table.humpTableName}Entity
	* @return int
	* @throws
	*/
	public int update(${table.humpClassName}Entity ${table.humpTableName}Entity);
	
}