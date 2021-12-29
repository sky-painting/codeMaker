package com.coderman.codemaker.app;

import com.coderman.codemaker.enums.ModuleEnum;
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


    @Resource(name = "colaApiDocWriteService")
    private IWriteFileService colaApiDocWriteService;



    @Resource(name = "colaStartWriteService")
    private IWriteFileService colaStartWriteService;


    @Resource(name = "colaFeignApiWriteService")
    private IWriteFileService colaFeginApiWriteService;


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
        else if (moduleName.equals(ModuleEnum.COLA_API_DOC.getModuleName())) {
            return  colaApiDocWriteService;
        }
        else if (moduleName.equals(ModuleEnum.COLA_START.getModuleName())) {
            return  colaStartWriteService;
        }
        else if (moduleName.equals(ModuleEnum.COLA_FEIGN_API.getModuleName())) {
            return  colaFeginApiWriteService;
        }
        return colaAdapterWriteService;
    }
}
