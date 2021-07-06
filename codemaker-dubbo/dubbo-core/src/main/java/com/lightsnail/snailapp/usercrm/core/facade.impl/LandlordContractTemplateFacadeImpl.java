package com.lightsnail.schoolmanager.core.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.api.facade.LandlordContractTemplateFacade;
import com.lightsnail.schoolmanager.api.dto.LandlordContractTemplateDTO;

import java.util.List;

/**
 * @Description:房东合同模板表Facade接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
public class LandlordContractTemplateFacadeImpl implements LandlordContractTemplateFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public ResultDto save(LandlordContractTemplateDTO dto) {
		ResultDto resultDto = new ResultDto();
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		logger.info("landlordContractTemplateService.delete,id="+id);
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDataDto<LandlordContractTemplateDTO> getById(Long id) {
		ResultDataDto<LandlordContractTemplateDTO> resultDataDto = new ResultDataDto<LandlordContractTemplateDTO>();
		logger.info("landlordContractTemplateService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<LandlordContractTemplateDTO>> getAll() {
		ResultDataDto<List<LandlordContractTemplateDTO>> resultDataDto = new ResultDataDto<List<LandlordContractTemplateDTO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(LandlordContractTemplateDTO dto) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}