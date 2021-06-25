package com.lightsnail.schoolmanager.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lightsnail.schoolmanager.common.mapper.RoomLandlordMapper;
import com.lightsnail.schoolmanager.core.service.RoomLandlordService;
import com.lightsnail.schoolmanager.core.model.bo.RoomLandlordBO;

import java.util.List;


/**
 * @Description:房东表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
@Slf4j
public class RoomLandlordServiceImpl implements RoomLandlordService{


	@Resource
	private RoomLandlordMapper roomLandlordMapper;


	@Override
	public RoomLandlordBO save(RoomLandlordBO roomLandlordBO)  throws Exception{
		return roomLandlordBO;
	}
	
	@Override
	public Boolean delete(Long id) {
		return false;
	}
	
	@Override
	public RoomLandlordBO getById(Long id)  throws Exception {
		return null;
	}
	
	@Override
	public List<RoomLandlordBO> getAll()  throws Exception {
		return null;
	}

	@Override
	public RoomLandlordBO update(RoomLandlordBO roomLandlordBO)  throws Exception {
		return roomLandlordBO;
	}

}