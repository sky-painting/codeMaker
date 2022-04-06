package com.lightsnail.app.user.crm.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.coderman.utils.bean.CglibConvertService;
import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.app.user.crm.core.mapper.AttractInvestmentMapper;
import com.lightsnail.app.user.crm.core.service.AttractInvestmentService;
import com.lightsnail.app.user.crm.core.vo.AttractInvestmentVO;
import com.lightsnail.app.user.crm.core.entity.AttractInvestmentEntity;

import java.util.List;


/**
 * @Description:招商服务注册表Service接口实现类
 * @Author：shenshuai
 * @CreateTime：2020-11-17 00:02:53
 * @version v1.0
 */
@Service
@Slf4j
public class AttractInvestmentServiceImpl implements AttractInvestmentService{


	@Resource
	private AttractInvestmentMapper attractInvestmentMapper;

    @Autowired
    private CglibConvertService cglibConvertService;

	@Override
	public ResultDto save(AttractInvestmentVO attractInvestmentVo)  throws Exception{
		ResultDto resultDto = new ResultDto();
        AttractInvestmentEntity attractInvestmentEntity = cglibConvertService.copyPropertity(AttractInvestmentEntity.class,attractInvestmentVo);
        attractInvestmentMapper.insert(attractInvestmentEntity);
		return resultDto;
	}
	
	@Override
	public ResultDto delete(Long id) {
		ResultDto resultDto = new ResultDto();
		log.info("attractInvestmentService.delete,id="+id);
       attractInvestmentMapper.deleteById(id);
		return resultDto;
	}
	
	@Override
	public ResultDataDto<AttractInvestmentVO> getById(Long id)  throws Exception {
		ResultDataDto<AttractInvestmentVO> resultDataDto = new ResultDataDto<AttractInvestmentVO>();
		AttractInvestmentEntity attractInvestmentEntity = attractInvestmentMapper.getById(id);
		AttractInvestmentVO attractInvestmentVo = cglibConvertService.copyPropertity(AttractInvestmentVO.class,attractInvestmentEntity);
		resultDataDto.setData(attractInvestmentVo);
		return resultDataDto;
	}
	
	@Override
	public ResultDataDto<List<AttractInvestmentVO>> getAll()  throws Exception {
		ResultDataDto<List<AttractInvestmentVO>> resultDataDto = new ResultDataDto<List<AttractInvestmentVO>>();
		//todo impl code
		return	resultDataDto;
	}

	@Override
	public ResultDto update(AttractInvestmentVO attractInvestmentVo)  throws Exception {
		ResultDto resultDto = new ResultDto();
		AttractInvestmentEntity attractInvestmentEntity = cglibConvertService.copyPropertity(AttractInvestmentEntity.class,attractInvestmentVo);
		attractInvestmentMapper.update(attractInvestmentEntity);
		return resultDto;
	}

}