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
@Component(value = "springCloudAppService")
public class SpringCloudAppService implements AppService{
    @Resource(name = "sCFeignApiWriteService")
    private IWriteFileService sCFeignApiWriteService;



    @Resource(name = "sCFeignProviderWriteService")
    private IWriteFileService sCFeignProviderWriteService;


    @Resource(name = "sCFeignApiDocWriteService")
    private IWriteFileService sCFeignApiDocWriteService;


    @Override
    public IWriteFileService getModelAppService(String moduleName) {
        if (moduleName.equals(ModuleEnum.SC_FEIGN_API.getModuleName())) {
            return  sCFeignApiWriteService;
        }
        else if (moduleName.equals(ModuleEnum.SPRINGBOOT_API_DOC.getModuleName())) {
            return  sCFeignApiDocWriteService;
        }
        else if (moduleName.equals(ModuleEnum.SC_FEIGN_PROVIDER.getModuleName())) {
            return  sCFeignProviderWriteService;
        }
        return null;
    }



}
