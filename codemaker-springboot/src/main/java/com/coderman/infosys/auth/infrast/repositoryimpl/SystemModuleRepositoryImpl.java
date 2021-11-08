package com.coderman.infosys.auth.infrast.repositoryimpl;

import com.coderman.infosys.auth.domain.bo.MenuBO;
import com.coderman.infosys.auth.domain.gataway.SystemModuleRepository;
import com.coderman.infosys.auth.domain.bo.SystemBO;
import com.coderman.infosys.auth.domain.bo.ModuleBO;
import com.coderman.infosys.auth.infrast.dao.dataobject.ModuleDO;
import com.coderman.infosys.auth.infrast.dao.mapper.ModuleMapper;
import com.coderman.infosys.auth.infrast.dao.dataobject.MenuDO;
import com.coderman.infosys.auth.infrast.dataconvert.ModuleConvert;
import com.coderman.infosys.auth.infrast.dataconvert.MenuConvert;
import com.coderman.infosys.auth.infrast.mq.producer.SystemMqProducer;
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
public class SystemModuleRepositoryImpl  implements SystemModuleRepository{

	private  Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SystemMapper systemMapper;

    @Autowired
    private SystemMqProducer systemMqProducer;

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private MenuMapper menuMapper;


    @Override
	public Long saveSystem(SystemBO systemBO){
        SystemDO systemDO = SystemConvert.INSTANCE.bo2do(systemBO);
        long longVar = systemMapper.insert(systemDO);
        systemMqProducer.systemChange(null);

        return null;
    }

    @Override
	public Integer updateSystem(SystemBO systemBO){
        SystemDO systemDO = SystemConvert.INSTANCE.bo2do(systemBO);
        int intVar = systemMapper.updateSystem();

        return null;
    }

    @Override
	public Long saveModule(ModuleBO moduleBO){
        ModuleDO moduleDO = ModuleConvert.INSTANCE.bo2do(moduleBO);
        long longVar = moduleMapper.insert(moduleDO);

        return null;
    }

    @Override
	public Integer updateModule(ModuleBO moduleBO){
        ModuleDO moduleDO = ModuleConvert.INSTANCE.bo2do(moduleBO);
        int intVar = moduleMapper.update(moduleDO);

        return null;
    }

    @Override
	public Long saveMenu(MenuBO moduleBO){
        MenuDO menuDO = MenuConvert.INSTANCE.bo2do(moduleBO);
        long longVar = menuMapper.insert(menuDO);

        return null;
    }

    @Override
	public Integer updateMenu(MenuBO moduleBO){
        MenuDO menuDO = MenuConvert.INSTANCE.bo2do(moduleBO);
        int intVar = menuMapper.update(menuDO);

        return null;
    }

}