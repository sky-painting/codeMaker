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
        if (ModuleEnum.SC_FEIGN_API.getModuleName().endsWith(moduleName)) {
            return  sCFeignApiWriteService;
        }
        else if (ModuleEnum.SPRINGBOOT_API_DOC.getModuleName().endsWith(moduleName) || moduleName.equals(GlobalConstant.PARENT)) {
            return  sCFeignApiDocWriteService;
        }
        else if (ModuleEnum.SC_FEIGN_PROVIDER.getModuleName().endsWith(moduleName) ) {
            return  sCFeignProviderWriteService;
        }
        return null;
    }



}
