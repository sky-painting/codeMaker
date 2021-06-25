package com.lightsnail.schoolmanager.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lightsnail.schoolmanager.common.mapper.RoomPublishMapper;
import com.lightsnail.schoolmanager.core.service.RoomPublishService;
import com.lightsnail.schoolmanager.core.model.bo.RoomPublishBO;

import java.util.List;


/**
 * @Description:代理人-房源发布表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
@Slf4j
public class RoomPublishServiceImpl implements RoomPublishService{


	@Resource
	private RoomPublishMapper roomPublishMapper;


	@Override
	public RoomPublishBO save(RoomPublishBO roomPublishBO)  throws Exception{
		return roomPublishBO;
	}
	
	@Override
	public Boolean delete(Long id) {
		return false;
	}
	
	@Override
	public RoomPublishBO getById(Long id)  throws Exception {
		return null;
	}
	
	@Override
	public List<RoomPublishBO> getAll()  throws Exception {
		return null;
	}

	@Override
	public RoomPublishBO update(RoomPublishBO roomPublishBO)  throws Exception {
		return roomPublishBO;
	}

}