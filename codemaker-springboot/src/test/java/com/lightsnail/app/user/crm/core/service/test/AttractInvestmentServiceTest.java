package com.lightsnail.app.user.crm.core.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lightsnail.app.user.crm.core.service.AttractInvestmentService;
import com.lightsnail.app.user.crm.core.vo.AttractInvestmentVO;
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
* @Description:招商服务注册表单元测试
* @Author:fanchunshuai
* @CreateTime:2020-11-17 00:02:53
* @version v1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {Application.class})
public class AttractInvestmentServiceTest{
	
	protected Logger logger = LoggerFactory.getLogger(AttractInvestmentServiceTest.class);

	@Autowired
	private AttractInvestmentService attractInvestmentService;
    @Autowired
    private TestRestTemplate restTemplate;

	/**
     * @Description:测试新增招商服务注册表 接口
     */
    @Test
    public void testAdd(){
        //todo test code
		AttractInvestmentVO vo = new AttractInvestmentVO();

		ResultDto resultDto = restTemplate.postForEntity("/attractInvestment/add",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
    }

	/**
	 * @Description:测试修改招商服务注册表
	 */
	@Test
	public void testUpdate(){
		//todo test code
		AttractInvestmentVO vo = new AttractInvestmentVO();
		ResultDto resultDto = restTemplate.postForEntity("/attractInvestment/update",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:测试删除根据id删除招商服务注册表
	 */
    @Test
	public void testDelete(){
		//todo test code
		long id = 1L;
		ResultDto resultDto = restTemplate.postForEntity("/attractInvestment/delete",id, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:但是根据ID获取招商服务注册表单条记录
	 */
	@Test
	public void testGetById(){
		//todo test code
		ResultDataDto resultDataDto = restTemplate.getForEntity("/attractInvestment/get?id=1",ResultDataDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDataDto));
	}

	/**
	 * @Description:测试分页获取招商服务注册表记录
	 */
	@Test
	public void getPage(){
		//todo test code
	}

	/**
	 * @Description:测试禁用招商服务注册表状态
	 */
	@Test
	public void testDisable(){
		//todo test code
	}
	/**
	 * @Description:测试启用招商服务注册表状态
	 */
	@Test
	public void testEnable(){
		//todo test code
	}
}
