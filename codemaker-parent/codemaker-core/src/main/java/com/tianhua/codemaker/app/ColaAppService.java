package com.tianhua.codemaker.app;

import com.tianhua.codemaker.bean.GlobalConstant;
import com.tianhua.codemaker.enums.ModuleEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description:
 * date: 2021/6/22
 *
 * @author shenshuai
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
        if(ModuleEnum.COLA_INFRAST.getModuleName().endsWith(moduleName)){
            return colaInfrastWriteService;
        } else if (ModuleEnum.COLA_CLIENT.getModuleName().endsWith(moduleName)) {
            return  colaClientWriteService;
        }
        else if(ModuleEnum.COLA_DOMAIN.getModuleName().endsWith(moduleName)){
            return colaDomainWriteService;
        } else if (ModuleEnum.COLA_APP.getModuleName().endsWith(moduleName)) {
            return  colaAppWriteService;
        }
        else if (moduleName.equals(ModuleEnum.COLA_API_DOC.getModuleName()) || moduleName.equals(GlobalConstant.PARENT)) {
            return  colaApiDocWriteService;
        }
        else if (ModuleEnum.COLA_START.getModuleName().endsWith(moduleName)) {
            return  colaStartWriteService;
        }
        else if (ModuleEnum.COLA_FEIGN_API.getModuleName().endsWith(moduleName)) {
            return  colaFeginApiWriteService;
        }
        return colaAdapterWriteService;
    }
}
