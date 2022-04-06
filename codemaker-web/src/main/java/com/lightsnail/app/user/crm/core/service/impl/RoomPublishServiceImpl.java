package com.lightsnail.app.user.crm.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.coderman.utils.bean.CglibConvertService;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.app.user.crm.core.mapper.RoomPublishMapper;
import com.lightsnail.app.user.crm.core.service.RoomPublishService;
import com.lightsnail.app.user.crm.core.vo.RoomPublishVO;
import com.lightsnail.app.user.crm.core.entity.RoomPublishEntity;

import java.util.List;


/**
 * @Description:代理人-房源发布表Service接口实现类
 * @Author：shenshuai
 * @CreateTime：2020-11-17 00:02:53
 * @version v1.0
 */
@Service
@Slf4j
public class RoomPublishServiceImpl implements RoomPublishService{


	@Resource
	private RoomPublishMapper roomPublishMapper;

    @Autowired
    private CglibConvertService cglibConvertService;

	@Override
	public ResultDto save(RoomPublishVO roomPublishVo)  throws Exception{
		ResultDto resultDto = new ResultDto();
        RoomPublishEntity roomPublishEntity = cglibConvertService.copyPropertity(RoomPublishEntity.class,roomPublishVo);
        roomPublishMapper.insert(roomPublishEntity);
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		log.info("roomPublishService.delete,id="+id);
       roomPublishMapper.deleteById(id);
		return resultDto;
	}
	
	@Override
	public ResultDataDto<RoomPublishVO> getById(Long id)  throws Exception {
		ResultDataDto<RoomPublishVO> resultDataDto = new ResultDataDto<RoomPublishVO>();
		RoomPublishEntity roomPublishEntity = roomPublishMapper.getById(id);
		RoomPublishVO roomPublishVo = cglibConvertService.copyPropertity(RoomPublishVO.class,roomPublishEntity);
		resultDataDto.setData(roomPublishVo);
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<RoomPublishVO>> getAll()  throws Exception {
		ResultDataDto<List<RoomPublishVO>> resultDataDto = new ResultDataDto<List<RoomPublishVO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(RoomPublishVO roomPublishVo)  throws Exception {
		ResultDto resultDto = new ResultDto();
		RoomPublishEntity roomPublishEntity = cglibConvertService.copyPropertity(RoomPublishEntity.class,roomPublishVo);
		roomPublishMapper.update(roomPublishEntity);
		return resultDto;
	}

}