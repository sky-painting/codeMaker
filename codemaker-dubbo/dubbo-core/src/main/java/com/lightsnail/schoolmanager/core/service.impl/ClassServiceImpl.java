package com.lightsnail.schoolmanager.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lightsnail.schoolmanager.common.mapper.ClassMapper;
import com.lightsnail.schoolmanager.core.service.ClassService;
import com.lightsnail.schoolmanager.core.model.bo.ClassBO;

import java.util.List;


/**
 * @Description:Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 16:19:51
 * @version v1.0
 */
@Service
@Slf4j
public class ClassServiceImpl implements ClassService{


	@Resource
	private ClassMapper classMapper;


	@Override
	public ClassBO save(ClassBO classBO)  throws Exception{
		return classBO;
	}
	
	@Override
	public Boolean delete(Long id) {
		return false;
	}
	
	@Override
	public ClassBO getById(Long id)  throws Exception {
		return null;
	}
	
	@Override
	public List<ClassBO> getAll()  throws Exception {
		return null;
	}

	@Override
	public ClassBO update(ClassBO classBO)  throws Exception {
		return classBO;
	}

}