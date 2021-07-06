package com.lightsnail.schoolmanager.core.model.bo;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:BO类
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 16:19:52
 * @version v1.0
 */
@Data
@ToString
public class ClassBO{


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