package com.lightsnail.schoolmanager.core.model.bo;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:BO类
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 16:19:51
 * @version v1.0
 */
@Data
@ToString
public class TeacherBO{


	/**  **/
	private Long id;

	/**  **/
	private String teacherName;

	/**  **/
	private Integer age;

	/**  **/
	private Integer sex;

}