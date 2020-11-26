package com.lightsnail.app.user.crm.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.coderman.utils.bean.CglibConvertService;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.app.user.crm.core.mapper.RealRoomSourceMapper;
import com.lightsnail.app.user.crm.core.service.RealRoomSourceService;
import com.lightsnail.app.user.crm.core.vo.RealRoomSourceVO;
import com.lightsnail.app.user.crm.core.entity.RealRoomSourceEntity;

import java.util.List;


/**
 * @Description:房东-房源表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2020-11-17 00:02:53
 * @version v1.0
 */
@Service
@Slf4j
public class RealRoomSourceServiceImpl implements RealRoomSourceService{


	@Resource
	private RealRoomSourceMapper realRoomSourceMapper;

    @Autowired
    private CglibConvertService cglibConvertService;

	@Override
	public ResultDto save(RealRoomSourceVO realRoomSourceVo)  throws Exception{
		ResultDto resultDto = new ResultDto();
        RealRoomSourceEntity realRoomSourceEntity = cglibConvertService.copyPropertity(RealRoomSourceEntity.class,realRoomSourceVo);
        realRoomSourceMapper.insert(realRoomSourceEntity);
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		log.info("realRoomSourceService.delete,id="+id);
       realRoomSourceMapper.deleteById(id);
		return resultDto;
	}
	
	@Override
	public ResultDataDto<RealRoomSourceVO> getById(Long id)  throws Exception {
		ResultDataDto<RealRoomSourceVO> resultDataDto = new ResultDataDto<RealRoomSourceVO>();
		RealRoomSourceEntity realRoomSourceEntity = realRoomSourceMapper.getById(id);
		RealRoomSourceVO realRoomSourceVo = cglibConvertService.copyPropertity(RealRoomSourceVO.class,realRoomSourceEntity);
		resultDataDto.setData(realRoomSourceVo);
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<RealRoomSourceVO>> getAll()  throws Exception {
		ResultDataDto<List<RealRoomSourceVO>> resultDataDto = new ResultDataDto<List<RealRoomSourceVO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(RealRoomSourceVO realRoomSourceVo)  throws Exception {
		ResultDto resultDto = new ResultDto();
		RealRoomSourceEntity realRoomSourceEntity = cglibConvertService.copyPropertity(RealRoomSourceEntity.class,realRoomSourceVo);
		realRoomSourceMapper.update(realRoomSourceEntity);
		return resultDto;
	}

}