package ${package}.api.facade;

import java.util.List;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;
import com.coderman.utils.response.PageDTO;

import ${package}.api.dto.${table.humpClassName}DTO;

/**
 * @Description:${table.tableComment}Facade接口
 * @Author:${author}
 * @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
public interface ${table.humpClassName}Facade{

	/**
	 * @Description:新增
	 * @param dto
	 * @return ResultDto    返回类型
	 * @throws
	 */
	public ResultDto save(${table.humpClassName}DTO dto) throws Exception;

	/**
	 * @Description: 通过id删除数据
	 * @param id
	 * @return ResultDto    返回类型 
	 * @throws
	 */
	public ResultDto delete(Long id);

	/**
	 * @Description: 通过id查询
	 * @param @param id
	 * @return ResultDataDto<${table.humpClassName}DTO>
	 * @throws
	 */
	public ResultDataDto<${table.humpClassName}DTO> getById(Long id) throws Exception;

	/**
	 * @Description:查询所有数据
	 * @return ResultDataDto<List<${table.humpClassName}DTO>>
	 * @throws
	 */
	public ResultDataDto<List<${table.humpClassName}DTO>> getAll() throws Exception;

	/**
	 * @Description:修改
	 * @param @param ${table.humpTableName}DTO
	 * @return int
	 * @throws
	 */
	public ResultDto update(${table.humpClassName}DTO ${table.humpTableName}DTO) throws Exception;



	/**
	 * @Description:分页查询记录
	 * @return ResultDataDto<PageDTO<${table.humpClassName}DTO>>
	 * @throws
	 */
	 public ResultDataDto<PageDTO<${table.humpClassName}DTO>> getPageList(PageDTO pageDto) throws Exception;

}