package com.lightsnail.app.user.crm.core.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lightsnail.app.user.crm.core.service.LandlordRoomRentRecordService;
import com.lightsnail.app.user.crm.core.vo.LandlordRoomRentRecordVO;
import com.lightsnail.app.user.crm.core.Application;

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
* @Description:房源出租记录表单元测试
* @Author:shenshuai
* @CreateTime:2020-11-17 00:02:54
* @version v1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {Application.class})
public class LandlordRoomRentRecordServiceTest{
	
	protected Logger logger = LoggerFactory.getLogger(LandlordRoomRentRecordServiceTest.class);

	@Autowired
	private LandlordRoomRentRecordService landlordRoomRentRecordService;
    @Autowired
    private TestRestTemplate restTemplate;

	/**
     * @Description:测试新增房源出租记录表 接口
     */
    @Test
    public void testAdd(){
        //todo test code
		LandlordRoomRentRecordVO vo = new LandlordRoomRentRecordVO();

		ResultDto resultDto = restTemplate.postForEntity("/landlordRoomRentRecord/add",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
    }

	/**
	 * @Description:测试修改房源出租记录表
	 */
	@Test
	public void testUpdate(){
		//todo test code
		LandlordRoomRentRecordVO vo = new LandlordRoomRentRecordVO();
		ResultDto resultDto = restTemplate.postForEntity("/landlordRoomRentRecord/update",vo, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:测试删除根据id删除房源出租记录表
	 */
    @Test
	public void testDelete(){
		//todo test code
		long id = 1L;
		ResultDto resultDto = restTemplate.postForEntity("/landlordRoomRentRecord/delete",id, ResultDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDto));
	}

	/**
	 * @Description:但是根据ID获取房源出租记录表单条记录
	 */
	@Test
	public void testGetById(){
		//todo test code
		ResultDataDto resultDataDto = restTemplate.getForEntity("/landlordRoomRentRecord/get?id=1",ResultDataDto.class).getBody();
		System.out.println(JSON.toJSONString(resultDataDto));
	}

	/**
	 * @Description:测试分页获取房源出租记录表记录
	 */
	@Test
	public void getPage(){
		//todo test code
	}

	/**
	 * @Description:测试禁用房源出租记录表状态
	 */
	@Test
	public void testDisable(){
		//todo test code
	}
	/**
	 * @Description:测试启用房源出租记录表状态
	 */
	@Test
	public void testEnable(){
		//todo test code
	}
}
