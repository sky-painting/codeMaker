package com.lightsnail.app.user.crm.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.coderman.utils.bean.CglibConvertService;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.app.user.crm.core.mapper.RoomAgentMapper;
import com.lightsnail.app.user.crm.core.service.RoomAgentService;
import com.lightsnail.app.user.crm.core.vo.RoomAgentVO;
import com.lightsnail.app.user.crm.core.entity.RoomAgentEntity;

import java.util.List;


/**
 * @Description:代理人表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2020-11-17 00:02:53
 * @version v1.0
 */
@Service
@Slf4j
public class RoomAgentServiceImpl implements RoomAgentService{


	@Resource
	private RoomAgentMapper roomAgentMapper;

    @Autowired
    private CglibConvertService cglibConvertService;

	@Override
	public ResultDto save(RoomAgentVO roomAgentVo)  throws Exception{
		ResultDto resultDto = new ResultDto();
        RoomAgentEntity roomAgentEntity = cglibConvertService.copyPropertity(RoomAgentEntity.class,roomAgentVo);
        roomAgentMapper.insert(roomAgentEntity);
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		log.info("roomAgentService.delete,id="+id);
       roomAgentMapper.deleteById(id);
		return resultDto;
	}
	
	@Override
	public ResultDataDto<RoomAgentVO> getById(Long id)  throws Exception {
		ResultDataDto<RoomAgentVO> resultDataDto = new ResultDataDto<RoomAgentVO>();
		RoomAgentEntity roomAgentEntity = roomAgentMapper.getById(id);
		RoomAgentVO roomAgentVo = cglibConvertService.copyPropertity(RoomAgentVO.class,roomAgentEntity);
		resultDataDto.setData(roomAgentVo);
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<RoomAgentVO>> getAll()  throws Exception {
		ResultDataDto<List<RoomAgentVO>> resultDataDto = new ResultDataDto<List<RoomAgentVO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(RoomAgentVO roomAgentVo)  throws Exception {
		ResultDto resultDto = new ResultDto();
		RoomAgentEntity roomAgentEntity = cglibConvertService.copyPropertity(RoomAgentEntity.class,roomAgentVo);
		roomAgentMapper.update(roomAgentEntity);
		return resultDto;
	}

}