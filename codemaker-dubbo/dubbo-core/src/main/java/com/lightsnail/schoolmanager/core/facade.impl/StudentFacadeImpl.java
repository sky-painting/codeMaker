package com.lightsnail.schoolmanager.core.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.api.facade.StudentFacade;
import com.lightsnail.schoolmanager.api.dto.StudentDTO;

import java.util.List;

/**
 * @Description:Facade接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 16:19:51
 * @version v1.0
 */
@Service
public class StudentFacadeImpl implements StudentFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public ResultDto save(StudentDTO dto) {
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
	public ResultDataDto<StudentDTO> getById(Long id) {
		ResultDataDto<StudentDTO> resultDataDto = new ResultDataDto<StudentDTO>();
		logger.info("studentService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<StudentDTO>> getAll() {
		ResultDataDto<List<StudentDTO>> resultDataDto = new ResultDataDto<List<StudentDTO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(StudentDTO dto) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}