package com.lightsnail.snailapp.usercrm.domain.bo;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:行政权限类
 * @Author:fanchunshuai
 * @CreateTime:2021-07-07 00:05:10
 * @version v1.0
 */
@Data
@ToString
public class AdminAuthorityBO  extends  AuthorityBO{

	/** 组织部门 **/
    private Long departmentId;
	/** 城市 **/
    private Long cityId;
	/** 职位 **/
    private Long jobId;


}