package com.lightsnail.schoolmanager.core.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.api.facade.RoomSubscribeFacade;
import com.lightsnail.schoolmanager.api.dto.RoomSubscribeDTO;

import java.util.List;

/**
 * @Description:代理人-预约看房记录表Facade接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
public class RoomSubscribeFacadeImpl implements RoomSubscribeFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public ResultDto save(RoomSubscribeDTO dto) {
		ResultDto resultDto = new ResultDto();
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		logger.info("roomSubscribeService.delete,id="+id);
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDataDto<RoomSubscribeDTO> getById(Long id) {
		ResultDataDto<RoomSubscribeDTO> resultDataDto = new ResultDataDto<RoomSubscribeDTO>();
		logger.info("roomSubscribeService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<RoomSubscribeDTO>> getAll() {
		ResultDataDto<List<RoomSubscribeDTO>> resultDataDto = new ResultDataDto<List<RoomSubscribeDTO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(RoomSubscribeDTO dto) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}