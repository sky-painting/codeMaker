package com.coderman.infosys.auth.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.infosys.auth.service.ModuleService;
import com.coderman.infosys.auth.vo.ModuleVO;
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
* @Description:菜单模块表单元测试
* @Author:shenshuai
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {Application.class})
public class ModuleServiceTest{
	
	protected Logger logger = LoggerFactory.getLogger(ModuleServiceTest.class);

	@Autowired
	private ModuleService moduleService;
    @Autowired
    private TestRestTemplate restTemplate;

	/**
     * @Description:测试新增菜单模块表 接口
     */
    @Test
    public void testAdd(){
        //todo test code
		ModuleVO vo = new ModuleVO();

		ResultDto resultDto = restTemplate.postForEntity("/module/add",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
    }

	/**
	 * @Description:测试修改菜单模块表
	 */
	@Test
	public void testUpdate(){
		//todo test code
		ModuleVO vo = new ModuleVO();
		ResultDto resultDto = restTemplate.postForEntity("/module/update",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:测试删除根据id删除菜单模块表
	 */
    @Test
	public void testDelete(){
		//todo test code
		long id = 1L;
		ResultDto resultDto = restTemplate.postForEntity("/module/delete",id, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:但是根据ID获取菜单模块表单条记录
	 */
	@Test
	public void testGetById(){
		//todo test code
		ResultDataDto resultDataDto = restTemplate.getForEntity("/module/get?id=1",ResultDataDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDataDto));
	}

	/**
	 * @Description:测试分页获取菜单模块表记录
	 */
	@Test
	public void getPage(){
		//todo test code
	}

	/**
	 * @Description:测试禁用菜单模块表状态
	 */
	@Test
	public void testDisable(){
		//todo test code
	}
	/**
	 * @Description:测试启用菜单模块表状态
	 */
	@Test
	public void testEnable(){
		//todo test code
	}
}
