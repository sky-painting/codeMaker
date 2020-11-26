package com.lightsnail.app.user.crm.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.coderman.utils.bean.CglibConvertService;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.app.user.crm.core.mapper.LandlordRoomRentRecordMapper;
import com.lightsnail.app.user.crm.core.service.LandlordRoomRentRecordService;
import com.lightsnail.app.user.crm.core.vo.LandlordRoomRentRecordVO;
import com.lightsnail.app.user.crm.core.entity.LandlordRoomRentRecordEntity;

import java.util.List;


/**
 * @Description:房源出租记录表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2020-11-17 00:02:54
 * @version v1.0
 */
@Service
@Slf4j
public class LandlordRoomRentRecordServiceImpl implements LandlordRoomRentRecordService{


	@Resource
	private LandlordRoomRentRecordMapper landlordRoomRentRecordMapper;

    @Autowired
    private CglibConvertService cglibConvertService;

	@Override
	public ResultDto save(LandlordRoomRentRecordVO landlordRoomRentRecordVo)  throws Exception{
		ResultDto resultDto = new ResultDto();
        LandlordRoomRentRecordEntity landlordRoomRentRecordEntity = cglibConvertService.copyPropertity(LandlordRoomRentRecordEntity.class,landlordRoomRentRecordVo);
        landlordRoomRentRecordMapper.insert(landlordRoomRentRecordEntity);
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		log.info("landlordRoomRentRecordService.delete,id="+id);
       landlordRoomRentRecordMapper.deleteById(id);
		return resultDto;
	}
	
	@Override
	public ResultDataDto<LandlordRoomRentRecordVO> getById(Long id)  throws Exception {
		ResultDataDto<LandlordRoomRentRecordVO> resultDataDto = new ResultDataDto<LandlordRoomRentRecordVO>();
		LandlordRoomRentRecordEntity landlordRoomRentRecordEntity = landlordRoomRentRecordMapper.getById(id);
		LandlordRoomRentRecordVO landlordRoomRentRecordVo = cglibConvertService.copyPropertity(LandlordRoomRentRecordVO.class,landlordRoomRentRecordEntity);
		resultDataDto.setData(landlordRoomRentRecordVo);
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<LandlordRoomRentRecordVO>> getAll()  throws Exception {
		ResultDataDto<List<LandlordRoomRentRecordVO>> resultDataDto = new ResultDataDto<List<LandlordRoomRentRecordVO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(LandlordRoomRentRecordVO landlordRoomRentRecordVo)  throws Exception {
		ResultDto resultDto = new ResultDto();
		LandlordRoomRentRecordEntity landlordRoomRentRecordEntity = cglibConvertService.copyPropertity(LandlordRoomRentRecordEntity.class,landlordRoomRentRecordVo);
		landlordRoomRentRecordMapper.update(landlordRoomRentRecordEntity);
		return resultDto;
	}

}