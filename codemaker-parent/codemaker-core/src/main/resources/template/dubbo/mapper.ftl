package ${package}.common.dao.mapper;

import java.util.List;
import ${package}.infrast.dao.dataobject.${table.humpClassName}DO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @Description:${table.tableComment}mapperDAO接口
 * @Author:${author}
 * @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
public interface ${table.humpClassName}Mapper{

	/**
	 * 
	 * @Description:新增或修改
	 * @param ${table.humpTableName}DO
	 * @return int
	 */
	public long insert(${table.humpClassName}DO ${table.humpTableName}DO);

	/**
	 * 
	 * @Description: 通过id删除数据
	 * @param id
	 * @return int
	 */
	public int deleteById(Long id);

	/**
	 * 
	 * @Description: 通过id查询
	 * @param  id
	 * @return ${table.humpClassName}DO
	 */
	public ${table.humpClassName}DO getById(Long id);

	/**
	 * @Description:查询所有数据
	 * @return List<${table.humpClassName}DO
	 */
	public List<${table.humpClassName}DO>  getAll();

	/**
	 *
	 * @Description:新增或修改
	 * @param ${table.humpTableName}DO
	 * @return int
	 */
	public int update(${table.humpClassName}DO ${table.humpTableName}DO);


	/**
	 * @Description:查询所有数据
	 * @return List<${table.humpClassName}DO
	 */
	public List<${table.humpClassName}DO>  getPageList();

	/**
	 * @Description:查询数量
	 * @return int
	 */
	 public int  getCount();
}