package com.lightsnail.schoolmanager.core.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.api.facade.RealRoomSourceFacade;
import com.lightsnail.schoolmanager.api.dto.RealRoomSourceDTO;

import java.util.List;

/**
 * @Description:房东-房源表Facade接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
public class RealRoomSourceFacadeImpl implements RealRoomSourceFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public ResultDto save(RealRoomSourceDTO dto) {
		ResultDto resultDto = new ResultDto();
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		logger.info("realRoomSourceService.delete,id="+id);
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDataDto<RealRoomSourceDTO> getById(Long id) {
		ResultDataDto<RealRoomSourceDTO> resultDataDto = new ResultDataDto<RealRoomSourceDTO>();
		logger.info("realRoomSourceService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<RealRoomSourceDTO>> getAll() {
		ResultDataDto<List<RealRoomSourceDTO>> resultDataDto = new ResultDataDto<List<RealRoomSourceDTO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(RealRoomSourceDTO dto) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}