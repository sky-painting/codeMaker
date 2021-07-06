package com.lightsnail.schoolmanager.core.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.api.facade.AccountFacade;
import com.lightsnail.schoolmanager.api.dto.AccountDTO;

import java.util.List;

/**
 * @Description:合作用户表Facade接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
public class AccountFacadeImpl implements AccountFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@Override
	public ResultDto save(AccountDTO dto) {
		ResultDto resultDto = new ResultDto();
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		logger.info("accountService.delete,id="+id);
        //todo impl code
		return resultDto;
	}
	
	@Override
	public ResultDataDto<AccountDTO> getById(Long id) {
		ResultDataDto<AccountDTO> resultDataDto = new ResultDataDto<AccountDTO>();
		logger.info("accountService.getById,id="+id);
		//todo impl code
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<AccountDTO>> getAll() {
		ResultDataDto<List<AccountDTO>> resultDataDto = new ResultDataDto<List<AccountDTO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(AccountDTO dto) {
		ResultDto resultDto = new ResultDto();
		//todo impl code
		return resultDto;
	}

}