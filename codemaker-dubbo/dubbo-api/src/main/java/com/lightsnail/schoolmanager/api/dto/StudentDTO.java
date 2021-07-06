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
public class StudentDTO{

	/**  **/
	private Long id;
	/**  **/
	private String studentName;
	/**  **/
	private String studentCode;
	/**  **/
	private String sex;
	/**  **/
	private Integer age;
	/** 班级ID **/
	private Long classId;

}