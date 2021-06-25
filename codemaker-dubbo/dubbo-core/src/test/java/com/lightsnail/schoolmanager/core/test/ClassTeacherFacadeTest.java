package com.lightsnail.schoolmanager.core.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lightsnail.schoolmanager.api.facade.ClassTeacherFacade;
import com.lightsnail.schoolmanager.api.dto.ClassTeacherDTO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.lightsnail.schoolmanager.core.Application;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
* @Description:单元测试
* @Author:fanchunshuai
* @CreateTime:2021-06-25 09:19:38
* @version v1.0
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Rollback
@Transactional
public class ClassTeacherFacadeTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	protected Logger logger = LoggerFactory.getLogger(ClassTeacherFacadeTest.class);

	@Reference(url = "")
	private ClassTeacherFacade classTeacherFacade;


	/**
     * @Description:测试新增 接口
     */
    @Test
    public void testAdd(){
        //todo test code
		ClassTeacherDTO dto = new ClassTeacherDTO();


    }

	/**
	 * @Description:测试修改
	 */
	@Test
	public void testUpdate(){
		//todo test code
		ClassTeacherDTO vo = new ClassTeacherDTO();
	}

	/**
	 * @Description:测试删除根据id删除
	 */
    @Test
	public void testDelete(){
		//todo test code
		long id = 1L;
	}

	/**
	 * @Description:根据ID获取单条记录
	 */
	@Test
	public void testGetById(){
		//todo test code
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
