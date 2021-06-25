package com.lightsnail.schoolmanager.core.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.api.facade.ClassTeacherFacade;
import com.lightsnail.schoolmanager.api.dto.ClassTeacherDTO;

import java.util.List;

/**
 * @Description:Facade接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 16:19:51
 * @version v1.0
 */
@Service
public class ClassTeacherFacadeImpl implements ClassTeacherFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public ResultDto save(ClassTeacherDTO dto) {
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
	public ResultDataDto<ClassTeacherDTO> getById(Long id) {
		ResultDataDto<ClassTeacherDTO> resultDataDto = new ResultDataDto<ClassTeacherDTO>();
		logger.info("classTeacherService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<ClassTeacherDTO>> getAll() {
		ResultDataDto<List<ClassTeacherDTO>> resultDataDto = new ResultDataDto<List<ClassTeacherDTO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(ClassTeacherDTO dto) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}