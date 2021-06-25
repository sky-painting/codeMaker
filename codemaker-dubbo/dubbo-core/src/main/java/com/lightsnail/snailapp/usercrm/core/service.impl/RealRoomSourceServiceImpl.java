package com.lightsnail.schoolmanager.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lightsnail.schoolmanager.common.mapper.RealRoomSourceMapper;
import com.lightsnail.schoolmanager.core.service.RealRoomSourceService;
import com.lightsnail.schoolmanager.core.model.bo.RealRoomSourceBO;

import java.util.List;


/**
 * @Description:房东-房源表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
@Slf4j
public class RealRoomSourceServiceImpl implements RealRoomSourceService{


	@Resource
	private RealRoomSourceMapper realRoomSourceMapper;


	@Override
	public RealRoomSourceBO save(RealRoomSourceBO realRoomSourceBO)  throws Exception{
		return realRoomSourceBO;
	}
	
	@Override
	public Boolean delete(Long id) {
		return false;
	}
	
	@Override
	public RealRoomSourceBO getById(Long id)  throws Exception {
		return null;
	}
	
	@Override
	public List<RealRoomSourceBO> getAll()  throws Exception {
		return null;
	}

	@Override
	public RealRoomSourceBO update(RealRoomSourceBO realRoomSourceBO)  throws Exception {
		return realRoomSourceBO;
	}

}