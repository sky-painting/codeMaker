package com.lightsnail.schoolmanager.api.dto;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

/**
* @Description:DTO类
* @Author:fanchunshuai
* @CreateTime:2021-06-25 16:19:52
* @version v1.0
*/
@Data
@ToString
public class ClassDTO{

	/**  **/
	private Long id;
	/**  **/
	private String manager;
	/**  **/
	private Integer studentCount;
	/** 年级 **/
	private Integer gradeNum;
	/** 班级 **/
	private Integer classNum;

}