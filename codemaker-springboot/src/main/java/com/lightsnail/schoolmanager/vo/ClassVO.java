package com.lightsnail.schoolmanager.vo;

import java.util.Date;
import java.math.BigDecimal;

/**
* @Description:VO类
* @Author:fanchunshuai
* @CreateTime:2021-06-25 16:08:54
* @version v1.0
*/
public class ClassVO{

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
	public String getManager() {
		return manager;
	}
	/**
	* 描述：
	*/
	public void setManager(String manager) {
		this.manager = manager;
	}
	/**
	* 描述：
	*/
	public Integer getStudentCount() {
		return studentCount;
	}
	/**
	* 描述：
	*/
	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}
	/**
	* 描述：年级
	*/
	public Integer getGradeNum() {
		return gradeNum;
	}
	/**
	* 描述：年级
	*/
	public void setGradeNum(Integer gradeNum) {
		this.gradeNum = gradeNum;
	}
	/**
	* 描述：班级
	*/
	public Integer getClassNum() {
		return classNum;
	}
	/**
	* 描述：班级
	*/
	public void setClassNum(Integer classNum) {
		this.classNum = classNum;
	}
}