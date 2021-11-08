package com.coderman.infosys.auth.core.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderman.infosys.auth.api.facade.ModuleFacade;
import com.coderman.infosys.auth.api.dto.ModuleDTO;

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
public class ModuleFacadeTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	protected Logger logger = LoggerFactory.getLogger(ModuleFacadeTest.class);

	@Reference(url = "")
	private ModuleFacade moduleFacade;


	/**
     * @Description:测试新增菜单模块表 接口
     */
    @Test
    public void testAdd(){
        //todo test code
		ModuleDTO dto = new ModuleDTO();


    }

	/**
	 * @Description:测试修改菜单模块表
	 */
	@Test
	public void testUpdate(){
		//todo test code
		ModuleDTO vo = new ModuleDTO();
	}

	/**
	 * @Description:测试删除根据id删除菜单模块表
	 */
    @Test
	public void testDelete(){
		//todo test code
		long id = 1L;
	}

	/**
	 * @Description:根据ID获取菜单模块表单条记录
	 */
	@Test
	public void testGetById(){
		//todo test code
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
