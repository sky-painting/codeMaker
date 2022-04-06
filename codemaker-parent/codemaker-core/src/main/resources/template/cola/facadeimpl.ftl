package ${package}.app.facadeimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;
import com.coderman.utils.response.PageDTO;

import ${package}.client.api.facade.${table.humpClassName}Facade;
import ${package}.client.api.dto.${table.humpClassName}DTO;

import java.util.List;

/**
 * @Description:${table.tableComment}Facade接口实现类
 * @Author：${author}
 * @CreateTime：${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
@Service
public class ${table.humpClassName}FacadeImpl implements ${table.humpClassName}Facade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public ResultDto save(${table.humpClassName}DTO dto) {
		ResultDto resultDto = new ResultDto();
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		logger.info("${table.humpTableName}Service.delete,id="+id);
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDataDto<${table.humpClassName}DTO> getById(Long id) {
		ResultDataDto<${table.humpClassName}DTO> resultDataDto = new ResultDataDto<${table.humpClassName}DTO>();
		logger.info("${table.humpTableName}Service.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<${table.humpClassName}DTO>> getAll() {
		ResultDataDto<List<${table.humpClassName}DTO>> resultDataDto = new ResultDataDto<List<${table.humpClassName}DTO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(${table.humpClassName}DTO dto) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

	@Override
	public ResultDataDto<PageDTO<${table.humpClassName}DTO>> getPageList(PageDTO pageDto) {
		ResultDataDto<PageDTO<${table.humpClassName}DTO>> resultDataDto = new ResultDataDto<PageDTO<${table.humpClassName}DTO>>();
		//todo impl code
		return	resultDataDto;
	}

}