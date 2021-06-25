package com.lightsnail.schoolmanager.core.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.api.facade.LandlordRoomSourceFacade;
import com.lightsnail.schoolmanager.api.dto.LandlordRoomSourceDTO;

import java.util.List;

/**
 * @Description:房东-房源表Facade接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
public class LandlordRoomSourceFacadeImpl implements LandlordRoomSourceFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public ResultDto save(LandlordRoomSourceDTO dto) {
		ResultDto resultDto = new ResultDto();
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		logger.info("landlordRoomSourceService.delete,id="+id);
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDataDto<LandlordRoomSourceDTO> getById(Long id) {
		ResultDataDto<LandlordRoomSourceDTO> resultDataDto = new ResultDataDto<LandlordRoomSourceDTO>();
		logger.info("landlordRoomSourceService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<LandlordRoomSourceDTO>> getAll() {
		ResultDataDto<List<LandlordRoomSourceDTO>> resultDataDto = new ResultDataDto<List<LandlordRoomSourceDTO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(LandlordRoomSourceDTO dto) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}