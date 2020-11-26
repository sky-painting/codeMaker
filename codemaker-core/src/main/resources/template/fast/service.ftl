package ${package}.service;

import java.util.List;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import ${package}.vo.${table.humpClassName}VO;

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
	 * @param @param dto
	 * @param @param userId
	 * @return ResultDto    返回类型
	 * @throws
	 */
	public ResultDto save(${table.humpClassName}VO ${table.humpTableName}Vo) throws Exception;

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
	 * @return ResultDataDto<${table.humpClassName}VO>
	 * @throws
	 */
	public ResultDataDto<${table.humpClassName}VO> getById(Long id) throws Exception;

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return ResultDataDto<List<${table.humpClassName}VO>>
	 * @throws
	 */
	public ResultDataDto<List<${table.humpClassName}VO>> getAll() throws Exception;

	/**
	*
	* @Title: update
	* @Description:修改
	* @author:
	* @param @param ${table.humpTableName}Entity
	* @return int
	* @throws
	*/
	public ResultDto update(${table.humpClassName}VO ${table.humpTableName}Vo) throws Exception;

}