package com.coderman.infosys.auth.infrast.dao.dataobject;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:用户组DO类
 * @Author:shenshuai
 * @CreateTime:2021-11-06 23:38:07
 * @version v1.0
 */
@Data
@ToString
public class UserGroupDO{


	/**  主键 **/
	private Long id;

	/** 用户组名 **/
	private String groupName;

	/** 用户组编码 **/
	private String groupCode;

	/**  用户组状态 **/
	private Long status;

	/** 用户组内用户列表 **/
	private String userList;

	/** 创建时间 **/
	private Date dateCreate;

	/** 修改时间 **/
	private Date dateUpdate;

	/** 修改人 **/
	private Long updateUserId;

	/** 创建人 **/
	private Long createUserId;

}