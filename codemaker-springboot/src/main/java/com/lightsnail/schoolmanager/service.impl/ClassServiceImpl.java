package com.lightsnail.schoolmanager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.mapper.ClassMapper;
import com.lightsnail.schoolmanager.service.ClassService;
import com.lightsnail.schoolmanager.vo.ClassVO;
import com.lightsnail.schoolmanager.entity.ClassEntity;

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
public class ClassServiceImpl implements ClassService{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ClassMapper classMapper;
	
	@Override
	public ResultDto save(ClassVO vo) {
		ResultDto resultDto = new ResultDto();
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		logger.info("classService.delete,id="+id);
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDataDto<ClassVO> getById(Long id) {
		ResultDataDto<ClassVO> resultDataDto = new ResultDataDto<ClassVO>();
		logger.info("classService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<ClassVO>> getAll() {
		ResultDataDto<List<ClassVO>> resultDataDto = new ResultDataDto<List<ClassVO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(ClassVO vo) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}