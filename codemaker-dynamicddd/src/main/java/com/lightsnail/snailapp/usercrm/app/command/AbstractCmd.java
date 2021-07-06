package com.lightsnail.snailapp.usercrm.app.command;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

 /**
 * @Description:抽象命令类类
 * @Author:fanchunshuai
 * @CreateTime:2021-07-06 17:11:46
 * @version v1.0
 */
@Data
@ToString
public class AbstractCmd {

	/** +操作时间 **/
    public  Date operateTime;
	/** +操作人ID **/
    public  Long userId;
	/** +命令类 **/
    public  Class commandClass;
	/** +命令数据 **/
    public  String payLoad;;


}