package com.lightsnail.app.user.crm.core.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lightsnail.app.user.crm.core.service.LandlordContractTemplateService;
import com.lightsnail.app.user.crm.core.vo.LandlordContractTemplateVO;
import com.lightsnail.app.user.crm.core.Application;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
* @Description:房东合同模板表单元测试
* @Author:shenshuai
* @CreateTime:2020-11-17 00:02:53
* @version v1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {Application.class})
public class LandlordContractTemplateServiceTest{
	
	protected Logger logger = LoggerFactory.getLogger(LandlordContractTemplateServiceTest.class);

	@Autowired
	private LandlordContractTemplateService landlordContractTemplateService;
    @Autowired
    private TestRestTemplate restTemplate;

	/**
     * @Description:测试新增房东合同模板表 接口
     */
    @Test
    public void testAdd(){
        //todo test code
		LandlordContractTemplateVO vo = new LandlordContractTemplateVO();

		ResultDto resultDto = restTemplate.postForEntity("/landlordContractTemplate/add",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
    }

	/**
	 * @Description:测试修改房东合同模板表
	 */
	@Test
	public void testUpdate(){
		//todo test code
		LandlordContractTemplateVO vo = new LandlordContractTemplateVO();
		ResultDto resultDto = restTemplate.postForEntity("/landlordContractTemplate/update",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:测试删除根据id删除房东合同模板表
	 */
    @Test
	public void testDelete(){
		//todo test code
		long id = 1L;
		ResultDto resultDto = restTemplate.postForEntity("/landlordContractTemplate/delete",id, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:但是根据ID获取房东合同模板表单条记录
	 */
	@Test
	public void testGetById(){
		//todo test code
		ResultDataDto resultDataDto = restTemplate.getForEntity("/landlordContractTemplate/get?id=1",ResultDataDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDataDto));
	}

	/**
	 * @Description:测试分页获取房东合同模板表记录
	 */
	@Test
	public void getPage(){
		//todo test code
	}

	/**
	 * @Description:测试禁用房东合同模板表状态
	 */
	@Test
	public void testDisable(){
		//todo test code
	}
	/**
	 * @Description:测试启用房东合同模板表状态
	 */
	@Test
	public void testEnable(){
		//todo test code
	}
}
