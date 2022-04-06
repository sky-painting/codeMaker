package com.coderman.infosys.auth.core.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.infosys.auth.api.facade.UserGroupFacade;
import com.coderman.infosys.auth.api.dto.UserGroupDTO;

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
* @Author:shenshuai
* @CreateTime:2021-06-25 09:19:38
* @version v1.0
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Rollback
@Transactional
public class UserGroupFacadeTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	protected Logger logger = LoggerFactory.getLogger(UserGroupFacadeTest.class);

	@Reference(url = "")
	private UserGroupFacade userGroupFacade;


	/**
     * @Description:测试新增用户组 接口
     */
    @Test
    public void testAdd(){
        //todo test code
		UserGroupDTO dto = new UserGroupDTO();


    }

	/**
	 * @Description:测试修改用户组
	 */
	@Test
	public void testUpdate(){
		//todo test code
		UserGroupDTO vo = new UserGroupDTO();
	}

	/**
	 * @Description:测试删除根据id删除用户组
	 */
    @Test
	public void testDelete(){
		//todo test code
		long id = 1L;
	}

	/**
	 * @Description:根据ID获取用户组单条记录
	 */
	@Test
	public void testGetById(){
		//todo test code
	}

	/**
	 * @Description:测试分页获取用户组记录
	 */
	@Test
	public void getPage(){
		//todo test code
	}

	/**
	 * @Description:测试禁用用户组状态
	 */
	@Test
	public void testDisable(){
		//todo test code
	}
	/**
	 * @Description:测试启用用户组状态
	 */
	@Test
	public void testEnable(){
		//todo test code
	}
}
