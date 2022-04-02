package ${package}.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import ${package}.mapper.${table.humpClassName}Mapper;
import ${package}.service.${table.humpClassName}Service;
import ${package}.vo.${table.humpClassName}VO;
import ${package}.entity.${table.humpClassName}Entity;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * @Description:${table.tableComment}Service接口实现类
 * @Author：${author}
 * @CreateTime：${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
@Service
public class ${table.humpClassName}ServiceImpl implements ${table.humpClassName}Service{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ${table.humpClassName}Mapper ${table.humpTableName}Mapper;
	
	@Override
	public ResultDto save(${table.humpClassName}VO vo) {
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
	public ResultDataDto<${table.humpClassName}VO> getById(Long id) {
		ResultDataDto<${table.humpClassName}VO> resultDataDto = new ResultDataDto<${table.humpClassName}VO>();
		logger.info("${table.humpTableName}Service.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<${table.humpClassName}VO>> getAll() {
		ResultDataDto<List<${table.humpClassName}VO>> resultDataDto = new ResultDataDto<List<${table.humpClassName}VO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(${table.humpClassName}VO vo) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}