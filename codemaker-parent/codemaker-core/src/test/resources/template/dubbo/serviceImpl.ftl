package ${package}.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import ${package}.dao.mapper.${table.humpClassName}Mapper;
import ${package}.service.${table.humpClassName}Service;
import ${package}.model.bo.${table.humpClassName}BO;

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


	@Override
	public ${table.humpClassName}BO save(${table.humpClassName}BO ${table.humpTableName}BO)  throws Exception{
		return ${table.humpTableName}BO;
	}
	
	@Override
	public Boolean delete(Long id) {
		return false;
	}
	
	@Override
	public ${table.humpClassName}BO getById(Long id)  throws Exception {
		return null;
	}
	
	@Override
	public List<${table.humpClassName}BO> getAll()  throws Exception {
		return null;
	}

	@Override
	public Boolean update(${table.humpClassName}BO ${table.humpTableName}BO)  throws Exception {
		return false;
	}

	@Override
	public List<${table.humpClassName}BO> getPageList()  throws Exception {
		return null;
	}
}