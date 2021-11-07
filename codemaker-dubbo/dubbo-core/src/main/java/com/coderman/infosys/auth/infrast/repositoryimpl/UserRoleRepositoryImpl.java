package com.coderman.infosys.auth.infrast.repositoryimpl;

import java.util.List;
import com.coderman.infosys.auth.domain.bo.UserGroupBO;
import com.coderman.utils.commonbo.PageBO;
import com.coderman.infosys.auth.domain.gataway.UserRoleRepository;
import com.coderman.infosys.auth.domain.bo.RoleBO;
import com.coderman.infosys.auth.domain.bo.RoleGroupBO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Description:接口实现类
 * @Author：
 * @CreateTime：2021-11-07 08:18:28
 * @version v1.0
 */
@Service
public class UserRoleRepositoryImpl  implements UserRoleRepository{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());



    @Override
	public Long saveUserGroup(UserGroupBO userGroupBO){

        return null;
    }

    @Override
	public Integer updateUserGroup(UserGroupBO userGroupBO){

        return null;
    }

    @Override
	public Long saveRoleGroup(RoleGroupBO roleGroupBO){

        return null;
    }

    @Override
	public Integer updateRoleGroup(RoleGroupBO roleGroupBO){

        return null;
    }

    @Override
	public Long saveRole(RoleBO roleBO){

        return null;
    }

    @Override
	public Integer updateRole(RoleBO roleBO){

        return null;
    }

    @Override
	public List<RoleBO> getPageList(PageBO pageBO){

        return null;
    }

    @Override
	public UserGroupBO getByUserGroupCode(String userGroupCode){

        return null;
    }

    @Override
	public RoleBO getByRoleCode(String roleCode){

        return null;
    }

    @Override
	public RoleGroupBO getByRoleGroupCode(String roleGroupCode){

        return null;
    }

}