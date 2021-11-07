package com.coderman.infosys.auth.domain.gataway;

import java.util.List;
import com.coderman.infosys.auth.domain.bo.MenuBO;
import com.coderman.utils.commonbo.PageBO;
import com.coderman.infosys.auth.domain.bo.SystemBO;
import com.coderman.infosys.auth.domain.bo.ModuleBO;


/**
 * @Description:系统菜单按钮查询服务接口
 * @Author:shenshuai
 * @CreateTime:2021-11-07 08:18:28
 * @version v1.0
 */
public interface SystemModuleQueryGataWay{
    /**
     *
     * @Description:分页查询系统列表
     * @return List<SystemBO>
     */
     List<SystemBO> querySystemPage(PageBO pageBO);
    /**
     *
     * @Description:分页查询菜单列表
     * @return List<ModuleBO>
     */
     List<ModuleBO> queryModulePage();
    /**
     *
     * @Description:基于系统code查询菜单和按钮
     * @return SystemBO
     */
     SystemBO queryBySystemCode(String systemCode);
    /**
     *
     * @Description:根据条件查询系统
     * @return List<SystemBO>
     */
     List<SystemBO> queryByCondition(String search);
    /**
     *
     * @Description:查询所有系统菜单和按钮
     * @return List<SystemBO>
     */
     List<SystemBO> queryWithAll(String search);
    /**
     *
     * @Description:根据code查询系统详情
     * @return SystemBO
     */
     SystemBO getSystemByCode(String systemCode);
    /**
     *
     * @Description:根据code查询菜单详情
     * @return ModuleBO
     */
     ModuleBO getModuleByCode(String moduleCode);
    /**
     *
     * @Description:根据code查询按钮详情
     * @return MenuBO
     */
     MenuBO getMenu(String menuCode);
    /**
     *
     * @Description:根据系统名称查询
     * @return List<SystemBO>
     */
     List<SystemBO> getSystemByName(String systemName);
}