package com.lightsnail.app.user.crm.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.coderman.utils.bean.CglibConvertService;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.app.user.crm.core.mapper.AccountMapper;
import com.lightsnail.app.user.crm.core.service.AccountService;
import com.lightsnail.app.user.crm.core.vo.AccountVO;
import com.lightsnail.app.user.crm.core.entity.AccountEntity;

import java.util.List;


/**
 * @Description:合作用户表Service接口实现类
 * @Author：shenshuai
 * @CreateTime：2020-11-17 00:02:53
 * @version v1.0
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService{


	@Resource
	private AccountMapper accountMapper;

    @Autowired
    private CglibConvertService cglibConvertService;

	@Override
	public ResultDto save(AccountVO accountVo)  throws Exception{
		ResultDto resultDto = new ResultDto();
        AccountEntity accountEntity = cglibConvertService.copyPropertity(AccountEntity.class,accountVo);
        accountMapper.insert(accountEntity);
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		log.info("accountService.delete,id="+id);
       accountMapper.deleteById(id);
		return resultDto;
	}
	
	@Override
	public ResultDataDto<AccountVO> getById(Long id)  throws Exception {
		ResultDataDto<AccountVO> resultDataDto = new ResultDataDto<AccountVO>();
		AccountEntity accountEntity = accountMapper.getById(id);
		AccountVO accountVo = cglibConvertService.copyPropertity(AccountVO.class,accountEntity);
		resultDataDto.setData(accountVo);
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<AccountVO>> getAll()  throws Exception {
		ResultDataDto<List<AccountVO>> resultDataDto = new ResultDataDto<List<AccountVO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(AccountVO accountVo)  throws Exception {
		ResultDto resultDto = new ResultDto();
		AccountEntity accountEntity = cglibConvertService.copyPropertity(AccountEntity.class,accountVo);
		accountMapper.update(accountEntity);
		return resultDto;
	}

}