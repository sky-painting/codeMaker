package com.coderman.infosys.auth.app.exe;

import com.coderman.infosys.auth.app.command.AbstractCmd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.coderman.infosys.auth.app.exe.RoleAuthCmdExe;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:角色权限cmd处理类接口实现类
 * @Author：shenshuai
 * @CreateTime：2021-11-07 08:18:28
 * @version v1.0
 */
@Service
public class RoleAuthCmdExe  implements  CmdExeService{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
	public String exe(AbstractCmd abstractCmd){

        return null;
    }

}