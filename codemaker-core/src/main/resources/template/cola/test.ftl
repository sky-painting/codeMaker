package ${package}.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${package}.service.${table.humpClassName}Service;
import ${package}.vo.${table.humpClassName}VO;
import ${package}.Application;

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
* @Description:${table.tableComment}单元测试
* @Author:${author}
* @CreateTime:${.now?string('yyyy-MM-dd HH:mm:ss')}
* @version v1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {Application.class})
public class ${table.humpClassName}ServiceTest{
	
	protected Logger logger = LoggerFactory.getLogger(${table.humpClassName}ServiceTest.class);

	@Autowired
	private ${table.humpClassName}Service ${table.humpTableName}Service;
    @Autowired
    private TestRestTemplate restTemplate;

	/**
     * @Description:测试新增${table.tableComment} 接口
     */
    @Test
    public void testAdd(){
        //todo test code
		${table.humpClassName}VO vo = new ${table.humpClassName}VO();

		ResultDto resultDto = restTemplate.postForEntity("/${table.humpTableName}/add",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
    }

	/**
	 * @Description:测试修改${table.tableComment}
	 */
	@Test
	public void testUpdate(){
		//todo test code
		${table.humpClassName}VO vo = new ${table.humpClassName}VO();
		ResultDto resultDto = restTemplate.postForEntity("/${table.humpTableName}/update",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:测试删除根据id删除${table.tableComment}
	 */
    @Test
	public void testDelete(){
		//todo test code
		long id = 1L;
		ResultDto resultDto = restTemplate.postForEntity("/${table.humpTableName}/delete",id, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:但是根据ID获取${table.tableComment}单条记录
	 */
	@Test
	public void testGetById(){
		//todo test code
		ResultDataDto resultDataDto = restTemplate.getForEntity("/${table.humpTableName}/get?id=1",ResultDataDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDataDto));
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
