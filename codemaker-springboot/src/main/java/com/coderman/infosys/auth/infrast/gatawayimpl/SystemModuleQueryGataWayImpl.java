package com.coderman.infosys.auth.infrast.gatawayimpl;

import java.util.List;
import com.coderman.infosys.auth.domain.bo.MenuBO;
import com.coderman.utils.commonbo.PageBO;
import com.coderman.infosys.auth.domain.bo.SystemBO;
import com.coderman.infosys.auth.domain.bo.ModuleBO;
import com.coderman.infosys.auth.domain.gataway.SystemModuleQueryGataWay;
import com.coderman.infosys.auth.infrast.dao.dataobject.ModuleDO;
import com.coderman.infosys.auth.infrast.dao.mapper.ModuleMapper;
import com.coderman.infosys.auth.infrast.dao.dataobject.MenuDO;
import com.coderman.infosys.auth.infrast.dataconvert.ModuleConvert;
import com.coderman.infosys.auth.infrast.dataconvert.MenuConvert;
import com.coderman.infosys.auth.infrast.dataconvert.SystemConvert;
import com.coderman.infosys.auth.infrast.dao.mapper.SystemMapper;
import com.coderman.infosys.auth.infrast.dao.dataobject.SystemDO;
import com.coderman.infosys.auth.infrast.dao.mapper.MenuMapper;

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
public class SystemModuleQueryGataWayImpl  implements SystemModuleQueryGataWay{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SystemMapper systemMapper;

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private MenuMapper menuMapper;


    @Override
	public List<SystemBO> querySystemPage(PageBO pageBO){
        List<SystemDO> systemDOList = systemMapper.getPageList(pageBO);
        List<SystemBO> systemBOList = SystemConvert.INSTANCE.doList2boList(systemDOList);

        return systemBOList;
    }

    @Override
	public List<ModuleBO> queryModulePage(){

        return null;
    }

    @Override
	public SystemBO queryBySystemCode(String systemCode){
        List<SystemDO> systemDOList = systemMapper.queryBySystemCode(systemCode);
        SystemBO systemBO = SystemConvert.INSTANCE.do2bo(null);

        return systemBO;
    }

    @Override
	public List<SystemBO> queryByCondition(String search){
        List<SystemDO> systemDOList = systemMapper.queryByCondition();
        List<SystemBO> systemBOList = SystemConvert.INSTANCE.doList2boList(systemDOList);

        return systemBOList;
    }

    @Override
	public List<SystemBO> queryWithAll(String search){

        return null;
    }

    @Override
	public SystemBO getSystemByCode(String systemCode){

        return null;
    }

    @Override
	public ModuleBO getModuleByCode(String moduleCode){
        List<ModuleDO> moduleDOList = moduleMapper.getByName(null);
        ModuleBO moduleBO = ModuleConvert.INSTANCE.do2bo(null);
        List<MenuDO> menuDOList = menuMapper.getListByModuleCode(null);
        List<MenuBO> menuBOList = MenuConvert.INSTANCE.doList2boList(menuDOList);

        return moduleBO;
    }

    @Override
	public MenuBO getMenu(String menuCode){

        return null;
    }

    @Override
	public List<SystemBO> getSystemByName(String systemName){
        List<SystemDO> systemDOList = systemMapper.getByName(systemName);
        List<SystemBO> systemBOList = SystemConvert.INSTANCE.doList2boList(systemDOList);

        return systemBOList;
    }

}