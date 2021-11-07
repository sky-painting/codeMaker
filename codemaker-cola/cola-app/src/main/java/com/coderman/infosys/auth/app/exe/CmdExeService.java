package com.coderman.infosys.auth.app.exe;

import com.coderman.infosys.auth.app.command.AbstractCmd;


/**
 * @Description:命令执行接口接口
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:23:49
 * @version v1.0
 */
public interface CmdExeService{

	/**
	 *
	 * @Description 命令执行入口
	 * @param abstractCmd
	 * @return String
	 */
     String exe(AbstractCmd abstractCmd);
}