package ${package}.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.api.facade.${table.humpClassName}Facade;
import ${package}.api.dto.${table.humpClassName}DTO;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.utils.response.ResultDto;

import ${package}.Application;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
public class ${table.humpClassName}FacadeTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	protected Logger logger = LoggerFactory.getLogger(${table.humpClassName}FacadeTest.class);

	@Reference(url = "")
	private ${table.humpClassName}Facade ${table.humpTableName}Facade;


	/**
     * @Description:测试新增${table.tableComment} 接口
     */
    @Test
    public void testAdd(){
        //todo test code
		${table.humpClassName}DTO dto = new ${table.humpClassName}DTO();


    }

	/**
	 * @Description:测试修改${table.tableComment}
	 */
	@Test
	public void testUpdate(){
		//todo test code
		${table.humpClassName}DTO vo = new ${table.humpClassName}DTO();
	}

	/**
	 * @Description:测试删除根据id删除${table.tableComment}
	 */
    @Test
	public void testDelete(){
		//todo test code
		long id = 1L;
	}

	/**
	 * @Description:根据ID获取${table.tableComment}单条记录
	 */
	@Test
	public void testGetById(){
		//todo test code
	}

	/**
	 * @Description:测试分页获取${table.tableComment}记录
	 */
	@Test
	public void getPage(){
		//todo test code
	}

	/**
	 * @Description:测试禁用${table.tableComment}状态
	 */
	@Test
	public void testDisable(){
		//todo test code
	}
	/**
	 * @Description:测试启用${table.tableComment}状态
	 */
	@Test
	public void testEnable(){
		//todo test code
	}
}
