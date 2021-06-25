package com.lightsnail.schoolmanager.vo;

import java.util.Date;
import java.math.BigDecimal;

/**
* @Description:VO类
* @Author:fanchunshuai
* @CreateTime:2021-06-25 16:08:54
* @version v1.0
*/
public class TeacherVO{

	/**  **/
	private Long id;
	/**  **/
	private String teacherName;
	/**  **/
	private Integer age;
	/**  **/
	private Integer sex;

	/**
	* 描述：
	*/
	public Long getId() {
		return id;
	}
	/**
	* 描述：
	*/
	public void setId(Long id) {
		this.id = id;
	}
	/**
	* 描述：
	*/
	public String getTeacherName() {
		return teacherName;
	}
	/**
	* 描述：
	*/
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	/**
	* 描述：
	*/
	public Integer getAge() {
		return age;
	}
	/**
	* 描述：
	*/
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	* 描述：
	*/
	public Integer getSex() {
		return sex;
	}
	/**
	* 描述：
	*/
	public void setSex(Integer sex) {
		this.sex = sex;
	}
}