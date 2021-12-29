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
@Component(value = "springBootAppService")
public class SpringBootAppService implements AppService{
    @Resource(name = "springBootWriteService")
    private IWriteFileService springBootWriteService;



    @Resource(name = "springBootApiDocWriteService")
    private IWriteFileService springBootApiDocWriteService;


    @Override
    public IWriteFileService getModelAppService(String moduleName) {
        if (moduleName.equals(ModuleEnum.SPRING_BOOT_WEB.getModuleName())) {
            return  springBootWriteService;
        }
        else if (moduleName.equals(ModuleEnum.SPRINGBOOT_API_DOC.getModuleName())) {
            return  springBootApiDocWriteService;
        }
        return null;
    }
}
