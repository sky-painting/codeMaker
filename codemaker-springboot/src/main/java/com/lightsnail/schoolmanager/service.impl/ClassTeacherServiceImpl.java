package com.lightsnail.schoolmanager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.mapper.ClassTeacherMapper;
import com.lightsnail.schoolmanager.service.ClassTeacherService;
import com.lightsnail.schoolmanager.vo.ClassTeacherVO;
import com.lightsnail.schoolmanager.entity.ClassTeacherEntity;

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
public class ClassTeacherServiceImpl implements ClassTeacherService{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ClassTeacherMapper classTeacherMapper;
	
	@Override
	public ResultDto save(ClassTeacherVO vo) {
		ResultDto resultDto = new ResultDto();
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		logger.info("classTeacherService.delete,id="+id);
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDataDto<ClassTeacherVO> getById(Long id) {
		ResultDataDto<ClassTeacherVO> resultDataDto = new ResultDataDto<ClassTeacherVO>();
		logger.info("classTeacherService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<ClassTeacherVO>> getAll() {
		ResultDataDto<List<ClassTeacherVO>> resultDataDto = new ResultDataDto<List<ClassTeacherVO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(ClassTeacherVO vo) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}