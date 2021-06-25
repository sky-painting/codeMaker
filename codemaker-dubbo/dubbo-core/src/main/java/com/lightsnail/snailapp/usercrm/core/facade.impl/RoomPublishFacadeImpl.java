package com.lightsnail.schoolmanager.core.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.api.facade.RoomPublishFacade;
import com.lightsnail.schoolmanager.api.dto.RoomPublishDTO;

import java.util.List;

/**
 * @Description:代理人-房源发布表Facade接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
public class RoomPublishFacadeImpl implements RoomPublishFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public ResultDto save(RoomPublishDTO dto) {
		ResultDto resultDto = new ResultDto();
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		logger.info("roomPublishService.delete,id="+id);
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDataDto<RoomPublishDTO> getById(Long id) {
		ResultDataDto<RoomPublishDTO> resultDataDto = new ResultDataDto<RoomPublishDTO>();
		logger.info("roomPublishService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<RoomPublishDTO>> getAll() {
		ResultDataDto<List<RoomPublishDTO>> resultDataDto = new ResultDataDto<List<RoomPublishDTO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(RoomPublishDTO dto) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}