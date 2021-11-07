package com.coderman.infosys.auth.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.infosys.auth.service.SystemAuthorityService;
import com.coderman.infosys.auth.vo.SystemAuthorityVO;
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
* @Description:系统菜单权限表单元测试
* @Author:shenshuai
* @CreateTime:2021-11-06 23:38:07
* @version v1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {Application.class})
public class SystemAuthorityServiceTest{
	
	protected Logger logger = LoggerFactory.getLogger(SystemAuthorityServiceTest.class);

	@Autowired
	private SystemAuthorityService systemAuthorityService;
    @Autowired
    private TestRestTemplate restTemplate;

	/**
     * @Description:测试新增系统菜单权限表 接口
     */
    @Test
    public void testAdd(){
        //todo test code
		SystemAuthorityVO vo = new SystemAuthorityVO();

		ResultDto resultDto = restTemplate.postForEntity("/systemAuthority/add",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
    }

	/**
	 * @Description:测试修改系统菜单权限表
	 */
	@Test
	public void testUpdate(){
		//todo test code
		SystemAuthorityVO vo = new SystemAuthorityVO();
		ResultDto resultDto = restTemplate.postForEntity("/systemAuthority/update",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:测试删除根据id删除系统菜单权限表
	 */
    @Test
	public void testDelete(){
		//todo test code
		long id = 1L;
		ResultDto resultDto = restTemplate.postForEntity("/systemAuthority/delete",id, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:但是根据ID获取系统菜单权限表单条记录
	 */
	@Test
	public void testGetById(){
		//todo test code
		ResultDataDto resultDataDto = restTemplate.getForEntity("/systemAuthority/get?id=1",ResultDataDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDataDto));
	}

	/**
	 * @Description:测试分页获取系统菜单权限表记录
	 */
	@Test
	public void getPage(){
		//todo test code
	}

	/**
	 * @Description:测试禁用系统菜单权限表状态
	 */
	@Test
	public void testDisable(){
		//todo test code
	}
	/**
	 * @Description:测试启用系统菜单权限表状态
	 */
	@Test
	public void testEnable(){
		//todo test code
	}
}
