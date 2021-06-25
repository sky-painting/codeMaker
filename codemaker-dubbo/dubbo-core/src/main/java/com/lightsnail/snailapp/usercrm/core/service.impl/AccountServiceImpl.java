package com.lightsnail.schoolmanager.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lightsnail.schoolmanager.common.mapper.AccountMapper;
import com.lightsnail.schoolmanager.core.service.AccountService;
import com.lightsnail.schoolmanager.core.model.bo.AccountBO;

import java.util.List;


/**
 * @Description:合作用户表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService{


	@Resource
	private AccountMapper accountMapper;


	@Override
	public AccountBO save(AccountBO accountBO)  throws Exception{
		return accountBO;
	}
	
	@Override
	public Boolean delete(Long id) {
		return false;
	}
	
	@Override
	public AccountBO getById(Long id)  throws Exception {
		return null;
	}
	
	@Override
	public List<AccountBO> getAll()  throws Exception {
		return null;
	}

	@Override
	public AccountBO update(AccountBO accountBO)  throws Exception {
		return accountBO;
	}

}