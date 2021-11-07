package com.coderman.infosys.auth.app.facadeimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.coderman.utils.response.ResultDataDto;
import com.coderman.infosys.auth.api.dto.CreateAuthorityRequestDTO;
import com.coderman.infosys.auth.api.facade.AuthorityFacade;
import com.coderman.infosys.auth.api.dto.AuthorityResponseDTO;
/**
 * @Description:接口实现类
 * @Author：
 * @CreateTime：2021-11-07 08:18:28
 * @version v1.0
 */
@Service
public class AuthorityFacadeImpl   implements AuthorityFacade{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());



    @Override
    public ResultDataDto<AuthorityResponseDTO> checkUserSystemAuth(Long userId,String systemCode){

        return null;
    }

    @Override
    public ResultDataDto<AuthorityResponseDTO> getUserSystemAuth(Long userId,String systemCode){

        return null;
    }

    @Override
    public ResultDataDto<AuthorityResponseDTO> getByCode(String authCode){

        return null;
    }

    @Override
    public ResultDataDto<AuthorityResponseDTO> saveAuthority(CreateAuthorityRequestDTO createAuthorityRequestDTO){

        return null;
    }

}