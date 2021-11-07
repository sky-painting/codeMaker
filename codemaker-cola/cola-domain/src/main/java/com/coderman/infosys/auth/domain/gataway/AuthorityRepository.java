package com.coderman.infosys.auth.domain.gataway;

import com.coderman.infosys.auth.domain.bo.AuthorityBO;
import com.coderman.infosys.auth.domain.bo.AuthorityUpdateRequestBO;


/**
 * @Description:权限管理仓库接口
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:23:49
 * @version v1.0
 */
public interface AuthorityRepository{

	/**
	 *
	 * @Description 给角色构建权限
	 * @param authorityBO
	 * @return Boolean
	 */
     Boolean buildAuthority(AuthorityBO authorityBO);

	/**
	 *
	 * @Description 给角色更新权限
	 * @param authorityUpdateRequestBO
	 * @return Boolean
	 */
     Boolean updateAuthority(AuthorityUpdateRequestBO authorityUpdateRequestBO);

	/**
	 *
	 * @Description 根据权限code禁用权限资源
	 * @param authorityCode
	 * @return Boolean
	 */
     Boolean disableAuthority(String authorityCode);

	/**
	 *
	 * @Description 根据权限code查询权限资源
	 * @param authorityCode
	 * @return AuthorityBO
	 */
     AuthorityBO getByCode(String authorityCode);

	/**
	 *
	 * @Description 根据角色code查询权限资源
	 * @param roleCode
	 * @return AuthorityBO
	 */
     AuthorityBO getByRoleCode(String roleCode);
}