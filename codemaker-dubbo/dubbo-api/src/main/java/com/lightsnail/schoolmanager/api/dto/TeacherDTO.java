package com.lightsnail.schoolmanager.api.dto;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

/**
* @Description:DTO类
* @Author:fanchunshuai
* @CreateTime:2021-06-25 16:19:51
* @version v1.0
*/
@Data
@ToString
public class TeacherDTO{

	/**  **/
	private Long id;
	/**  **/
	private String teacherName;
	/**  **/
	private Integer age;
	/**  **/
	private Integer sex;

}