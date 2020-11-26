package com.lightsnail.app.user.crm.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.coderman.utils.bean.CglibConvertService;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.app.user.crm.core.mapper.RoomAgentOrderMapper;
import com.lightsnail.app.user.crm.core.service.RoomAgentOrderService;
import com.lightsnail.app.user.crm.core.vo.RoomAgentOrderVO;
import com.lightsnail.app.user.crm.core.entity.RoomAgentOrderEntity;

import java.util.List;


/**
 * @Description:代理人-签单表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2020-11-17 00:02:53
 * @version v1.0
 */
@Service
@Slf4j
public class RoomAgentOrderServiceImpl implements RoomAgentOrderService{


	@Resource
	private RoomAgentOrderMapper roomAgentOrderMapper;

    @Autowired
    private CglibConvertService cglibConvertService;

	@Override
	public ResultDto save(RoomAgentOrderVO roomAgentOrderVo)  throws Exception{
		ResultDto resultDto = new ResultDto();
        RoomAgentOrderEntity roomAgentOrderEntity = cglibConvertService.copyPropertity(RoomAgentOrderEntity.class,roomAgentOrderVo);
        roomAgentOrderMapper.insert(roomAgentOrderEntity);
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		log.info("roomAgentOrderService.delete,id="+id);
       roomAgentOrderMapper.deleteById(id);
		return resultDto;
	}
	
	@Override
	public ResultDataDto<RoomAgentOrderVO> getById(Long id)  throws Exception {
		ResultDataDto<RoomAgentOrderVO> resultDataDto = new ResultDataDto<RoomAgentOrderVO>();
		RoomAgentOrderEntity roomAgentOrderEntity = roomAgentOrderMapper.getById(id);
		RoomAgentOrderVO roomAgentOrderVo = cglibConvertService.copyPropertity(RoomAgentOrderVO.class,roomAgentOrderEntity);
		resultDataDto.setData(roomAgentOrderVo);
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<RoomAgentOrderVO>> getAll()  throws Exception {
		ResultDataDto<List<RoomAgentOrderVO>> resultDataDto = new ResultDataDto<List<RoomAgentOrderVO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(RoomAgentOrderVO roomAgentOrderVo)  throws Exception {
		ResultDto resultDto = new ResultDto();
		RoomAgentOrderEntity roomAgentOrderEntity = cglibConvertService.copyPropertity(RoomAgentOrderEntity.class,roomAgentOrderVo);
		roomAgentOrderMapper.update(roomAgentOrderEntity);
		return resultDto;
	}

}