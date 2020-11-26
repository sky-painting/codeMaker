package com.lightsnail.app.user.crm.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.coderman.utils.bean.CglibConvertService;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.app.user.crm.core.mapper.RoomLandlordMapper;
import com.lightsnail.app.user.crm.core.service.RoomLandlordService;
import com.lightsnail.app.user.crm.core.vo.RoomLandlordVO;
import com.lightsnail.app.user.crm.core.entity.RoomLandlordEntity;

import java.util.List;


/**
 * @Description:房东表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2020-11-17 00:02:53
 * @version v1.0
 */
@Service
@Slf4j
public class RoomLandlordServiceImpl implements RoomLandlordService{


	@Resource
	private RoomLandlordMapper roomLandlordMapper;

    @Autowired
    private CglibConvertService cglibConvertService;

	@Override
	public ResultDto save(RoomLandlordVO roomLandlordVo)  throws Exception{
		ResultDto resultDto = new ResultDto();
        RoomLandlordEntity roomLandlordEntity = cglibConvertService.copyPropertity(RoomLandlordEntity.class,roomLandlordVo);
        roomLandlordMapper.insert(roomLandlordEntity);
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		log.info("roomLandlordService.delete,id="+id);
       roomLandlordMapper.deleteById(id);
		return resultDto;
	}
	
	@Override
	public ResultDataDto<RoomLandlordVO> getById(Long id)  throws Exception {
		ResultDataDto<RoomLandlordVO> resultDataDto = new ResultDataDto<RoomLandlordVO>();
		RoomLandlordEntity roomLandlordEntity = roomLandlordMapper.getById(id);
		RoomLandlordVO roomLandlordVo = cglibConvertService.copyPropertity(RoomLandlordVO.class,roomLandlordEntity);
		resultDataDto.setData(roomLandlordVo);
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<RoomLandlordVO>> getAll()  throws Exception {
		ResultDataDto<List<RoomLandlordVO>> resultDataDto = new ResultDataDto<List<RoomLandlordVO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(RoomLandlordVO roomLandlordVo)  throws Exception {
		ResultDto resultDto = new ResultDto();
		RoomLandlordEntity roomLandlordEntity = cglibConvertService.copyPropertity(RoomLandlordEntity.class,roomLandlordVo);
		roomLandlordMapper.update(roomLandlordEntity);
		return resultDto;
	}

}