package ${package}.api.facade;

import java.util.List;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import ${package}.api.dto.${table.humpClassName}DTO;

/**
 * @Description:${table.tableComment}Facade接口
 * @Author:${author}
 * @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
public interface ${table.humpClassName}Facade{

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
	public ResultDto save(${table.humpClassName}DTO dto) throws Exception;

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
	 * @return ResultDataDto<${table.humpClassName}DTO>
	 * @throws
	 */
	public ResultDataDto<${table.humpClassName}DTO> getById(Long id) throws Exception;

	/**
	 * 
	 * @Title: getAll 
	 * @Description:查询所有数据 
	 * @author: 
	 * @return ResultDataDto<List<${table.humpClassName}DTO>>
	 * @throws
	 */
	public ResultDataDto<List<${table.humpClassName}DTO>> getAll() throws Exception;

	/**
	*
	* @Title: update
	* @Description:修改
	* @author:
	* @param @param ${table.humpTableName}DTO
	* @return int
	* @throws
	*/
	public ResultDto update(${table.humpClassName}DTO ${table.humpTableName}DTO) throws Exception;

}