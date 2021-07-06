package com.lightsnail.schoolmanager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.mapper.TeacherMapper;
import com.lightsnail.schoolmanager.service.TeacherService;
import com.lightsnail.schoolmanager.vo.TeacherVO;
import com.lightsnail.schoolmanager.entity.TeacherEntity;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * @Description:Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 16:08:53
 * @version v1.0
 */
@Service
public class TeacherServiceImpl implements TeacherService{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TeacherMapper teacherMapper;
	
	@Override
	public ResultDto save(TeacherVO vo) {
		ResultDto resultDto = new ResultDto();
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		logger.info("teacherService.delete,id="+id);
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDataDto<TeacherVO> getById(Long id) {
		ResultDataDto<TeacherVO> resultDataDto = new ResultDataDto<TeacherVO>();
		logger.info("teacherService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<TeacherVO>> getAll() {
		ResultDataDto<List<TeacherVO>> resultDataDto = new ResultDataDto<List<TeacherVO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(TeacherVO vo) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}