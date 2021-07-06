package com.lightsnail.snailapp.usercrm.domain.bo;


import lombok.Data;
import lombok.ToString;

 /**
 * @Description:系统设置类
 * @Author:fanchunshuai
 * @CreateTime:2021-07-06 17:11:46
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