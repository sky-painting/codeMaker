package ${package}.core.service;

import java.util.List;

import ${package}.core.bo.${table.humpClassName}BO;

/**
 * @Description:${table.tableComment}Service接口
 * @Author:${author}
 * @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
public interface ${table.humpClassName}Service{

	/**
	 * @Description:新增
	 * @param bo
	 * @return ResultDto    返回类型
	 * @throws
	 */
	public ${table.humpClassName}BO save(${table.humpClassName}BO bo) throws Exception;

	/**
	 * @Description: 通过id删除数据
	 * @param id
	 * @return ResultDto    返回类型 
	 * @throws
	 */
	public Boolean delete(Long id);

	/**
	 * @Description: 通过id查询
	 * @param id
	 * @return ResultDataDto<${table.humpClassName}BO>
	 * @throws
	 */
	public ${table.humpClassName}BO getById(Long id) throws Exception;

	/**
	 * @Description:查询所有数据
	 * @return List<${table.humpClassName}BO>
	 * @throws
	 */
	public List<${table.humpClassName}BO> getAll() throws Exception;

	/**
	 * @Description:修改
	 * @param  ${table.humpTableName}BO
	 * @return boolean
	 * @throws
	 */
	public Boolean update(${table.humpClassName}BO ${table.humpTableName}BO) throws Exception;


	/**
	 * @Description:分页查询数据
	 * @return List<${table.humpClassName}BO>
	 * @throws
	 */
	public List<${table.humpClassName}BO> getPageList() throws Exception;

}