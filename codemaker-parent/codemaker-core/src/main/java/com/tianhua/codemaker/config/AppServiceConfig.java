package com.tianhua.codemaker.config;



import com.tianhua.codemaker.api.ICompDecorateService;
import com.tianhua.codemaker.api.ICompScanService;
import com.tianhua.codemaker.api.IValidateService;
import com.tianhua.codemaker.app.AppService;
import com.tianhua.codemaker.bean.config.ConfigFileBean;
import com.tianhua.codemaker.bean.config.FtlBean;
import com.tianhua.codemaker.bean.config.PomBean;
import com.tianhua.codemaker.enums.ModuleEnum;
import com.tianhua.codemaker.exceptions.ConfigException;
import com.tianhua.codemaker.app.IWriteFileService;
import com.tianhua.codemaker.utils.SpringContextHolder;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description:
 * date: 2021/6/22
 *
 * @author shenshuai
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

    @Value(value = "${application.global.package}")
    private String packageName;

    @Value(value = "${application.global.author}")
    private String author;

    /**
     * 是否链接具体数据库，根据数据库来构建数据模型
     */
    @Value(value = "${application.global.linkdb}")
    private Boolean linkDB;

    @Value(value = "${application.maven.repo.path}")
    private String mavenRepoPath;

    /**
     * 全局组件
     */
    @Value(value = "${application.component.scan.config}")
    private String defaultComponentConfig;

    @Value(value = "${application.component.scan.bean}")
    private String defaultComponentScanBean;


    @Value(value = "${application.component.decorate.bean}")
    private String defaultComponentDecorateBean;

    @Value(value = "${application.javsscript.enable}")
    private boolean enableJavsScript;

    @Resource(name = "colaAppService")
    private AppService colaAppService;

    @Resource(name = "dubboAppService")
    private AppService dubboAppService;

    @Resource(name = "springBootAppService")
    private AppService springBootAppService;

    @Resource(name = "dynamicDDDAppService")
    private AppService dynamicDDDAppService;

    @Resource(name = "springCloudAppService")
    private AppService springCloudAppService;

    @Autowired
    private ProjectTemplateSpringbootConfig projectTemplateSpringbootConfig;

    @Autowired
    private ProjectTemplateDubboConfig projectTemplateDubboConfig;

    @Autowired
    private ProjectTemplateColaConfig projectTemplateColaConfig;

    @Autowired
    private ProjectTemplateSpringCloudConfig projectTemplateSpringCloudConfig;

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
        else if(applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())){
            return  springCloudAppService;
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
            logger.error("get WriteFileService impl error.templateName={}",templateName);
        }
        return null;
    }

    /**
     * 根据模块名称获取模块服务
     * @param moduleName
     * @return
     */
    public IWriteFileService getWriteServiceByModuleName(String moduleName){
        try {
            AppService appService = this.getAppService();
            return appService.getModelAppService(moduleName);
        } catch (Exception e) {
            logger.error("get WriteFileService impl error.templateName={}",moduleName);
        }
        return null;
    }

    /**
     * 获取dynamicddd代码写服务
     * @return
     */
    public IWriteFileService getDynamicDDDWriteService(){
        return dynamicDDDAppService.getModelAppService(null);
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
            if(StringUtils.isEmpty(moduleName)){
                moduleName = ModuleEnum.SPRINGBOOT_API_DOC.getTemplateFileSet().contains(templateName) ? ModuleEnum.SPRINGBOOT_API_DOC.getModuleName() : null;
            }
        }
        else if(applicationType.equals(ModuleEnum.DUBBO_API.getAppName())){
            moduleName = ModuleEnum.DUBBO_API.getTemplateFileSet().contains(templateName) ? ModuleEnum.DUBBO_API.getModuleName() : null;
            if(StringUtils.isEmpty(moduleName)){
                moduleName = ModuleEnum.DUBBO_COMMON.getTemplateFileSet().contains(templateName) ? ModuleEnum.DUBBO_COMMON.getModuleName() : null;
            }
            if(StringUtils.isEmpty(moduleName)){
                moduleName = ModuleEnum.DUBBO_CORE.getTemplateFileSet().contains(templateName) ? ModuleEnum.DUBBO_CORE.getModuleName() : null;
            }

            if(StringUtils.isEmpty(moduleName)){
                moduleName = ModuleEnum.DUBBO_API_DOC.getTemplateFileSet().contains(templateName) ? ModuleEnum.DUBBO_API_DOC.getModuleName() : null;
            }
        }
        else if(applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())){
            moduleName = ModuleEnum.COLA_ADAPTER.getTemplateFileSet().contains(templateName) ? ModuleEnum.COLA_ADAPTER.getModuleName() : null;
            if(StringUtils.isEmpty(moduleName)){
                moduleName = ModuleEnum.COLA_APP.getTemplateFileSet().contains(templateName) ? ModuleEnum.COLA_APP.getModuleName() : null;
            }
            if(StringUtils.isEmpty(moduleName)){
                moduleName = ModuleEnum.COLA_DOMAIN.getTemplateFileSet().contains(templateName) ? ModuleEnum.COLA_DOMAIN.getModuleName() : null;
            }
            if(StringUtils.isEmpty(moduleName)){
                moduleName = ModuleEnum.COLA_CLIENT.getTemplateFileSet().contains(templateName) ? ModuleEnum.COLA_CLIENT.getModuleName() : null;
            }
            if(StringUtils.isEmpty(moduleName)){
                moduleName = ModuleEnum.COLA_INFRAST.getTemplateFileSet().contains(templateName) ? ModuleEnum.COLA_INFRAST.getModuleName() : null;
            }
            if(StringUtils.isEmpty(moduleName)){
                moduleName = ModuleEnum.COLA_API_DOC.getTemplateFileSet().contains(templateName) ? ModuleEnum.COLA_API_DOC.getModuleName() : null;
            }
            if(StringUtils.isEmpty(moduleName)){
                moduleName = ModuleEnum.COLA_START.getTemplateFileSet().contains(templateName) ? ModuleEnum.COLA_START.getModuleName() : null;
            }
            if(StringUtils.isEmpty(moduleName)){
                moduleName = ModuleEnum.COLA_FEIGN_API.getTemplateFileSet().contains(templateName) ? ModuleEnum.COLA_FEIGN_API.getModuleName() : null;
            }
        }
        else if(applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())){
            moduleName = ModuleEnum.SC_FEIGN_API.getTemplateFileSet().contains(templateName) ? ModuleEnum.SC_FEIGN_API.getModuleName() : null;
            if(StringUtils.isEmpty(moduleName)){
                moduleName = ModuleEnum.SC_FEIGN_PROVIDER.getTemplateFileSet().contains(templateName) ? ModuleEnum.SC_FEIGN_PROVIDER.getModuleName() : null;
            }
            if(StringUtils.isEmpty(moduleName)){
                moduleName = ModuleEnum.SC_API_DOC.getTemplateFileSet().contains(templateName) ? ModuleEnum.SC_API_DOC.getModuleName() : null;
            }
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
            return projectTemplateSpringbootConfig.getDbName();
        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            return projectTemplateDubboConfig.getDbName();
        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            return projectTemplateColaConfig.getDbName();

        } else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            return projectTemplateSpringCloudConfig.getDbName();
        }
        return "";
    }

    public String getAuthor() {
        return this.author;
    }

    public String getPackage() {
        return this.packageName;
    }

    public String getMavenRepoPath() {
        return mavenRepoPath;
    }

    public void setMavenRepoPath(String mavenRepoPath) {
        this.mavenRepoPath = mavenRepoPath;
    }

    /**
     * 获取配置中的plantUML类图文件名
     * @return
     */
    public String getPlantUMLFileName() {
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            return projectTemplateSpringbootConfig.getPlantumlName();
        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            return projectTemplateDubboConfig.getPlantumlName();
        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            return projectTemplateColaConfig.getPlantumlName();
        } else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            return projectTemplateSpringCloudConfig.getPlantumlName();
        }
        return "";
    }

    /**
     * 获取配置中的应用名称
     * @return
     */
    public String getApplicationName() {
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            return projectTemplateSpringbootConfig.getApplicationName();
        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            return projectTemplateDubboConfig.getApplicationName();
        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            return projectTemplateColaConfig.getApplicationName();
        } else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            return projectTemplateSpringCloudConfig.getApplicationName();
        }
        return "";
    }

    /**
     * 获取不同项目的核心模块，db-er图生成之后会在此模块存放
     * @return
     */
    public String getErPictureOutPath(){
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            return projectTemplateSpringbootConfig.getModulePath();
        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            return projectTemplateDubboConfig.getModuleCommonPath();
        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            return projectTemplateColaConfig.getModuleInfrastPath();
        } else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            return projectTemplateSpringCloudConfig.getModuleFeginProviderPath();
        }
        return null;
    }


    /**
     * 获取不同项目的文档输出路径
     * @return
     */
    public String getApiDocOutPath(){
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            return projectTemplateSpringbootConfig.getApiDocPath();
        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            return projectTemplateDubboConfig.getApiDocPath();
        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            return projectTemplateColaConfig.getApiDocPath();
        } else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            return projectTemplateSpringCloudConfig.getApiDocPath();
        }
        return null;
    }

    /**
     * 获取不同项目的文档输出路径
     * @return
     */
    public String getJavsProjectOutPath(){
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            return projectTemplateSpringbootConfig.getModulePath()+"-javs";
        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            return projectTemplateDubboConfig.getModuleParentPath()+"-javs";
        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            return projectTemplateColaConfig.getModuleParentPath()+"-javs";
        } else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            return projectTemplateSpringCloudConfig.getModuleParentPath()+"-javs";
        }
        return null;
    }

    /**
     * 获取不同项目的文档输出路径
     * @return
     */
    public String getUMLDocOutPath(){
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            return projectTemplateSpringbootConfig.getUMLDocPath();
        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            return projectTemplateDubboConfig.getUMLDocPath();
        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            return projectTemplateColaConfig.getUMLDocPath();
        } else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            return projectTemplateSpringCloudConfig.getUMLDocPath();
        }
        return null;
    }

    /**
     * 获取配置中的文档生成开关
     * @return
     */
    public Boolean getApiDocGenerator() {
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            return projectTemplateSpringbootConfig.getApiGenerate();
        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            return projectTemplateDubboConfig.getApiGenerate();
        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            return projectTemplateColaConfig.getApiGenerate();
        } else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            return projectTemplateSpringCloudConfig.getApiGenerate();
        }
        return false;
    }

    /**
     * 获取配置中的文档生成开关
     * @return
     */
    public Boolean getPlantUMLSync() {
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            return projectTemplateSpringbootConfig.getPlantUMlDocSync();
        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            return projectTemplateDubboConfig.getPlantUMlDocSync();
        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            return projectTemplateColaConfig.getPlantUMlDocSync();
        } else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            return projectTemplateSpringCloudConfig.getPlantUMlDocSync();
        }
        return false;
    }



    /**
     * 获取配置中的自定义读操作统一语言
     * @return
     */
    public Set<String> getReadDslSet() {
        String readSence = null;
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            readSence = projectTemplateSpringbootConfig.getReadSenceSet();

        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            readSence = projectTemplateDubboConfig.getReadSenceSet();

        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            readSence = projectTemplateColaConfig.getReadSenceSet();
        }else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            readSence = projectTemplateSpringCloudConfig.getReadSenceSet();
        }
        if(StringUtils.isEmpty(readSence)){
            return null;
        }
        return  Sets.newHashSet(readSence.split(","));
    }


    /**
     * 获取reuest分子包配置
     * @return
     */
    public boolean getRequestAsSubPackage() {

        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            return projectTemplateSpringbootConfig.getRequest();

        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            return projectTemplateDubboConfig.getRequest();

        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            return projectTemplateColaConfig.getRequest();
        } else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            return projectTemplateSpringCloudConfig.getRequest();
        }
        return  false;
    }


    /**
     * 获取response分子包配置
     * @return
     */
    public boolean getResponseAsSubPackage() {

        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            return projectTemplateSpringbootConfig.getResponse();

        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            return projectTemplateDubboConfig.getResponse();
        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            return projectTemplateColaConfig.getResponse();
        } else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            return projectTemplateSpringCloudConfig.getResponse();
        }
        return  false;
    }


    /**
     * 获取配置中的自定义写操作统一语言
     * @return
     */
    public Set<String> getWriteDslSet() {
        String writeSence = null;
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            writeSence = projectTemplateSpringbootConfig.getWriteSenceSet();

        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            writeSence = projectTemplateDubboConfig.getWriteSenceSet();

        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            writeSence = projectTemplateColaConfig.getWriteSenceSet();
        } else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            writeSence = projectTemplateSpringCloudConfig.getWriteSenceSet();
        }
        if(StringUtils.isEmpty(writeSence)){
            return null;
        }
        return  Sets.newHashSet(writeSence.split(","));
    }



    /**
     * 获取项目自定义的需要注入的组件列表
     * @return
     */
    public List<String> getCustomComponentList(){
        String componentList = null;
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            componentList = projectTemplateSpringbootConfig.getComponentList();
        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            componentList = projectTemplateDubboConfig.getComponentList();
        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            componentList = projectTemplateColaConfig.getComponentList();
        } else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            componentList = projectTemplateSpringCloudConfig.getComponentList();
        }
        if(StringUtils.isEmpty(componentList)){
            return null;
        }
        return Lists.newArrayList(componentList.split(","));
    }

    /**
     * 获取需要初始化到项目里的工具类组件
     * @return
     */
    public Map<String, List<String>> getInitClassMapList(){

        String initClazz = null;
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            initClazz = projectTemplateSpringbootConfig.getInitClazz();
        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            initClazz = projectTemplateDubboConfig.getInitClazz();
        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            initClazz = projectTemplateColaConfig.getInitClazz();
        } else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            initClazz = projectTemplateSpringCloudConfig.getInitClazz();
        }

        if(StringUtils.isEmpty(initClazz)){
            return null;
        }

        String [] array = initClazz.split(",");
        Map<String,List<String>> initClassMap = new HashMap<>();
        for (String clazz : array){
            String [] kvArr = clazz.trim().split(":");
            String className = kvArr[0];
            String moduleName = applicationType + "-" + kvArr[1];
            if(applicationType.equals(kvArr[1])){
                moduleName = applicationType;
            }
            List<String> classList = initClassMap.get(moduleName);
            if (CollectionUtils.isEmpty(classList)){
                classList = new ArrayList<>();
            }
            classList.add(className);
            initClassMap.put(moduleName, classList);
        }
        return initClassMap;
    }



    /**
     * 获取配置的组件扫描bean
     * @return
     */
    public List<ICompScanService> getCustomICompScanServiceList(){
        String componentScanBeanStr = null;
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            componentScanBeanStr = projectTemplateSpringbootConfig.getComponentScanBeans();
        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            componentScanBeanStr = projectTemplateDubboConfig.getComponentScanBeans();
        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            componentScanBeanStr = projectTemplateColaConfig.getComponentScanBeans();
        } else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            componentScanBeanStr = projectTemplateSpringCloudConfig.getComponentScanBeans();
        }
        if(StringUtils.isEmpty(componentScanBeanStr)){
            return null;
        }
        List<String> componentScanBeanList = Lists.newArrayList(componentScanBeanStr.split(","));
        List<ICompScanService> compScanServiceList = new ArrayList<>();

        for (String scanBeanName : componentScanBeanList){
            compScanServiceList.add(SpringContextHolder.getBean(scanBeanName,ICompScanService.class));
        }
        return compScanServiceList;
    }

    /**
     * 获取配置的组件装饰bean
     * @return
     */
    public List<ICompDecorateService> getCustomICompDecorateServiceList(){
        String componentDecorateBeanStr = null;
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            componentDecorateBeanStr = projectTemplateSpringbootConfig.getComponentDecorateBeans();
        } else if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            componentDecorateBeanStr = projectTemplateDubboConfig.getComponentDecorateBeans();
        } else if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            componentDecorateBeanStr = projectTemplateColaConfig.getComponentDecorateBeans();
        } else if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            componentDecorateBeanStr = projectTemplateSpringCloudConfig.getComponentDecorateBeans();
        }
        if(StringUtils.isEmpty(componentDecorateBeanStr)){
            return null;
        }
        List<String> componentDecorateBeanList = Lists.newArrayList(componentDecorateBeanStr.split(","));
        List<ICompDecorateService> compDecorateServiceList = new ArrayList<>();

        for (String decorateBeanName : componentDecorateBeanList){
            compDecorateServiceList.add(SpringContextHolder.getBean(decorateBeanName,ICompDecorateService.class));
        }
        return compDecorateServiceList;
    }

    /**
     * 获取全局组件
     * @return
     */
    public List<String> getDefaultComponentList(){
        return Lists.newArrayList(this.defaultComponentConfig.split(","));
    }

    /**
     * 获取默认的框架级全局组件扫描服务
     * @return
     */
    public ICompScanService getDefaultCompScanService(){
        return SpringContextHolder.getBean(this.defaultComponentScanBean,ICompScanService.class);
    }

    /**
     * 获取默认的框架级全局组件装饰服务
     * @return
     */
    public ICompDecorateService getDefaultCompDecorateService(){
        return SpringContextHolder.getBean(this.defaultComponentDecorateBean,ICompDecorateService.class);
    }

    /**
     * 获取自定义的代码元素注册内容
     * @return
     */
    public List<FtlBean> getCustomCodeFtlList(){
        List<FtlBean> ftlBeanList = new ArrayList<>();
        if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            ftlBeanList = projectTemplateColaConfig.getFtlBeanList();
        }

        if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            ftlBeanList = projectTemplateDubboConfig.getFtlBeanList();
        }
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            ftlBeanList = projectTemplateSpringbootConfig.getFtlBeanList();
        }
        if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            ftlBeanList = projectTemplateSpringCloudConfig.getFtlBeanList();
        }
        ftlBeanList.forEach(ftlBean -> ModuleEnum.addCustomTemplateFile(this.applicationType, ftlBean.getModuleName(),ftlBean.getCodeTempFileName()));
        return ftlBeanList;
    }

    /**
     * 获取统一的pom项目配置
     * @return
     */
    public Map<String,PomBean> getPomConfigMap(){
        if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            return projectTemplateColaConfig.getPomBeanV2();
        }
        if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            return projectTemplateDubboConfig.getPomBeanV2();
        }
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            return projectTemplateSpringbootConfig.getPomBeanV2();
        }
        if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            return projectTemplateSpringCloudConfig.getPomBeanV2();
        }
        return null;
    }

    /**
     * 获取项目的配置信息
     * @return
     */
    public List<ConfigFileBean> getConfigFileBeanList(){
        if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            return projectTemplateColaConfig.getConfigFileBean();
        }
        if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            return projectTemplateDubboConfig.getConfigFileBean();
        }
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            return projectTemplateSpringbootConfig.getConfigFileBean();
        }
        if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            return projectTemplateSpringCloudConfig.getConfigFileBean();
        }
        return null;
    }

    public Boolean getLinkDB() {
        return linkDB;
    }

    /**
     * 获取参数校验bean列表
     * @return
     */
    public List<IValidateService> getValidateServiceBeanList(){
        List<String> validateBeanList = new ArrayList<>();
        if (applicationType.equals(ModuleEnum.COLA_ADAPTER.getAppName())) {
            validateBeanList = projectTemplateColaConfig.getValidateBeanList();
        }
        if (applicationType.equals(ModuleEnum.DUBBO_API.getAppName())) {
            validateBeanList = projectTemplateDubboConfig.getValidateBeanList();
        }
        if (applicationType.equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            validateBeanList = projectTemplateSpringbootConfig.getValidateBeanList();
        }
        if (applicationType.equals(ModuleEnum.SC_FEIGN_API.getAppName())) {
            validateBeanList = projectTemplateSpringCloudConfig.getValidateBeanList();
        }
        return validateBeanList.stream().map(beanName -> (IValidateService)SpringContextHolder.getBean(beanName)).collect(Collectors.toList());
    }


    /**
     * 判断是否配置了mybatis-plus组件的装饰bean
     *
     * @return
     */
    public boolean containsMybatisPlus(){
        List<ICompDecorateService> compDecorateServiceList = getCustomICompDecorateServiceList();
        for (ICompDecorateService iCompDecorateService : compDecorateServiceList){
            if(iCompDecorateService.getClass().getName().toLowerCase().contains("mybatisplus")){
                return true;
            }
        }

        return false;
    }


    /**
     * 判断是否启用javsscritp脚本引擎并构建脚本文件
     * @return
     */
    public boolean enableJavsScript(){
        return this.enableJavsScript;
    }
}
