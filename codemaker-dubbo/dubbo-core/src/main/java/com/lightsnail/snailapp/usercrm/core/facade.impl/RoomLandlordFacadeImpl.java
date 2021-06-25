package com.lightsnail.schoolmanager.core.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.api.facade.RoomLandlordFacade;
import com.lightsnail.schoolmanager.api.dto.RoomLandlordDTO;

import java.util.List;

/**
 * @Description:房东表Facade接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
public class RoomLandlordFacadeImpl implements RoomLandlordFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public ResultDto save(RoomLandlordDTO dto) {
		ResultDto resultDto = new ResultDto();
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		logger.info("roomLandlordService.delete,id="+id);
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDataDto<RoomLandlordDTO> getById(Long id) {
		ResultDataDto<RoomLandlordDTO> resultDataDto = new ResultDataDto<RoomLandlordDTO>();
		logger.info("roomLandlordService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<RoomLandlordDTO>> getAll() {
		ResultDataDto<List<RoomLandlordDTO>> resultDataDto = new ResultDataDto<List<RoomLandlordDTO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(RoomLandlordDTO dto) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}