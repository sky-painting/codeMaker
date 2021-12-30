package ${package}.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.coderman.utils.bean.CglibConvertService;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import ${package}.mapper.${table.humpClassName}Mapper;
import ${package}.service.${table.humpClassName}Service;
import ${package}.vo.${table.humpClassName}VO;
import ${package}.entity.${table.humpClassName}Entity;

import java.util.List;


/**
 * @Description:${table.tableComment}Service接口实现类
 * @Author：${author}
 * @CreateTime：${.now?string('yyyy-MM-dd HH:mm:ss')}
 * @version v1.0
 */
@Service
@Slf4j
public class ${table.humpClassName}ServiceImpl implements ${table.humpClassName}Service{


	@Resource
	private ${table.humpClassName}Mapper ${table.humpTableName}Mapper;

    @Autowired
    private CglibConvertService cglibConvertService;

	@Override
	public ResultDto save(${table.humpClassName}VO ${table.humpTableName}Vo)  throws Exception{
		ResultDto resultDto = new ResultDto();
        ${table.humpClassName}Entity ${table.humpTableName}Entity = cglibConvertService.copyPropertity(${table.humpClassName}Entity.class,${table.humpTableName}Vo);
        ${table.humpTableName}Mapper.insert(${table.humpTableName}Entity);
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		log.info("${table.humpTableName}Service.delete,id="+id);
       ${table.humpTableName}Mapper.deleteById(id);
		return resultDto;
	}
	
	@Override
	public ResultDataDto<${table.humpClassName}VO> getById(Long id)  throws Exception {
		ResultDataDto<${table.humpClassName}VO> resultDataDto = new ResultDataDto<${table.humpClassName}VO>();
		${table.humpClassName}Entity ${table.humpTableName}Entity = ${table.humpTableName}Mapper.getById(id);
		${table.humpClassName}VO ${table.humpTableName}Vo = cglibConvertService.copyPropertity(${table.humpClassName}VO.class,${table.humpTableName}Entity);
		resultDataDto.setData(${table.humpTableName}Vo);
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<${table.humpClassName}VO>> getAll()  throws Exception {
		ResultDataDto<List<${table.humpClassName}VO>> resultDataDto = new ResultDataDto<List<${table.humpClassName}VO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(${table.humpClassName}VO ${table.humpTableName}Vo)  throws Exception {
		ResultDto resultDto = new ResultDto();
		${table.humpClassName}Entity ${table.humpTableName}Entity = cglibConvertService.copyPropertity(${table.humpClassName}Entity.class,${table.humpTableName}Vo);
		${table.humpTableName}Mapper.update(${table.humpTableName}Entity);
		return resultDto;
	}

}