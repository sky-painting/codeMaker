package com.tianhua.codemaker.service;

import com.tianhua.codemaker.app.IWriteFileService;
import com.tianhua.codemaker.bean.ColumnBean;
import com.tianhua.codemaker.bean.TableBean;
import com.tianhua.codemaker.bean.WriteContentBean;
import com.tianhua.codemaker.bean.component.ComponentConfigBean;
import com.tianhua.codemaker.bean.config.ConfigFileBean;
import com.tianhua.codemaker.bean.config.FtlBean;
import com.tianhua.codemaker.bean.config.PomBean;
import com.tianhua.codemaker.bean.config.ProjectBean;
import com.tianhua.codemaker.bean.dddelement.ElementBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.EnumBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.config.AppServiceConfig;

import com.tianhua.codemaker.config.ConfigFactory;
import com.tianhua.codemaker.enums.ClassEnum;
import com.tianhua.codemaker.enums.ModuleEnum;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.tianhua.codemaker.service.component.ComponentDecorateService;
import com.tianhua.codemaker.service.plantuml.WriteDomainPlantDocService;
import com.tianhua.codemaker.service.registry.factory.ElementFactory;
import com.tianhua.codemaker.service.template.FreemarkerService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:
 * 新版本的应用模块代码写服务，屏蔽底层写文件的细节，通过app和module隔离api和底层实现
 * 做到一套api支持多种应用框架风格的代码生成。
 * date: 2021/6/22
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */

@Component
public class WriteDynamicCodeService extends AbstractEleWriteService{

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Autowired
    private FreemarkerService freemarkerService;

    @Autowired
    private WriteDynamicDDDService writeDynamicDDDModuleService;

    @Autowired
    private WriteCodeService writeCodeService;


    @Autowired
    private WriteApiDocService writeApiDocService;

    @Autowired
    private WriteDomainPlantDocService writeDomainPlantDocService;

    @Autowired
    private ComponentDecorateService componentDecorateService;

    @Autowired
    private ConfigFactory configFactory;

    @Autowired
    private ElementFactory elementFactory;


    /**
     * 写dynamicMapperxml文件
     * @param classBeanList
     */
    public void writeDynamicMapperXml(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.MAPPER_XML_DDD.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeDynamicMapperXml(classBeanList,writeFileService,null);
    }

    /**
     * 写AppExeImpl文件
     * @param classBeanList
     */
    public void writeAppExeImpl(List<ClassBean> classBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.CMD.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeAppExeImpl(classBeanList,writeFileService,null);
    }

    /**
     * 写AppExeInterface文件
     * @param interfaceBeanList
     */
    public void writeAppExeInterface(List<InterfaceBean> interfaceBeanList) {
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.EXE.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeAppExeInterface(interfaceBeanList,writeFileService,null);
    }


    /**
     * 写feignDTO文件
     * @param classBeanList
     */
    public void writeFeignDTO(List<ClassBean> classBeanList) {

        IWriteFileService writeFileService = appServiceConfig.getWriteServiceByModuleName(ModuleEnum.COLA_FEIGN_API.getModuleName());
        //只有springcloud cola需要写feign dto
        if (writeFileService == null
                || appServiceConfig.getApplicationType().equals(ModuleEnum.DUBBO_API.getAppName())
                || appServiceConfig.getApplicationType().equals(ModuleEnum.SPRING_BOOT_WEB.getAppName())) {
            return;
        }
        writeDynamicDDDModuleService.writeClassEle(classBeanList,TemplateFileEnum.DTO_DDD,TemplateFileEnum.DTO_DDD,null);
    }

