package com.coderman.infosys.auth.domain.factory;

import com.coderman.infosys.auth.domain.bo.AuthorityBO;
import com.coderman.infosys.auth.domain.bo.UserAuthAggregateBO;
import com.coderman.infosys.auth.domain.bo.AuthAggregateBO;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

 /**
 * @Description:AuthorityFactory类
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:23:49
 * @version v1.0
 */
@Service
public class AuthorityFactory{



	/**
	 *
	 * @Description 基于权限本身的维度-权限编码构建权限模
	 * @param authorityCode
	 * @return AuthorityBO
	 */
    public AuthorityBO buildAuthorityByCode(String authorityCode){

        return null;
    }

	/**
	 *
	 * @Description 基于用户的维度构建权限模
	 * @param roleCode
	 * @return UserAuthAggregateBO
	 */
    public UserAuthAggregateBO buildAuthorityWithRoleCode(String roleCode){

        return null;
    }

}