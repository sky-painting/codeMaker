package com.coderman.infosys.auth.app.facadeimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.coderman.infosys.auth.api.dto.UserGroupResponseDTO;
import com.coderman.infosys.auth.api.facade.UserGroupQueryFacade;
import com.coderman.utils.response.ResultDataDto;
/**
 * @Description:接口实现类
 * @Author：
 * @CreateTime：2021-11-07 08:18:28
 * @version v1.0
 */
@Service
public class UserGroupQueryFacadeImpl   implements UserGroupQueryFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());



    @Override
    public ResultDataDto<UserGroupResponseDTO> getByGroupCode(String groupCode){

        return null;
    }

}