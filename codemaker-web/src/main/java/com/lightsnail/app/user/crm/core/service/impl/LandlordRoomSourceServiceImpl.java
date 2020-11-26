package com.lightsnail.app.user.crm.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.coderman.utils.bean.CglibConvertService;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.app.user.crm.core.mapper.LandlordRoomSourceMapper;
import com.lightsnail.app.user.crm.core.service.LandlordRoomSourceService;
import com.lightsnail.app.user.crm.core.vo.LandlordRoomSourceVO;
import com.lightsnail.app.user.crm.core.entity.LandlordRoomSourceEntity;

import java.util.List;


/**
 * @Description:房东-房源表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2020-11-17 00:02:53
 * @version v1.0
 */
@Service
@Slf4j
public class LandlordRoomSourceServiceImpl implements LandlordRoomSourceService{


	@Resource
	private LandlordRoomSourceMapper landlordRoomSourceMapper;

    @Autowired
    private CglibConvertService cglibConvertService;

	@Override
	public ResultDto save(LandlordRoomSourceVO landlordRoomSourceVo)  throws Exception{
		ResultDto resultDto = new ResultDto();
        LandlordRoomSourceEntity landlordRoomSourceEntity = cglibConvertService.copyPropertity(LandlordRoomSourceEntity.class,landlordRoomSourceVo);
        landlordRoomSourceMapper.insert(landlordRoomSourceEntity);
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		log.info("landlordRoomSourceService.delete,id="+id);
       landlordRoomSourceMapper.deleteById(id);
		return resultDto;
	}
	
	@Override
	public ResultDataDto<LandlordRoomSourceVO> getById(Long id)  throws Exception {
		ResultDataDto<LandlordRoomSourceVO> resultDataDto = new ResultDataDto<LandlordRoomSourceVO>();
		LandlordRoomSourceEntity landlordRoomSourceEntity = landlordRoomSourceMapper.getById(id);
		LandlordRoomSourceVO landlordRoomSourceVo = cglibConvertService.copyPropertity(LandlordRoomSourceVO.class,landlordRoomSourceEntity);
		resultDataDto.setData(landlordRoomSourceVo);
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<LandlordRoomSourceVO>> getAll()  throws Exception {
		ResultDataDto<List<LandlordRoomSourceVO>> resultDataDto = new ResultDataDto<List<LandlordRoomSourceVO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(LandlordRoomSourceVO landlordRoomSourceVo)  throws Exception {
		ResultDto resultDto = new ResultDto();
		LandlordRoomSourceEntity landlordRoomSourceEntity = cglibConvertService.copyPropertity(LandlordRoomSourceEntity.class,landlordRoomSourceVo);
		landlordRoomSourceMapper.update(landlordRoomSourceEntity);
		return resultDto;
	}

}