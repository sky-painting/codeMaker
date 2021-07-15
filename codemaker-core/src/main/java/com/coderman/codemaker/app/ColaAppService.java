package com.coderman.codemaker.app;

import com.coderman.codemaker.enums.ModuleEnum;
import com.coderman.codemaker.service.IWriteFileService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description:
 * date: 2021/6/22
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "colaAppService")
public class ColaAppService implements AppService{

    @Resource(name = "colaAdapterWriteService")
    private IWriteFileService colaAdapterWriteService;

    @Resource(name = "colaAppWriteService")
    private IWriteFileService colaAppWriteService;

    @Resource(name = "colaClientWriteService")
    private IWriteFileService colaClientWriteService;

    @Resource(name = "colaDomainWriteService")
    private IWriteFileService colaDomainWriteService;

    @Resource(name = "colaInfrastWriteService")
    private IWriteFileService colaInfrastWriteService;


    @Override
    public IWriteFileService getModelAppService(String moduleName) {
        if(moduleName.equals(ModuleEnum.COLA_INFRAST.getModuleName())){
            return colaInfrastWriteService;
        } else if (moduleName.equals(ModuleEnum.COLA_CLIENT.getModuleName())) {
            return  colaClientWriteService;
        }
        else if(moduleName.equals(ModuleEnum.COLA_DOMAIN.getModuleName())){
            return colaDomainWriteService;
        } else if (moduleName.equals(ModuleEnum.COLA_APP.getModuleName())) {
            return  colaAppWriteService;
        }
        return colaAdapterWriteService;
    }
}
