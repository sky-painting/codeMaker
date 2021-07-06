package com.lightsnail.schoolmanager.common.dataobject;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:DO类
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 16:19:51
 * @version v1.0
 */
@Data
@ToString
public class StudentDO{


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