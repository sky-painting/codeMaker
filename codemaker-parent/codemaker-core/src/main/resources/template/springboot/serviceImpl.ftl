package ${package}.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${package}.dao.mapper.${table.humpClassName}Mapper;
import ${package}.service.${table.humpClassName}Service;
import ${package}.bo.${table.humpClassName}BO;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

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
        //todo impl code
		return null;
	}
	
	@Override
	public Boolean delete(Long id) {
		logger.info("${table.humpTableName}Service.delete,id="+id);
        //todo impl code
		return false;
	}
	
	@Override
	public ${table.humpClassName}BO getById(Long id) {
		logger.info("${table.humpTableName}Service.getById,id="+id);
		//todo impl code
		return null;
	}
	
	@Override
	public List<${table.humpClassName}BO> getAll() {
		//todo impl code
		return	null;
	}

	@Override
	public Boolean update(${table.humpClassName}BO bo) {
		//todo impl code
		return false;
	}


	@Override
	public List<${table.humpClassName}BO> getPageList()  throws Exception {
		return null;
	}


	@Override
	public int getCount()  throws Exception {
		return 0;
	}

}