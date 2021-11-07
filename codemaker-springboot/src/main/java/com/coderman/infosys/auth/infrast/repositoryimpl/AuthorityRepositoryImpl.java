package com.coderman.infosys.auth.infrast.repositoryimpl;

import com.coderman.infosys.auth.domain.bo.AuthorityBO;
import com.coderman.infosys.auth.domain.bo.AuthorityUpdateRequestBO;
import com.coderman.infosys.auth.domain.gataway.AuthorityRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Description:接口实现类
 * @Author：
 * @CreateTime：2021-11-06 23:38:06
 * @version v1.0
 */
@Service
public class AuthorityRepositoryImpl  implements AuthorityRepository{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());



    @Override
	public Boolean buildAuthority(AuthorityBO authorityBO){

        return null;
    }

    @Override
	public Boolean updateAuthority(AuthorityUpdateRequestBO authorityUpdateRequestBO){

        return null;
    }

    @Override
	public Boolean disableAuthority(String authorityCode){

        return null;
    }

    @Override
	public AuthorityBO getByCode(String authorityCode){

        return null;
    }

    @Override
	public AuthorityBO getByRoleCode(String roleCode){

        return null;
    }

}