package com.lightsnail.schoolmanager.core.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.api.facade.AttractInvestmentFacade;
import com.lightsnail.schoolmanager.api.dto.AttractInvestmentDTO;

import java.util.List;

/**
 * @Description:招商服务注册表Facade接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
public class AttractInvestmentFacadeImpl implements AttractInvestmentFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public ResultDto save(AttractInvestmentDTO dto) {
		ResultDto resultDto = new ResultDto();
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		logger.info("attractInvestmentService.delete,id="+id);
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDataDto<AttractInvestmentDTO> getById(Long id) {
		ResultDataDto<AttractInvestmentDTO> resultDataDto = new ResultDataDto<AttractInvestmentDTO>();
		logger.info("attractInvestmentService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<AttractInvestmentDTO>> getAll() {
		ResultDataDto<List<AttractInvestmentDTO>> resultDataDto = new ResultDataDto<List<AttractInvestmentDTO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(AttractInvestmentDTO dto) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}