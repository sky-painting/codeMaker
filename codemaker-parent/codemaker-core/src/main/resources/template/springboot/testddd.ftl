package ${package}.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
<#list imports as importClass>
import ${importClass};
</#list>

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
 * @Author:${author}
 * @CreateTime:2021-06-25 09:19:38
 * @version v1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Rollback
@Transactional
public class ${class.className}Test extends AbstractTransactionalJUnit4SpringContextTests{
	
	protected Logger logger = LoggerFactory.getLogger(${class.className}Test.class);

	@Reference(url = "")
	private ${class.className} ${class.className};


<#list methods as method>

	@Test
	public void ${method.simpleMethodNameInfo}Test(){
		//todo test
	}
</#list>

}
