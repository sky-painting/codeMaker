package com.coderman.infosys.auth.core.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.infosys.auth.api.facade.AdminAuthorityFacade;
import com.coderman.infosys.auth.api.dto.AdminAuthorityDTO;

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
public class AdminAuthorityFacadeTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	protected Logger logger = LoggerFactory.getLogger(AdminAuthorityFacadeTest.class);

	@Reference(url = "")
	private AdminAuthorityFacade adminAuthorityFacade;


	/**
     * @Description:测试新增行政数据权限 接口
     */
    @Test
    public void testAdd(){
        //todo test code
		AdminAuthorityDTO dto = new AdminAuthorityDTO();


    }

	/**
	 * @Description:测试修改行政数据权限
	 */
	@Test
	public void testUpdate(){
		//todo test code
		AdminAuthorityDTO vo = new AdminAuthorityDTO();
	}

	/**
	 * @Description:测试删除根据id删除行政数据权限
	 */
    @Test
	public void testDelete(){
		//todo test code
		long id = 1L;
	}

	/**
	 * @Description:根据ID获取行政数据权限单条记录
	 */
	@Test
	public void testGetById(){
		//todo test code
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
