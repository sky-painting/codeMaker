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
@Component(value = "springBootAppService")
public class SpringBootAppService implements AppService{
    @Resource(name = "springBootWriteService")
    private IWriteFileService springBootWriteService;



    @Resource(name = "springBootApiDocWriteService")
    private IWriteFileService springBootApiDocWriteService;


    @Override
    public IWriteFileService getModelAppService(String moduleName) {
        if (ModuleEnum.SPRING_BOOT_WEB.getModuleName().endsWith(moduleName)) {
            return  springBootWriteService;
        }
        else if (ModuleEnum.SPRINGBOOT_API_DOC.getModuleName().endsWith(moduleName) || moduleName.equals(GlobalConstant.PARENT)) {
            return  springBootApiDocWriteService;
        }
        return null;
    }
}
