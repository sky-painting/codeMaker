package com.lightsnail.schoolmanager.core.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.api.facade.LandlordRoomRentRecordFacade;
import com.lightsnail.schoolmanager.api.dto.LandlordRoomRentRecordDTO;

import java.util.List;

/**
 * @Description:房源出租记录表Facade接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
public class LandlordRoomRentRecordFacadeImpl implements LandlordRoomRentRecordFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public ResultDto save(LandlordRoomRentRecordDTO dto) {
		ResultDto resultDto = new ResultDto();
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		logger.info("landlordRoomRentRecordService.delete,id="+id);
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDataDto<LandlordRoomRentRecordDTO> getById(Long id) {
		ResultDataDto<LandlordRoomRentRecordDTO> resultDataDto = new ResultDataDto<LandlordRoomRentRecordDTO>();
		logger.info("landlordRoomRentRecordService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<LandlordRoomRentRecordDTO>> getAll() {
		ResultDataDto<List<LandlordRoomRentRecordDTO>> resultDataDto = new ResultDataDto<List<LandlordRoomRentRecordDTO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(LandlordRoomRentRecordDTO dto) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}