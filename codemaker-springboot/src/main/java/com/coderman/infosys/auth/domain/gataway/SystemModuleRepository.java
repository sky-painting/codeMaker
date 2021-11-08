package com.coderman.infosys.auth.domain.gataway;

import com.coderman.infosys.auth.domain.bo.MenuBO;
import com.coderman.infosys.auth.domain.bo.SystemBO;
import com.coderman.infosys.auth.domain.bo.ModuleBO;


/**
 * @Description:系统菜单按钮仓库服务接口
 * @Author:shenshuai
 * @CreateTime:2021-11-06 23:38:06
 * @version v1.0
 */
public interface SystemModuleRepository{
    /**
     *
     * @Description:创建系统信息
     * @return Long
     */
     Long saveSystem(SystemBO systemBO);
    /**
     *
     * @Description:更新系统信息
     * @return Integer
     */
     Integer updateSystem(SystemBO systemBO);
    /**
     *
     * @Description:创建菜单信息
     * @return Long
     */
     Long saveModule(ModuleBO moduleBO);
    /**
     *
     * @Description:更新菜单信息
     * @return Integer
     */
     Integer updateModule(ModuleBO moduleBO);
    /**
     *
     * @Description:创建按钮信息
     * @return Long
     */
     Long saveMenu(MenuBO moduleBO);
    /**
     *
     * @Description:更新按钮信息
     * @return Integer
     */
     Integer updateMenu(MenuBO moduleBO);
}