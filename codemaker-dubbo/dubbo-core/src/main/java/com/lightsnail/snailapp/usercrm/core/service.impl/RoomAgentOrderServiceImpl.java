package com.lightsnail.schoolmanager.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lightsnail.schoolmanager.common.mapper.RoomAgentOrderMapper;
import com.lightsnail.schoolmanager.core.service.RoomAgentOrderService;
import com.lightsnail.schoolmanager.core.model.bo.RoomAgentOrderBO;

import java.util.List;


/**
 * @Description:代理人-签单表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
@Slf4j
public class RoomAgentOrderServiceImpl implements RoomAgentOrderService{


	@Resource
	private RoomAgentOrderMapper roomAgentOrderMapper;


	@Override
	public RoomAgentOrderBO save(RoomAgentOrderBO roomAgentOrderBO)  throws Exception{
		return roomAgentOrderBO;
	}
	
	@Override
	public Boolean delete(Long id) {
		return false;
	}
	
	@Override
	public RoomAgentOrderBO getById(Long id)  throws Exception {
		return null;
	}
	
	@Override
	public List<RoomAgentOrderBO> getAll()  throws Exception {
		return null;
	}

	@Override
	public RoomAgentOrderBO update(RoomAgentOrderBO roomAgentOrderBO)  throws Exception {
		return roomAgentOrderBO;
	}

}