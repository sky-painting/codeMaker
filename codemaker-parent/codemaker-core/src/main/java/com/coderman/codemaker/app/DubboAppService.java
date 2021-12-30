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
@Component(value = "dubboAppService")
public class DubboAppService implements AppService{
    @Resource(name = "dubboCoreWriteFileService")
    private IWriteFileService dubboCoreWriteFileService;

    @Resource(name = "dubboApiWriteFileService")
    private IWriteFileService dubboApiWriteFileService;

    @Resource(name = "dubboCommonWriteFileService")
    private IWriteFileService dubboCommonWriteFileService;



    @Resource(name = "dubboApiDocWriteFileService")
    private IWriteFileService dubboApiDocWriteFileService;


    @Override
    public IWriteFileService getModelAppService(String moduleName) {
        if(moduleName.equals(ModuleEnum.DUBBO_API.getModuleName())){
            return dubboApiWriteFileService;
        } else if (moduleName.equals(ModuleEnum.DUBBO_COMMON.getModuleName())) {
            return  dubboCommonWriteFileService;
        }
        else if (moduleName.equals(ModuleEnum.DUBBO_API_DOC.getModuleName())) {
            return  dubboApiDocWriteFileService;
        }
        return dubboCoreWriteFileService;
    }
}
