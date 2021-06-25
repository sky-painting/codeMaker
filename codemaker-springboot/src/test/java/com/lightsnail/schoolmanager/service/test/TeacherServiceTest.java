package com.lightsnail.schoolmanager.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lightsnail.schoolmanager.service.TeacherService;
import com.lightsnail.schoolmanager.vo.TeacherVO;
import com.lightsnail.schoolmanager.Application;

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
* @Description:单元测试
* @Author:fanchunshuai
* @CreateTime:2021-06-25 16:08:54
* @version v1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {Application.class})
public class TeacherServiceTest{
	
	protected Logger logger = LoggerFactory.getLogger(TeacherServiceTest.class);

	@Autowired
	private TeacherService teacherService;
    @Autowired
    private TestRestTemplate restTemplate;

	/**
     * @Description:测试新增 接口
     */
    @Test
    public void testAdd(){
        //todo test code
		TeacherVO vo = new TeacherVO();

		ResultDto resultDto = restTemplate.postForEntity("/teacher/add",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
    }

	/**
	 * @Description:测试修改
	 */
	@Test
	public void testUpdate(){
		//todo test code
		TeacherVO vo = new TeacherVO();
		ResultDto resultDto = restTemplate.postForEntity("/teacher/update",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:测试删除根据id删除
	 */
    @Test
	public void testDelete(){
		//todo test code
		long id = 1L;
		ResultDto resultDto = restTemplate.postForEntity("/teacher/delete",id, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:但是根据ID获取单条记录
	 */
	@Test
	public void testGetById(){
		//todo test code
		ResultDataDto resultDataDto = restTemplate.getForEntity("/teacher/get?id=1",ResultDataDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDataDto));
	}

	/**
	 * @Description:测试分页获取记录
	 */
	@Test
	public void getPage(){
		//todo test code
	}

	/**
	 * @Description:测试禁用状态
	 */
	@Test
	public void testDisable(){
		//todo test code
	}
	/**
	 * @Description:测试启用状态
	 */
	@Test
	public void testEnable(){
		//todo test code
	}
}
