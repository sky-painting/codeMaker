package com.coderman.infosys.auth.domain.bo;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:系统设置类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:18:27
 * @version v1.0
 */
@Data
@ToString
public class SystemConfig {

	/** 变量名 **/
    private String varName;
	/** 变量描述 **/
    private String varDesc;
	/** 变量值 **/
    private String value;


}