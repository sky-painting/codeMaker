package ${package}.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ${table.humpClassName}ServiceImpl implements ${table.humpClassName}Service{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ${table.humpClassName}Mapper ${table.humpTableName}Mapper;
	
	@Override
	public ${table.humpClassName}BO save(${table.humpClassName}BO bo) {
		return null;
	}
	
	@Override
	public boolean delete(Long id) {
		return false;
	}
	
	@Override
	public ${table.humpClassName}BO getById(Long id) {
		return null;
	}
	
	@Override
	public List<${table.humpClassName}BO> getAll() {
		return null;
	}

	@Override
	public boolean update(${table.humpClassName}BO bo) {
		return false;
	}

}