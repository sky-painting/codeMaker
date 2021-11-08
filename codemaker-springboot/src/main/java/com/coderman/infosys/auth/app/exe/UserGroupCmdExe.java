package com.coderman.infosys.auth.app.exe;

import com.coderman.infosys.auth.app.command.AbstractCmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.coderman.infosys.auth.app.exe.UserGroupCmdExe;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:用户组cmd处理类接口实现类
 * @Author：shenshuai
 * @CreateTime：2021-11-06 23:38:06
 * @version v1.0
 */
@Service
public class UserGroupCmdExe  implements  CmdExeService{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
	public String exe(AbstractCmd abstractCmd){

        return null;
    }

}