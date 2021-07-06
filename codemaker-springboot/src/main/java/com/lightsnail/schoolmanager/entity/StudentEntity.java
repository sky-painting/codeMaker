package com.lightsnail.schoolmanager.entity;

import java.util.Date;
import java.math.BigDecimal;

 /**
 * @Description:Entity类
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 16:08:54
 * @version v1.0
 */
public class StudentEntity{

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
	public String getStudentName() {
		return studentName;
	}
	/**
	 * 描述：
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * 描述：
	 */
	public String getStudentCode() {
		return studentCode;
	}
	/**
	 * 描述：
	 */
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	/**
	 * 描述：
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 描述：
	 */
	public void setSex(String sex) {
		this.sex = sex;
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
	 * 描述：班级ID
	 */
	public Long getClassId() {
		return classId;
	}
	/**
	 * 描述：班级ID
	 */
	public void setClassId(Long classId) {
		this.classId = classId;
	}
}