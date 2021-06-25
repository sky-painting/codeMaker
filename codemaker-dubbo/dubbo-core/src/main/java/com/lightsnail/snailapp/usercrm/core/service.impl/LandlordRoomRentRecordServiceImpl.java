package com.lightsnail.schoolmanager.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lightsnail.schoolmanager.common.mapper.LandlordRoomRentRecordMapper;
import com.lightsnail.schoolmanager.core.service.LandlordRoomRentRecordService;
import com.lightsnail.schoolmanager.core.model.bo.LandlordRoomRentRecordBO;

import java.util.List;


/**
 * @Description:房源出租记录表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
@Slf4j
public class LandlordRoomRentRecordServiceImpl implements LandlordRoomRentRecordService{


	@Resource
	private LandlordRoomRentRecordMapper landlordRoomRentRecordMapper;


	@Override
	public LandlordRoomRentRecordBO save(LandlordRoomRentRecordBO landlordRoomRentRecordBO)  throws Exception{
		return landlordRoomRentRecordBO;
	}
	
	@Override
	public Boolean delete(Long id) {
		return false;
	}
	
	@Override
	public LandlordRoomRentRecordBO getById(Long id)  throws Exception {
		return null;
	}
	
	@Override
	public List<LandlordRoomRentRecordBO> getAll()  throws Exception {
		return null;
	}

	@Override
	public LandlordRoomRentRecordBO update(LandlordRoomRentRecordBO landlordRoomRentRecordBO)  throws Exception {
		return landlordRoomRentRecordBO;
	}

}