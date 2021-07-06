package com.lightsnail.snailapp.usercrm.app.exe;

import com.lightsnail.snailapp.usercrm.app.command.AbstractCmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.lightsnail.snailapp.usercrm.app.exe.UserAuthCmdExe;

/**
 * @Description:用户权限cmd处理类接口实现类
 * @Author：fanchunshuai
 * @CreateTime：2021-07-07 00:05:11
 * @version v1.0
 */
@Service
public class UserAuthCmdExe  implements  CmdExeService{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
	public String  exe(AbstractCmd abstractCmd){
        return null;
    }

}