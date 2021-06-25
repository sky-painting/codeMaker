package com.lightsnail.schoolmanager.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lightsnail.schoolmanager.common.mapper.StudentMapper;
import com.lightsnail.schoolmanager.core.service.StudentService;
import com.lightsnail.schoolmanager.core.model.bo.StudentBO;

import java.util.List;


/**
 * @Description:Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 16:19:51
 * @version v1.0
 */
@Service
@Slf4j
public class StudentServiceImpl implements StudentService{


	@Resource
	private StudentMapper studentMapper;


	@Override
	public StudentBO save(StudentBO studentBO)  throws Exception{
		return studentBO;
	}
	
	@Override
	public Boolean delete(Long id) {
		return false;
	}
	
	@Override
	public StudentBO getById(Long id)  throws Exception {
		return null;
	}
	
	@Override
	public List<StudentBO> getAll()  throws Exception {
		return null;
	}

	@Override
	public StudentBO update(StudentBO studentBO)  throws Exception {
		return studentBO;
	}

}