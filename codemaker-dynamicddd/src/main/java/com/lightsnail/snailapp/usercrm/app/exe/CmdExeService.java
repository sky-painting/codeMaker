package com.lightsnail.snailapp.usercrm.app.exe;

import com.lightsnail.snailapp.usercrm.app.command.AbstractCmd;


/**
 * @Description:命令执行接口接口
 * @Author:fanchunshuai
 * @CreateTime:2021-07-07 00:05:11
 * @version v1.0
 */
public interface CmdExeService{
    /**
     *
     * @Description:命令执行入口
     * @return String
     */
     String  exe(AbstractCmd abstractCmd);
}