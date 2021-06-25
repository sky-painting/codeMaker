package com.lightsnail.schoolmanager.core.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.api.facade.TeacherFacade;
import com.lightsnail.schoolmanager.api.dto.TeacherDTO;

import java.util.List;

/**
 * @Description:Facade接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 16:19:51
 * @version v1.0
 */
@Service
public class TeacherFacadeImpl implements TeacherFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public ResultDto save(TeacherDTO dto) {
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
	public ResultDataDto<TeacherDTO> getById(Long id) {
		ResultDataDto<TeacherDTO> resultDataDto = new ResultDataDto<TeacherDTO>();
		logger.info("teacherService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<TeacherDTO>> getAll() {
		ResultDataDto<List<TeacherDTO>> resultDataDto = new ResultDataDto<List<TeacherDTO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(TeacherDTO dto) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}