package com.coderman.infosys.auth.domain.gataway;

import java.util.List;
import com.coderman.infosys.auth.domain.bo.UserGroupBO;
import com.coderman.utils.commonbo.PageBO;
import com.coderman.infosys.auth.domain.bo.RoleBO;
import com.coderman.infosys.auth.domain.bo.RoleGroupBO;


/**
 * @Description:用户和角色管理仓库接口
 * @Author:shenshuai
 * @CreateTime:2021-11-06 23:38:06
 * @version v1.0
 */
public interface UserRoleRepository{
    /**
     *
     * @Description:保存用户组并尝试关联用户
     * @return Long
     */
     Long saveUserGroup(UserGroupBO userGroupBO);
    /**
     *
     * @Description:更新用户组
     * @return Integer
     */
     Integer updateUserGroup(UserGroupBO userGroupBO);
    /**
     *
     * @Description:保存角色组并尝试关联角色
     * @return Long
     */
     Long saveRoleGroup(RoleGroupBO roleGroupBO);
    /**
     *
     * @Description:更新角色组
     * @return Integer
     */
     Integer updateRoleGroup(RoleGroupBO roleGroupBO);
    /**
     *
     * @Description:保存角色
     * @return Long
     */
     Long saveRole(RoleBO roleBO);
    /**
     *
     * @Description:更新角色
     * @return Integer
     */
     Integer updateRole(RoleBO roleBO);
    /**
     *
     * @Description:分页获取角色
     * @return List<RoleBO>
     */
     List<RoleBO> getPageList(PageBO pageBO);
    /**
     *
     * @Description:根据code查询用户组详情
     * @return UserGroupBO
     */
     UserGroupBO getByUserGroupCode(String userGroupCode);
    /**
     *
     * @Description:根据code查询角色详情
     * @return RoleBO
     */
     RoleBO getByRoleCode(String roleCode);
    /**
     *
     * @Description:根据code查询角色组详情
     * @return RoleGroupBO
     */
     RoleGroupBO getByRoleGroupCode(String roleGroupCode);
}