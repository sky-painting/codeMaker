package com.lightsnail.schoolmanager.entity;

import java.util.Date;
import java.math.BigDecimal;

 /**
 * @Description:Entity类
 * @Author:fanchunshuai
 * @CreateTime:2021-06-25 16:08:54
 * @version v1.0
 */
public class ClassTeacherEntity{

	/**  **/
	private Long id;
	/**  **/
	private Long classId;
	/**  **/
	private Long teacherId;
	
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
	public Long getClassId() {
		return classId;
	}
	/**
	 * 描述：
	 */
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	/**
	 * 描述：
	 */
	public Long getTeacherId() {
		return teacherId;
	}
	/**
	 * 描述：
	 */
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
}