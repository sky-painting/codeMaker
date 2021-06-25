package com.lightsnail.schoolmanager.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lightsnail.schoolmanager.common.mapper.LandlordContractTemplateMapper;
import com.lightsnail.schoolmanager.core.service.LandlordContractTemplateService;
import com.lightsnail.schoolmanager.core.model.bo.LandlordContractTemplateBO;

import java.util.List;


/**
 * @Description:房东合同模板表Service接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-06-25 23:24:09
 * @version v1.0
 */
@Service
@Slf4j
public class LandlordContractTemplateServiceImpl implements LandlordContractTemplateService{


	@Resource
	private LandlordContractTemplateMapper landlordContractTemplateMapper;


	@Override
	public LandlordContractTemplateBO save(LandlordContractTemplateBO landlordContractTemplateBO)  throws Exception{
		return landlordContractTemplateBO;
	}
	
	@Override
	public Boolean delete(Long id) {
		return false;
	}
	
	@Override
	public LandlordContractTemplateBO getById(Long id)  throws Exception {
		return null;
	}
	
	@Override
	public List<LandlordContractTemplateBO> getAll()  throws Exception {
		return null;
	}

	@Override
	public LandlordContractTemplateBO update(LandlordContractTemplateBO landlordContractTemplateBO)  throws Exception {
		return landlordContractTemplateBO;
	}

}