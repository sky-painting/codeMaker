package com.lightsnail.schoolmanager.core.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.api.facade.RoomAgentOrderFacade;
import com.lightsnail.schoolmanager.api.dto.RoomAgentOrderDTO;

import java.util.List;

/**
 * @Description:代理人-签单表Facade接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
public class RoomAgentOrderFacadeImpl implements RoomAgentOrderFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public ResultDto save(RoomAgentOrderDTO dto) {
		ResultDto resultDto = new ResultDto();
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		logger.info("roomAgentOrderService.delete,id="+id);
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDataDto<RoomAgentOrderDTO> getById(Long id) {
		ResultDataDto<RoomAgentOrderDTO> resultDataDto = new ResultDataDto<RoomAgentOrderDTO>();
		logger.info("roomAgentOrderService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<RoomAgentOrderDTO>> getAll() {
		ResultDataDto<List<RoomAgentOrderDTO>> resultDataDto = new ResultDataDto<List<RoomAgentOrderDTO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(RoomAgentOrderDTO dto) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}