package com.lightsnail.snailapp.usercrm.domain.bo;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:数据权限类
 * @Author:fanchunshuai
 * @CreateTime:2021-07-07 00:05:10
 * @version v1.0
 */
@Data
@ToString
public class DataAuthorityBO  extends AuthorityBO {

	/** 系统标示 **/
    private String systemName;
	/** 表名 **/
    private String tableName;
	/** 字段名 **/
    private String columnName;


}