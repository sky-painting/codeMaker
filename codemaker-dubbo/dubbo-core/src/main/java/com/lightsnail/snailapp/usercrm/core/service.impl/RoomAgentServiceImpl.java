package com.lightsnail.schoolmanager.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lightsnail.schoolmanager.common.mapper.RoomAgentMapper;
import com.lightsnail.schoolmanager.core.service.RoomAgentService;
import com.lightsnail.schoolmanager.core.model.bo.RoomAgentBO;

import java.util.List;


/**
 * @Description:代理人表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
@Slf4j
public class RoomAgentServiceImpl implements RoomAgentService{


	@Resource
	private RoomAgentMapper roomAgentMapper;


	@Override
	public RoomAgentBO save(RoomAgentBO roomAgentBO)  throws Exception{
		return roomAgentBO;
	}
	
	@Override
	public Boolean delete(Long id) {
		return false;
	}
	
	@Override
	public RoomAgentBO getById(Long id)  throws Exception {
		return null;
	}
	
	@Override
	public List<RoomAgentBO> getAll()  throws Exception {
		return null;
	}

	@Override
	public RoomAgentBO update(RoomAgentBO roomAgentBO)  throws Exception {
		return roomAgentBO;
	}

}