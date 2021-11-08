package com.coderman.infosys.auth.app.facadeimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.api.dto.RoleResponseDTO;
import com.coderman.infosys.auth.api.facade.RoleFacade;
/**
 * @Description:接口实现类
 * @Author：
 * @CreateTime：2021-11-07 08:23:49
 * @version v1.0
 */
@Service
public class RoleFacadeImpl   implements RoleFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());



    @Override
    public ResultDataDto<RoleResponseDTO> getByRoleCode(String roleCode){

        return null;
    }

}