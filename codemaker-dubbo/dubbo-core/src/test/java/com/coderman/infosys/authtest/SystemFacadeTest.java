package com.coderman.infosys.auth.core.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.infosys.auth.api.facade.SystemFacade;
import com.coderman.infosys.auth.api.dto.SystemDTO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import com.coderman.infosys.auth.core.Application;

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
public class SystemFacadeTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	protected Logger logger = LoggerFactory.getLogger(SystemFacadeTest.class);

	@Reference(url = "")
	private SystemFacade systemFacade;


	/**
     * @Description:测试新增系统表 接口
     */
    @Test
    public void testAdd(){
        //todo test code
		SystemDTO dto = new SystemDTO();


    }

	/**
	 * @Description:测试修改系统表
	 */
	@Test
	public void testUpdate(){
		//todo test code
		SystemDTO vo = new SystemDTO();
	}

	/**
	 * @Description:测试删除根据id删除系统表
	 */
    @Test
	public void testDelete(){
		//todo test code
		long id = 1L;
	}

	/**
	 * @Description:根据ID获取系统表单条记录
	 */
	@Test
	public void testGetById(){
		//todo test code
	}

	/**
	 * @Description:测试分页获取系统表记录
	 */
	@Test
	public void getPage(){
		//todo test code
	}

	/**
	 * @Description:测试禁用系统表状态
	 */
	@Test
	public void testDisable(){
		//todo test code
	}
	/**
	 * @Description:测试启用系统表状态
	 */
	@Test
	public void testEnable(){
		//todo test code
	}
}
