package com.lightsnail.schoolmanager.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lightsnail.schoolmanager.common.mapper.LandlordRoomSourceMapper;
import com.lightsnail.schoolmanager.core.service.LandlordRoomSourceService;
import com.lightsnail.schoolmanager.core.model.bo.LandlordRoomSourceBO;

import java.util.List;


/**
 * @Description:房东-房源表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
@Slf4j
public class LandlordRoomSourceServiceImpl implements LandlordRoomSourceService{


	@Resource
	private LandlordRoomSourceMapper landlordRoomSourceMapper;


	@Override
	public LandlordRoomSourceBO save(LandlordRoomSourceBO landlordRoomSourceBO)  throws Exception{
		return landlordRoomSourceBO;
	}
	
	@Override
	public Boolean delete(Long id) {
		return false;
	}
	
	@Override
	public LandlordRoomSourceBO getById(Long id)  throws Exception {
		return null;
	}
	
	@Override
	public List<LandlordRoomSourceBO> getAll()  throws Exception {
		return null;
	}

	@Override
	public LandlordRoomSourceBO update(LandlordRoomSourceBO landlordRoomSourceBO)  throws Exception {
		return landlordRoomSourceBO;
	}

}