    /**
     * 一次性生成所有表的模块代码，同时解析plantUML的类图结合起来
     * @param allMetaDataMap
     */
    public void writeAllWithDDD(Map<String,Object> allMetaDataMap){
        writeClassEle((List<ClassBean>)allMetaDataMap.get("domainbo"),TemplateFileEnum.BUSINESS_OBJECT_DDD,TemplateFileEnum.BUSINESS_OBJECT_DDD,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("domainvalueobject"),TemplateFileEnum.VALUE_OBJECT,TemplateFileEnum.VALUE_OBJECT,null);
        writeEnumEle((List<EnumBean>)allMetaDataMap.get("valueobjectenum"),null);
        writeEnumEle((List<EnumBean>)allMetaDataMap.get("apienum"),null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("domainmsg"),TemplateFileEnum.MESSAGE_BODY,TemplateFileEnum.BUSINESS_OBJECT_DDD,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("domainevent"),TemplateFileEnum.EVENT_BODY,TemplateFileEnum.BUSINESS_OBJECT_DDD,null);
        writeInterfaceEle((List<InterfaceBean>)allMetaDataMap.get("gataway"),TemplateFileEnum.GATAWAY,TemplateFileEnum.GATAWAY,null);
        writeInterfaceEle((List<InterfaceBean>)allMetaDataMap.get("domainservice"),TemplateFileEnum.SERVICE_DDD,TemplateFileEnum.SERVICE_DDD,null);
        writeInterfaceEle((List<InterfaceBean>)allMetaDataMap.get("repository"),TemplateFileEnum.GATAWAY,TemplateFileEnum.REPOSITORY,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("gatawayimpl"),TemplateFileEnum.GATAWAY_IMPL,TemplateFileEnum.GATAWAY_IMPL,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("serviceimpl"),TemplateFileEnum.GATAWAY_IMPL,TemplateFileEnum.SERVICE_IMPL,null);

        writeClassEle((List<ClassBean>)allMetaDataMap.get("repositoryimpl"),TemplateFileEnum.GATAWAY_IMPL,TemplateFileEnum.GATAWAY_IMPL,null);

        writeInterfaceEle((List<InterfaceBean>)allMetaDataMap.get("infrastacl"),TemplateFileEnum.ACL,TemplateFileEnum.ACL,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("infrastaclparam"),TemplateFileEnum.BUSINESS_OBJECT_DDD,TemplateFileEnum.ACL_PARAM,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("infrastaclimpl"),TemplateFileEnum.ACL_IMPL,TemplateFileEnum.ACL_IMPL,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("cmd"),TemplateFileEnum.BUSINESS_OBJECT_DDD, TemplateFileEnum.CMD,null);

        this.writeAppExeImpl((List<ClassBean>)allMetaDataMap.get("exeClass"));

        writeInterfaceEle((List<InterfaceBean>)allMetaDataMap.get("exeInterface"),TemplateFileEnum.GATAWAY,TemplateFileEnum.EXE,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("domainfactory"),TemplateFileEnum.FACTORY, TemplateFileEnum.FACTORY,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("applistener"),TemplateFileEnum.MQ_LISTENER, TemplateFileEnum.MQ_LISTENER,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("mqproducer"),TemplateFileEnum.MQ_PRODUCER, TemplateFileEnum.MQ_PRODUCER,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("mqconsumer"),TemplateFileEnum.MQ_CONSUMER, TemplateFileEnum.MQ_CONSUMER,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("mqhandler"),TemplateFileEnum.MQ_HANDLER, TemplateFileEnum.MQ_HANDLER,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("cache"),TemplateFileEnum.CACHE, TemplateFileEnum.CACHE,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("adaptervo"),TemplateFileEnum.VO_DDD, TemplateFileEnum.VO_DDD,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("controller"),TemplateFileEnum.CONTROLLER_DDD, TemplateFileEnum.CONTROLLER_DDD,null);
        writeInterfaceEle((List<InterfaceBean>)allMetaDataMap.get("derivefeign"),TemplateFileEnum.FEIGN_DDD,TemplateFileEnum.FEIGN,null);
        writeInterfaceEle((List<InterfaceBean>)allMetaDataMap.get("derivefacade"),TemplateFileEnum.FACADE_DDD,TemplateFileEnum.FACADE,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("derivefacadeimpl"),TemplateFileEnum.FACADE_IMPL_DDD, TemplateFileEnum.FACADE_IMPL,null);
        writeClassEle((List<ClassBean>)allMetaDataMap.get("derivedto"),TemplateFileEnum.DTO_DDD, TemplateFileEnum.DTO_DDD,null);

        writeInterfaceEle((List<InterfaceBean>)allMetaDataMap.get("dtoboconvert"),TemplateFileEnum.DTOBO_CONVERT,TemplateFileEnum.CONVERT,null);
        writeInterfaceEle((List<InterfaceBean>)allMetaDataMap.get("voboconvert"),TemplateFileEnum.VOBO_CONVERT,TemplateFileEnum.CONVERT,null);
        writeInterfaceEle((List<InterfaceBean>)allMetaDataMap.get("doboconvert"),TemplateFileEnum.DOBO_CONVERT,TemplateFileEnum.CONVERT,null);
        writeInterfaceEle((List<InterfaceBean>)allMetaDataMap.get("dynamicmapper"),TemplateFileEnum.MAPPER_DDD,TemplateFileEnum.MAPPER_DDD,null);

        List<ClassBean> dynamicMapperXmlBeanList = (List<ClassBean>)allMetaDataMap.get("dynamicmapperxml");
        this.writeDynamicMapperXml(dynamicMapperXmlBeanList);

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)allMetaDataMap.get("table");
        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)allMetaDataMap.get("columns");
        Map<String,Object> varMap = new HashMap<>();

        this.writeFeignDTO((List<ClassBean>)allMetaDataMap.get("derivedto"));
        writeInterfaceEle((List<InterfaceBean>)allMetaDataMap.get("convertdto2dto"),TemplateFileEnum.DOBO_CONVERT,TemplateFileEnum.CONVERT,null);

        varMap.put("projectBean",allMetaDataMap.get("projectBean"));
        tableBeanMap.forEach((k,v)->{
            varMap.put("table", v);
            varMap.put("columns", columnBeanListMap.get(k));
            varMap.put("package", allMetaDataMap.get("package"));
            varMap.put("author", allMetaDataMap.get("author"));
            varMap.put("packageInfrast", allMetaDataMap.get("packageInfrast"));
            varMap.put("packageDomain", allMetaDataMap.get("packageDomain"));

            String templateContent = freemarkerService.parseTpl(TemplateFileEnum.DATA_OBJECT.getTempFileName(),varMap);
            writeCodeService.writeDO(templateContent,v.getHumpClassName()+"DO");
        });


        List<InterfaceBean> facadeInterfaceBeanList = (List<InterfaceBean>)allMetaDataMap.get("derivefacade");
        if(CollectionUtils.isNotEmpty(facadeInterfaceBeanList)){
            for (InterfaceBean interfaceBean : facadeInterfaceBeanList){
                varMap.put("package", allMetaDataMap.get("package"));
                varMap.put("author", allMetaDataMap.get("author"));
                varMap.put("packageInfrast", allMetaDataMap.get("packageInfrast"));
                varMap.put("packageDomain", allMetaDataMap.get("packageDomain"));
                varMap.putAll(interfaceBean.buildVarMap());
                String templateContent = freemarkerService.parseTpl(TemplateFileEnum.TEST_DDD.getTempFileName(),varMap);
                writeCodeService.writeTest(templateContent,interfaceBean.getClassName());
            }
        }

        //处理自定义的代码模板
        for (Map.Entry<String,Object> entry : allMetaDataMap.entrySet()){
            String key = entry.getKey();
            if(!key.contains(":")){
                continue;
            }
            FtlBean ftlBean = FtlBean.getInstance(key);
            if(ClassEnum.isClass(ftlBean.getClassType())){
                List<ClassBean> classBeanList = (List<ClassBean>)entry.getValue();
                writeClassBean(classBeanList,ftlBean);
            }
            if(ClassEnum.isInterface(ftlBean.getClassType())){
                List<InterfaceBean> interfaceBeanList = (List<InterfaceBean>)entry.getValue();
                writeInterfaceBean(interfaceBeanList,ftlBean);
            }
            if(ClassEnum.isEnum(ftlBean.getClassType())){
                List<EnumBean> enumBeanList = (List<EnumBean>)entry.getValue();
            }

            if(ClassEnum.isPom(ftlBean.getClassType())){
                List<PomBean> pomBeanList = (List<PomBean>)entry.getValue();
                writePom(pomBeanList,ftlBean);
            }
        }

        //写公共服务类
        this.writeCommon(varMap,"");
        //写项目配置文件
        this.writeConfig(varMap);
        //渲染e-r图
        writeCodeService.writeERPicture(tableBeanMap,columnBeanListMap);

        //进行文档生成
        if(appServiceConfig.getApiDocGenerator()){
            this.writeHttpApiDoc((List<ClassBean>)allMetaDataMap.get("controller"),(List<ClassBean>)allMetaDataMap.get("adaptervo"));
            this.writeRpcApiDoc((List<InterfaceBean>)allMetaDataMap.get("derivefacade"),(List<ClassBean>)allMetaDataMap.get("derivedto"));
            this.writeRpcApiDoc((List<InterfaceBean>)allMetaDataMap.get("derivefeign"),(List<ClassBean>)allMetaDataMap.get("derivedto"));
        }

        //进行plantuml文档同步
        if(appServiceConfig.getPlantUMLSync()){
            writeDomainPlantDocService.writePlantUMLDomainDoc();
            List<String> dynamicInvokeFileList = (List<String>)allMetaDataMap.get("dynamicInvokeFileList");
            writeDomainPlantDocService.writePlantUMLSequenceDoc(dynamicInvokeFileList);
        }

        this.writeSqlDoc((List<String>)allMetaDataMap.get("sql"));
    }



    /**
     * 写http接口文档
     * @param controllerBeanList
     */
    private void writeHttpApiDoc(List<ClassBean> controllerBeanList,List<ClassBean> voBeanList){
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.API_HTTP.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeApiDocService.writeHttpApiDoc(controllerBeanList,writeFileService,voBeanList);
    }

    /**
     * 写rpc接口文档
     * @param facadeBeanList
     * @param dtoBeanList
     */
    private void writeRpcApiDoc(List<InterfaceBean> facadeBeanList,List<ClassBean> dtoBeanList){
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.API_RPC.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeApiDocService.writeRpcApiDoc(facadeBeanList,writeFileService,dtoBeanList);
    }


    /**
     * 写sql文档
     * @param sqlContentList
     */
    private void writeSqlDoc(List<String> sqlContentList){
        IWriteFileService writeFileService = appServiceConfig.getModuleWriteService(TemplateFileEnum.API_RPC.getTempFileName());
        if (writeFileService == null) {
            return;
        }
        writeApiDocService.writeSqlDoc(sqlContentList,writeFileService);
    }

    /**
     * 写公共基础服务类
     *
     * @param varMap
     */
    public void writeCommon(Map<String, Object> varMap, String fast) {

        Map<String,List<String>> initClassMap = appServiceConfig.getInitClassMapList();
        if(initClassMap == null || initClassMap.isEmpty()){
            return;
        }

        for (Map.Entry<String,List<String>> entry : initClassMap.entrySet()){
            for (String classTemplate : entry.getValue()){
                String clazzContent = freemarkerService.parseTplCommon(classTemplate, varMap);
                writeCodeService.writeInitClass(entry.getKey(),clazzContent,classTemplate);
            }
        }
    }

    /**
     * 写配置文件
     *
     * @param varMap
     */
    public void writeConfig(Map<String, Object> varMap) {

        List<ConfigFileBean> configFileBeanList = appServiceConfig.getConfigFileBeanList();
        if(CollectionUtils.isEmpty(configFileBeanList)){
            return;
        }
        ProjectBean projectBean = (ProjectBean)varMap.get("projectBean");
        for (ConfigFileBean configFileBean : configFileBeanList){
            ComponentConfigBean componentConfigBean = projectBean.getConfigFileMap().get(configFileBean.getConfigFileName());
            //非组件配置
            if(componentConfigBean == null){
                String configContent = freemarkerService.parseTplConfig(configFileBean.getTemplateName(), varMap);
                writeCodeService.writeConfig(configFileBean.getModuleName(),configContent,configFileBean.getConfigFileName()+"."+configFileBean.getConfigFileSuffix());
                continue;
            }
            else if(componentConfigBean != null && CollectionUtils.isNotEmpty(componentConfigBean.getConfigList()) && configFileBean.getConfigFileName().equals("application")){
                varMap.put("configFileList", componentConfigBean.getConfigList());
                String configContent = freemarkerService.parseTplConfig(configFileBean.getTemplateName(), varMap);
                writeCodeService.writeConfig(configFileBean.getModuleName(),configContent,configFileBean.getConfigFileName()+"."+configFileBean.getConfigFileSuffix());
            }else {
                String configContent = StringUtils.join(componentConfigBean.getConfigList(),"\n");
                writeCodeService.writeConfig(configFileBean.getModuleName(),configContent,configFileBean.getConfigFileName()+"."+configFileBean.getConfigFileSuffix());
            }
        }


    }

    /**
     * 写dynamicddd模块代码生成
     *
     * @param dynamicDDDMap
     */
    public void writeDynamicDDD(Map<String, Object> dynamicDDDMap) {
        IWriteFileService writeFileService = appServiceConfig.getDynamicDDDWriteService();
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeDynamicDDD(dynamicDDDMap, writeFileService);
    }


    /**
     * 写classBean
     * @param classBeanList
     * @param ftlBean
     */
    public void writeClassBean(List<ClassBean> classBeanList, FtlBean ftlBean){
        IWriteFileService writeFileService = appServiceConfig.getWriteServiceByModuleName(ftlBean.getModuleName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeClassBean(classBeanList,writeFileService,null,ftlBean.getCodeTempFileName());
    }

    /**
     * 写interfaceBean
     * @param interfaceBeanList
     * @param ftlBean
     */
    public void writeInterfaceBean(List<InterfaceBean> interfaceBeanList, FtlBean ftlBean){
        IWriteFileService writeFileService = appServiceConfig.getWriteServiceByModuleName(ftlBean.getModuleName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writeInterfaceBean(interfaceBeanList,writeFileService,ftlBean.getCodeTempFileName());
    }


    /**
     * 写pomBean
     * @param pomBeanList
     * @param ftlBean
     */
    public void writePom(List<PomBean> pomBeanList, FtlBean ftlBean){
        if(CollectionUtils.isEmpty(pomBeanList)){
            return;
        }
        IWriteFileService writeFileService = appServiceConfig.getWriteServiceByModuleName(ftlBean.getModuleName());
        if (writeFileService == null) {
            return;
        }
        writeDynamicDDDModuleService.writePomBean(pomBeanList,writeFileService,ftlBean.getCodeTempFileName());
    }


    /**
     * 纯数据库模式下写一些配置类文件
     */
    public void writeConfigForDBModel(Map<String,Object> varMap){
        PlantUmlContextBean plantUmlContextBean = new PlantUmlContextBean();
        plantUmlContextBean.setDerivedPlantUmlContextBean(new PlantUmlContextBean());
        plantUmlContextBean.setAppName(appServiceConfig.getApplicationName());
        plantUmlContextBean.setApplicationType(appServiceConfig.getApplicationType());
        Map<String,PomBean> pomBeanMap = appServiceConfig.getPomConfigMap();

        pomBeanMap.forEach((k,v)-> plantUmlContextBean.addPomBean(v));
        componentDecorateService.decorateComponent(plantUmlContextBean);
        elementFactory.dealPomDependency(plantUmlContextBean);

        Map<String, ElementBean> customElementMap = elementFactory.getElementMap(plantUmlContextBean);

        Map<String,List<PomBean>> pomBeanListMap = new HashMap<>();
        if(!customElementMap.isEmpty()){
            List<FtlBean> ftlBeanList = appServiceConfig.getCustomCodeFtlList();
            Map<String,FtlBean> ftlBeanMap = ftlBeanList.stream().collect(Collectors.toMap(FtlBean::getFtlStr, o->o));
            for (Map.Entry<String,ElementBean> entry : customElementMap.entrySet()){
                FtlBean ftlBean = ftlBeanMap.get(entry.getKey());
                if(ClassEnum.isPom(ftlBean.getClassType())){
                    pomBeanListMap.put(ftlBean.getFtlStr(),entry.getValue().getPomBeanList());
                }
            }
        }


        for (Map.Entry<String,List<PomBean>> entry : pomBeanListMap.entrySet()){
            FtlBean ftlBean = FtlBean.getInstance(entry.getKey());
            if(ClassEnum.isPom(ftlBean.getClassType())){
                List<PomBean> pomBeanList = entry.getValue();
                writePom(pomBeanList,ftlBean);
            }
        }


        //进行框架中间件和组件的包装和注册
        componentDecorateService.decorateComponent(plantUmlContextBean);
        ProjectBean projectBean = configFactory.getProjectBean(plantUmlContextBean);
        varMap.put("projectBean",projectBean);
        this.writeConfig(varMap);
    }

}
