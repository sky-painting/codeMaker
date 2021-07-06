package com.lightsnail.schoolmanager.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lightsnail.schoolmanager.common.mapper.RoomSubscribeMapper;
import com.lightsnail.schoolmanager.core.service.RoomSubscribeService;
import com.lightsnail.schoolmanager.core.model.bo.RoomSubscribeBO;

import java.util.List;


/**
 * @Description:代理人-预约看房记录表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
@Slf4j
public class RoomSubscribeServiceImpl implements RoomSubscribeService{


	@Resource
	private RoomSubscribeMapper roomSubscribeMapper;


	@Override
	public RoomSubscribeBO save(RoomSubscribeBO roomSubscribeBO)  throws Exception{
		return roomSubscribeBO;
	}
	
	@Override
	public Boolean delete(Long id) {
		return false;
	}
	
	@Override
	public RoomSubscribeBO getById(Long id)  throws Exception {
		return null;
	}
	
	@Override
	public List<RoomSubscribeBO> getAll()  throws Exception {
		return null;
	}

	@Override
	public RoomSubscribeBO update(RoomSubscribeBO roomSubscribeBO)  throws Exception {
		return roomSubscribeBO;
	}

}