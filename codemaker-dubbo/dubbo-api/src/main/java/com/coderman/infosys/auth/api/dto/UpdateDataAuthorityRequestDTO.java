package com.coderman.infosys.auth.api.dto;


import lombok.Data;
import lombok.ToString;

/**
* @Description:数据字段权限类
* @Author:
* @CreateTime:2021-11-07 08:18:28
* @version v1.0
*/
@Data
@ToString
public class UpdateDataAuthorityRequestDTO {

	/** 主键ID **/
	private Long id;
	/** 权限编码标示 **/
	private String authCode;
	/** 数据字段ID **/
	private Long dataColumnId;


}