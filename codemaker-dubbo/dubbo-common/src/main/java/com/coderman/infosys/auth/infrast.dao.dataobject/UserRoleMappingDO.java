package com.coderman.infosys.auth.infrast.dao.dataobject;

import java.util.Date;
import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

 /**
 * @Description:用户角色关联表DO类
 * @Author:shenshuai
 * @CreateTime:2021-12-24 23:17:12
 * @version v1.0
 */
@Data
@ToString
public class UserRoleMappingDO{


	/**  主键 **/
	private Long id;

	/** 用户ID **/
	private Long userId;

	/** 角色code **/
	private String roleCode;

}