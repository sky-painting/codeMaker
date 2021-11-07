package com.coderman.infosys.auth.app.command;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

 /**
 * @Description:抽象命令类类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:23:49
 * @version v1.0
 */
@Data
@ToString
public class AbstractCmd {


	/** 操作时 **/
    public  Date operateTime;

	/** 操作人I **/
    public  Long userId;

	/** 命令 **/
    public  Class commandClass;

	/** 命令数 **/
    public  String payLoad;;


}