package com.lightsnail.app.user.crm.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.coderman.utils.bean.CglibConvertService;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.app.user.crm.core.mapper.RoomSubscribeMapper;
import com.lightsnail.app.user.crm.core.service.RoomSubscribeService;
import com.lightsnail.app.user.crm.core.vo.RoomSubscribeVO;
import com.lightsnail.app.user.crm.core.entity.RoomSubscribeEntity;

import java.util.List;


/**
 * @Description:代理人-预约看房记录表Service接口实现类
 * @Author：shenshuai
 * @CreateTime：2020-11-17 00:02:53
 * @version v1.0
 */
@Service
@Slf4j
public class RoomSubscribeServiceImpl implements RoomSubscribeService{


	@Resource
	private RoomSubscribeMapper roomSubscribeMapper;

    @Autowired
    private CglibConvertService cglibConvertService;

	@Override
	public ResultDto save(RoomSubscribeVO roomSubscribeVo)  throws Exception{
		ResultDto resultDto = new ResultDto();
        RoomSubscribeEntity roomSubscribeEntity = cglibConvertService.copyPropertity(RoomSubscribeEntity.class,roomSubscribeVo);
        roomSubscribeMapper.insert(roomSubscribeEntity);
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		log.info("roomSubscribeService.delete,id="+id);
       roomSubscribeMapper.deleteById(id);
		return resultDto;
	}
	
	@Override
	public ResultDataDto<RoomSubscribeVO> getById(Long id)  throws Exception {
		ResultDataDto<RoomSubscribeVO> resultDataDto = new ResultDataDto<RoomSubscribeVO>();
		RoomSubscribeEntity roomSubscribeEntity = roomSubscribeMapper.getById(id);
		RoomSubscribeVO roomSubscribeVo = cglibConvertService.copyPropertity(RoomSubscribeVO.class,roomSubscribeEntity);
		resultDataDto.setData(roomSubscribeVo);
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<RoomSubscribeVO>> getAll()  throws Exception {
		ResultDataDto<List<RoomSubscribeVO>> resultDataDto = new ResultDataDto<List<RoomSubscribeVO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(RoomSubscribeVO roomSubscribeVo)  throws Exception {
		ResultDto resultDto = new ResultDto();
		RoomSubscribeEntity roomSubscribeEntity = cglibConvertService.copyPropertity(RoomSubscribeEntity.class,roomSubscribeVo);
		roomSubscribeMapper.update(roomSubscribeEntity);
		return resultDto;
	}

}