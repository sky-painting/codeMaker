package com.coderman.infosys.auth.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.infosys.auth.service.AdminAuthorityService;
import com.coderman.infosys.auth.vo.AdminAuthorityVO;
import com.coderman.infosys.auth.Application;

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
* @Description:行政数据权限单元测试
* @Author:shenshuai
* @CreateTime:2021-12-24 23:46:19
* @version v1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {Application.class})
public class AdminAuthorityServiceTest{
	
	protected Logger logger = LoggerFactory.getLogger(AdminAuthorityServiceTest.class);

	@Autowired
	private AdminAuthorityService adminAuthorityService;
    @Autowired
    private TestRestTemplate restTemplate;

	/**
     * @Description:测试新增行政数据权限 接口
     */
    @Test
    public void testAdd(){
        //todo test code
		AdminAuthorityVO vo = new AdminAuthorityVO();

		ResultDto resultDto = restTemplate.postForEntity("/adminAuthority/add",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
    }

	/**
	 * @Description:测试修改行政数据权限
	 */
	@Test
	public void testUpdate(){
		//todo test code
		AdminAuthorityVO vo = new AdminAuthorityVO();
		ResultDto resultDto = restTemplate.postForEntity("/adminAuthority/update",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:测试删除根据id删除行政数据权限
	 */
    @Test
	public void testDelete(){
		//todo test code
		long id = 1L;
		ResultDto resultDto = restTemplate.postForEntity("/adminAuthority/delete",id, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:但是根据ID获取行政数据权限单条记录
	 */
	@Test
	public void testGetById(){
		//todo test code
		ResultDataDto resultDataDto = restTemplate.getForEntity("/adminAuthority/get?id=1",ResultDataDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDataDto));
	}

	/**
	 * @Description:测试分页获取行政数据权限记录
	 */
	@Test
	public void getPage(){
		//todo test code
	}

	/**
	 * @Description:测试禁用行政数据权限状态
	 */
	@Test
	public void testDisable(){
		//todo test code
	}
	/**
	 * @Description:测试启用行政数据权限状态
	 */
	@Test
	public void testEnable(){
		//todo test code
	}
}
