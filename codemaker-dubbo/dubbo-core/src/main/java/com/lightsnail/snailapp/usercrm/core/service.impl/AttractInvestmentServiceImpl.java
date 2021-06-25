package com.lightsnail.schoolmanager.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lightsnail.schoolmanager.common.mapper.AttractInvestmentMapper;
import com.lightsnail.schoolmanager.core.service.AttractInvestmentService;
import com.lightsnail.schoolmanager.core.model.bo.AttractInvestmentBO;

import java.util.List;


/**
 * @Description:招商服务注册表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
@Slf4j
public class AttractInvestmentServiceImpl implements AttractInvestmentService{


	@Resource
	private AttractInvestmentMapper attractInvestmentMapper;


	@Override
	public AttractInvestmentBO save(AttractInvestmentBO attractInvestmentBO)  throws Exception{
		return attractInvestmentBO;
	}
	
	@Override
	public Boolean delete(Long id) {
		return false;
	}
	
	@Override
	public AttractInvestmentBO getById(Long id)  throws Exception {
		return null;
	}
	
	@Override
	public List<AttractInvestmentBO> getAll()  throws Exception {
		return null;
	}

	@Override
	public AttractInvestmentBO update(AttractInvestmentBO attractInvestmentBO)  throws Exception {
		return attractInvestmentBO;
	}

}