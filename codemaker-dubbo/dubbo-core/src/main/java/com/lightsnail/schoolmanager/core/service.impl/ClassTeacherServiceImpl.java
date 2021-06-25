package com.lightsnail.schoolmanager.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lightsnail.schoolmanager.common.mapper.ClassTeacherMapper;
import com.lightsnail.schoolmanager.core.service.ClassTeacherService;
import com.lightsnail.schoolmanager.core.model.bo.ClassTeacherBO;

import java.util.List;


/**
 * @Description:Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 16:19:51
 * @version v1.0
 */
@Service
@Slf4j
public class ClassTeacherServiceImpl implements ClassTeacherService{


	@Resource
	private ClassTeacherMapper classTeacherMapper;


	@Override
	public ClassTeacherBO save(ClassTeacherBO classTeacherBO)  throws Exception{
		return classTeacherBO;
	}
	
	@Override
	public Boolean delete(Long id) {
		return false;
	}
	
	@Override
	public ClassTeacherBO getById(Long id)  throws Exception {
		return null;
	}
	
	@Override
	public List<ClassTeacherBO> getAll()  throws Exception {
		return null;
	}

	@Override
	public ClassTeacherBO update(ClassTeacherBO classTeacherBO)  throws Exception {
		return classTeacherBO;
	}

}