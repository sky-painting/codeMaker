package com.coderman.codemaker.config;

import com.coderman.codemaker.app.AppService;
import com.coderman.codemaker.enums.ModuleEnum;
import com.coderman.codemaker.exceptions.ConfigException;
import com.coderman.codemaker.service.IWriteFileService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@Component
public class AppServiceConfig {

    private Logger logger = LoggerFactory.getLogger(AppServiceConfig.class);

    /**
     * 应用类型
     */
    @Value("${application.type}")
    private String  applicationType;

    @Resource(name = "colaAppService")
    private AppService colaAppService;

    @Resource(name = "dubboAppService")
    private AppService dubboAppService;

    @Resource(name = "springBootAppService")
    private AppService springBootAppService;

    @Autowired
    private ProjectTemplateConfig projectTemplateConfig;

    @Autowired
    private ProjectTemplateDubboConfig projectTemplateDubboConfig;

    /**
     * 从应用框架的视角获取应用服务
      * @return
     * @throws Exception
     */
    public AppService getAppService() throws Exception {
        if (StringUtils.isEmpty(applicationType)) {
            throw new ConfigException("application.type not null,please set it in application.properties!");
        }
        if(applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())){
            return springBootAppService;
        }
        else if(applicationType.equals(ModuleEnum.DUBBO_API.getAppName())){
            return dubboAppService;
        }
        else if(applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())){
            return  colaAppService;
        }
        throw new ConfigException("application.type="+applicationType+" not support!");
    }


    /**
     * 根据模板名称获取模块服务
     * @param templateName
     * @return
     */
    public IWriteFileService getModuleWriteService(String templateName){
        try {
            AppService appService = this.getAppService();
            String moduleName = getModuleName(templateName);
            return appService.getModelAppService(moduleName);
        } catch (Exception e) {
            logger.error("get WriteFileService impl error.");
        }
        return null;
    }

    /**
     * 根据模版名称获取模块名称
     * @param templateName
     * @return
     */
    public String getModuleName(String templateName){
        String moduleName = null;
        if(applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())){
            moduleName = ModuleEnum.SPRING_BOOT_WEB.getTemplateFileSet().contains(templateName) ? ModuleEnum.SPRING_BOOT_WEB.getModuleName() : null;
        }
        else if(applicationType.equals(ModuleEnum.DUBBO_API.getAppName())){
            moduleName = ModuleEnum.DUBBO_API.getTemplateFileSet().contains(templateName) ? ModuleEnum.DUBBO_API.getModuleName() : null;
            if(StringUtils.isEmpty(moduleName)){
                moduleName = ModuleEnum.DUBBO_COMMON.getTemplateFileSet().contains(templateName) ? ModuleEnum.DUBBO_COMMON.getModuleName() : null;
            }
            if(StringUtils.isEmpty(moduleName)){
                moduleName = ModuleEnum.DUBBO_CORE.getTemplateFileSet().contains(templateName) ? ModuleEnum.DUBBO_CORE.getModuleName() : null;
            }
        }
        else if(applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())){

        }
        if(StringUtils.isEmpty(moduleName)){
            logger.error("templatefile:"+templateName+" not in the application:"+applicationType);
        }
        return moduleName;
    }


    public String getApplicationType(){
        return applicationType;
    }


    public String getDbName() {
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            return projectTemplateConfig.getDbName();
        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            return projectTemplateDubboConfig.getDbName();
        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            return projectTemplateConfig.getDbName();
        }
        return "";
    }

}