package com.lightsnail.schoolmanager.core.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.api.facade.ClassFacade;
import com.lightsnail.schoolmanager.api.dto.ClassDTO;

import java.util.List;

/**
 * @Description:Facade接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 16:19:52
 * @version v1.0
 */
@Service
public class ClassFacadeImpl implements ClassFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public ResultDto save(ClassDTO dto) {
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
	public ResultDataDto<ClassDTO> getById(Long id) {
		ResultDataDto<ClassDTO> resultDataDto = new ResultDataDto<ClassDTO>();
		logger.info("classService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<ClassDTO>> getAll() {
		ResultDataDto<List<ClassDTO>> resultDataDto = new ResultDataDto<List<ClassDTO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(ClassDTO dto) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}