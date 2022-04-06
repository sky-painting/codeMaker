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
        if(ModuleEnum.DUBBO_API.getModuleName().endsWith(moduleName)){
            return dubboApiWriteFileService;
        } else if (ModuleEnum.DUBBO_COMMON.getModuleName().endsWith(moduleName)) {
            return  dubboCommonWriteFileService;
        }
        else if (ModuleEnum.DUBBO_API_DOC.getModuleName().endsWith(moduleName) || moduleName.equals(GlobalConstant.PARENT)) {
            return  dubboApiDocWriteFileService;
        }
        return dubboCoreWriteFileService;
    }
}
