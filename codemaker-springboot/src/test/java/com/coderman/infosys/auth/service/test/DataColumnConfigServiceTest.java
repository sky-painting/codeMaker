package com.coderman.infosys.auth.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.infosys.auth.service.DataColumnConfigService;
import com.coderman.infosys.auth.vo.DataColumnConfigVO;
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
* @Description:数据字段管理表单元测试
* @Author:shenshuai
* @CreateTime:2021-12-24 23:46:19
* @version v1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {Application.class})
public class DataColumnConfigServiceTest{
	
	protected Logger logger = LoggerFactory.getLogger(DataColumnConfigServiceTest.class);

	@Autowired
	private DataColumnConfigService dataColumnConfigService;
    @Autowired
    private TestRestTemplate restTemplate;

	/**
     * @Description:测试新增数据字段管理表 接口
     */
    @Test
    public void testAdd(){
        //todo test code
		DataColumnConfigVO vo = new DataColumnConfigVO();

		ResultDto resultDto = restTemplate.postForEntity("/dataColumnConfig/add",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
    }

	/**
	 * @Description:测试修改数据字段管理表
	 */
	@Test
	public void testUpdate(){
		//todo test code
		DataColumnConfigVO vo = new DataColumnConfigVO();
		ResultDto resultDto = restTemplate.postForEntity("/dataColumnConfig/update",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:测试删除根据id删除数据字段管理表
	 */
    @Test
	public void testDelete(){
		//todo test code
		long id = 1L;
		ResultDto resultDto = restTemplate.postForEntity("/dataColumnConfig/delete",id, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:但是根据ID获取数据字段管理表单条记录
	 */
	@Test
	public void testGetById(){
		//todo test code
		ResultDataDto resultDataDto = restTemplate.getForEntity("/dataColumnConfig/get?id=1",ResultDataDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDataDto));
	}

	/**
	 * @Description:测试分页获取数据字段管理表记录
	 */
	@Test
	public void getPage(){
		//todo test code
	}

	/**
	 * @Description:测试禁用数据字段管理表状态
	 */
	@Test
	public void testDisable(){
		//todo test code
	}
	/**
	 * @Description:测试启用数据字段管理表状态
	 */
	@Test
	public void testEnable(){
		//todo test code
	}
}
