package com.lightsnail.app.user.crm.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.coderman.utils.bean.CglibConvertService;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.app.user.crm.core.mapper.LandlordContractTemplateMapper;
import com.lightsnail.app.user.crm.core.service.LandlordContractTemplateService;
import com.lightsnail.app.user.crm.core.vo.LandlordContractTemplateVO;
import com.lightsnail.app.user.crm.core.entity.LandlordContractTemplateEntity;

import java.util.List;


/**
 * @Description:房东合同模板表Service接口实现类
 * @Author：shenshuai
 * @CreateTime：2020-11-17 00:02:53
 * @version v1.0
 */
@Service
@Slf4j
public class LandlordContractTemplateServiceImpl implements LandlordContractTemplateService{


	@Resource
	private LandlordContractTemplateMapper landlordContractTemplateMapper;

    @Autowired
    private CglibConvertService cglibConvertService;

	@Override
	public ResultDto save(LandlordContractTemplateVO landlordContractTemplateVo)  throws Exception{
		ResultDto resultDto = new ResultDto();
        LandlordContractTemplateEntity landlordContractTemplateEntity = cglibConvertService.copyPropertity(LandlordContractTemplateEntity.class,landlordContractTemplateVo);
        landlordContractTemplateMapper.insert(landlordContractTemplateEntity);
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		log.info("landlordContractTemplateService.delete,id="+id);
       landlordContractTemplateMapper.deleteById(id);
		return resultDto;
	}
	
	@Override
	public ResultDataDto<LandlordContractTemplateVO> getById(Long id)  throws Exception {
		ResultDataDto<LandlordContractTemplateVO> resultDataDto = new ResultDataDto<LandlordContractTemplateVO>();
		LandlordContractTemplateEntity landlordContractTemplateEntity = landlordContractTemplateMapper.getById(id);
		LandlordContractTemplateVO landlordContractTemplateVo = cglibConvertService.copyPropertity(LandlordContractTemplateVO.class,landlordContractTemplateEntity);
		resultDataDto.setData(landlordContractTemplateVo);
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<LandlordContractTemplateVO>> getAll()  throws Exception {
		ResultDataDto<List<LandlordContractTemplateVO>> resultDataDto = new ResultDataDto<List<LandlordContractTemplateVO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(LandlordContractTemplateVO landlordContractTemplateVo)  throws Exception {
		ResultDto resultDto = new ResultDto();
		LandlordContractTemplateEntity landlordContractTemplateEntity = cglibConvertService.copyPropertity(LandlordContractTemplateEntity.class,landlordContractTemplateVo);
		landlordContractTemplateMapper.update(landlordContractTemplateEntity);
		return resultDto;
	}

}