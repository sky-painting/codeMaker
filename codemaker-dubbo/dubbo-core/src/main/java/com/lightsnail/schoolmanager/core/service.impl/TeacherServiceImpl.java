package com.lightsnail.schoolmanager.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lightsnail.schoolmanager.common.mapper.TeacherMapper;
import com.lightsnail.schoolmanager.core.service.TeacherService;
import com.lightsnail.schoolmanager.core.model.bo.TeacherBO;

import java.util.List;


/**
 * @Description:Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 16:19:51
 * @version v1.0
 */
@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService{


	@Resource
	private TeacherMapper teacherMapper;


	@Override
	public TeacherBO save(TeacherBO teacherBO)  throws Exception{
		return teacherBO;
	}
	
	@Override
	public Boolean delete(Long id) {
		return false;
	}
	
	@Override
	public TeacherBO getById(Long id)  throws Exception {
		return null;
	}
	
	@Override
	public List<TeacherBO> getAll()  throws Exception {
		return null;
	}

	@Override
	public TeacherBO update(TeacherBO teacherBO)  throws Exception {
		return teacherBO;
	}

}