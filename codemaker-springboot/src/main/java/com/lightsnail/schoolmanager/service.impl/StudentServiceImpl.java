package com.lightsnail.schoolmanager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.mapper.StudentMapper;
import com.lightsnail.schoolmanager.service.StudentService;
import com.lightsnail.schoolmanager.vo.StudentVO;
import com.lightsnail.schoolmanager.entity.StudentEntity;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * @Description:Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 16:08:54
 * @version v1.0
 */
@Service
public class StudentServiceImpl implements StudentService{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public ResultDto save(StudentVO vo) {
		ResultDto resultDto = new ResultDto();
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		logger.info("studentService.delete,id="+id);
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDataDto<StudentVO> getById(Long id) {
		ResultDataDto<StudentVO> resultDataDto = new ResultDataDto<StudentVO>();
		logger.info("studentService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<StudentVO>> getAll() {
		ResultDataDto<List<StudentVO>> resultDataDto = new ResultDataDto<List<StudentVO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(StudentVO vo) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}