package com.tianhua.codemaker.service.registry;

import com.alibaba.fastjson.JSON;
import com.tianhua.codemaker.api.DomainElementHandler;
import com.tianhua.codemaker.api.IJavsAdapterSerivce;
import com.tianhua.codemaker.api.IValidateService;
import com.tianhua.codemaker.app.dynamicddd.ElementHandlerContainer;
import com.tianhua.codemaker.bean.ColumnBean;
import com.tianhua.codemaker.bean.component.ComponentConfigBean;
import com.tianhua.codemaker.bean.component.ComponentContextBean;
import com.tianhua.codemaker.bean.config.FtlBean;
import com.tianhua.codemaker.bean.config.PomBean;
import com.tianhua.codemaker.bean.config.ProjectBean;
import com.tianhua.codemaker.config.ConfigFactory;
import com.tianhua.codemaker.enums.ClassEnum;
import com.tianhua.codemaker.enums.CodeElementEnum;
import com.tianhua.codemaker.service.AnnotationParseService;
import com.tianhua.codemaker.service.adapter.JavsAdapterBeanFactory;
import com.tianhua.codemaker.service.component.ComponentDecorateService;
import com.tianhua.codemaker.service.invoker.InvokeElementRegistService;
import com.tianhua.codemaker.service.invoker.InvokeSequenceService;
import com.tianhua.codemaker.app.dynamicddd.derivedhandler.*;
import com.tianhua.codemaker.bean.TableBean;
import com.tianhua.codemaker.bean.dddelement.*;
import com.tianhua.codemaker.bean.dddelementderive.*;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.bean.plantuml.PlantUmlContextBean;
import com.tianhua.codemaker.config.AppServiceConfig;
import com.tianhua.codemaker.enums.DomainDerivedElementEnum;
import com.tianhua.codemaker.enums.DomainElementEnum;
import com.tianhua.codemaker.service.plantuml.ReadDomainPlantDocService;
import com.tianhua.codemaker.service.registry.factory.ElementFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description: AbstractVarRegistry <br>
 * date: 2020/7/7 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
@Slf4j
public abstract   class AbstractVarRegistry {
    @Autowired
    private ReadDomainPlantDocService readFileService;

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Resource(name = "domainBoElementHandler")
    private DomainElementHandler domainElementHandler;

    @Resource(name = "valueObjectElementHandler")
    private DomainElementHandler valueObjectElementHandler;

    @Resource(name = "repositoryElementHandler")
    private DomainElementHandler repositoryElementHandler;

    @Resource(name = "domainGatawayElementHandler")
    private DomainElementHandler domainGatawayElementHandler;

    @Resource(name = "msgBodyElementHandler")
    private DomainElementHandler msgBodyElementHandler;

    @Resource(name = "domainFactoryElementHandler")
    private DomainElementHandler domainFactoryElementHandler;


    @Resource(name = "domainServiceElementHandler")
    private DomainElementHandler domainServiceElementHandler;

    @Resource(name = "infrastAclElementHandler")
    private DomainElementHandler infrastAclElementHandler;

    @Resource(name = "appCmdElementHandler")
    private DomainElementHandler appCmdElementHandler;

    @Resource(name = "appExeElementHandler")
    private DomainElementHandler appExeElementHandler;

    @Resource(name = "eventElementHandler")
    private DomainElementHandler eventElementHandler;

    @Resource(name = "appListenerElementHandler")
    private DomainElementHandler appListenerElementHandler;

    @Resource(name = "mqHandlerElementHandler")
    private DomainElementHandler mqHandlerElementHandler;

    @Resource(name = "mqConsumerElementHandler")
    private DomainElementHandler mqConsumerElementHandler;

    @Resource(name = "mqProducerElementHandler")
    private DomainElementHandler mqProducerElementHandler;

    @Resource(name = "dynamicMapperElementHandler")
    private DomainElementHandler dynamicMapperElementHandler;


    @Resource(name = "cacheElementHandler")
    private DomainElementHandler cacheElementHandler;





    //------------------?????????????????????????????????????????????

    @Resource(name = "derivedDTOElementHandler")
    private DerivedDTOElementHandler derivedDTOElementHandler;

    @Resource(name = "derivedFacadeElementHandler")
    private DerivedFacadeElementHandler derivedFacadeElementHandler;

    @Resource(name = "derivedFacadeImplElementHandler")
    private DerivedFacadeImplElementHandler derivedFacadeImplElementHandler;

    @Resource(name = "derivedDTOBOConvertElementHandler")
    private DerivedDTOBOConvertElementHandler derivedDTOBOConvertElementHandler;

    @Resource(name = "derivedDOBOConvertElementHandler")
    private DerivedDOBOConvertElementHandler derivedDOBOConvertElementHandler;

    @Resource(name = "derivedVOElementHandler")
    private DerivedVOElementHandler derivedVOElementHandler;

    @Resource(name = "derivedControllerElementHandler")
    private DerivedControllerElementHandler derivedControllerElementHandler;

    @Resource(name = "derivedVOBOConvertElementHandler")
    private DerivedVOBOConvertElementHandler derivedVOBOConvertElementHandler;

    @Resource(name = "derivedEnumElementHandler")
    private DerivedEnumElementHandler derivedEnumElementHandler;

    @Resource(name = "derivedRepositoryImplElementHandler")
    private DerivedRepositoryImplElementHandler derivedRepositoryImplElementHandler;

    @Resource(name = "derivedGatawayImplElementHandler")
    private DerivedGatawayImplElementHandler derivedGatawayImplElementHandler;

    @Resource(name = "derivedServiceImplElementHandler")
    private DerivedServiceImplElementHandler derivedServiceImplElementHandler;

    @Resource(name = "derivedInfrastAclImplElementHandler")
    private DerivedInfrastAclImplElementHandler derivedInfrastAclImplElementHandler;


    @Resource(name = "derivedFeignElementHandler")
    private DerivedFeignElementHandler derivedFeignElementHandler;


    @Resource(name = "derivedDTO2DTOConvertElementHandler")
    private DerivedDTO2DTOConvertElementHandler derivedDTO2DTOConvertElementHandler;


    //------------------?????????????????????????????????
    @Autowired
    private InvokeSequenceService invokeSequenceService;

    @Autowired
    private InvokeElementRegistService invokeElementRegistService;

    //------------------?????????????????????????????????
    @Autowired
    private ComponentDecorateService componentDecorateService;


    @Autowired
    protected ElementHandlerContainer elementHandlerContainer;


    @Autowired
    private AnnotationParseService annotationParseService;

    @Autowired
    private JavsAdapterBeanFactory javsAdapterBeanFactory;


    @Autowired
    private ConfigFactory configFactory;

    @Autowired
    private ElementFactory elementFactory;

    /**
     * ??????????????????
     * @return
     */
    public Map<String,Object> getTemplateVar(){
        Map<String,Object> varMap = getRegistVarMap();

        varMap.putIfAbsent("package",appServiceConfig.getPackage());
        varMap.putIfAbsent("packageInfrast",appServiceConfig.getPackage()+".infrast");
        varMap.putIfAbsent("packageDomain",appServiceConfig.getPackage()+".domain");

        varMap.putIfAbsent("author",appServiceConfig.getAuthor());
        String plantUMLName = appServiceConfig.getPlantUMLFileName();
        if(StringUtils.isEmpty(plantUMLName)){
            return varMap;
        }

        PlantUmlContextBean plantUmlContextBean = readFileService.getPlantUmlContextBean(plantUMLName);

        if(plantUmlContextBean == null){
            return varMap;
        }

        //?????????????????????plantuml????????????
        annotationParseService.dealPlantUmlContextAnnotation(plantUmlContextBean);


        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)varMap.get("table");


        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)varMap.get("columns");

        plantUmlContextBean.setAppName(appServiceConfig.getApplicationName());
        plantUmlContextBean.setApplicationType(appServiceConfig.getApplicationType());
        appServiceConfig.getPomConfigMap().forEach((k,v)-> plantUmlContextBean.addPomBean(v));

        tableBeanMap.forEach((k,v)->{
            ClassBean classBean = v.convertToClassBean(appServiceConfig.getPackage()+".infrast.dao.dataobject",columnBeanListMap.get(k));

            InterfaceBean mapperInterface = v.convertToMapperInterface(appServiceConfig.getPackage()+".infrast.dao.mapper",columnBeanListMap.get(k), appServiceConfig.containsMybatisPlus());
            plantUmlContextBean.getClassBeanMap().put(classBean.getClassName(),classBean);
            plantUmlContextBean.getInterfaceBeanMap().put(mapperInterface.getClassName(),mapperInterface);

        });

        //?????????????????????????????????????????????????????????handler????????????????????????????????????????????????
        plantUmlContextBean.setDerivedPlantUmlContextBean(new PlantUmlContextBean());

        //??????
        varMap.put("dynamicddd","dynamicddd");
        varMap.put("dynamicInvokeFileList",plantUmlContextBean.getDynamicInvokeFileList());


        //????????????????????????
        invokeElementRegistService.registDefaultClass(plantUmlContextBean);


        DomainBoElementBean domainBoElementBean = (DomainBoElementBean)domainElementHandler.getElementBeanList(plantUmlContextBean);
        ValueObjectElementBean valueObjectElementBean = (ValueObjectElementBean)valueObjectElementHandler.getElementBeanList(plantUmlContextBean);
        RepositoryElementBean repositoryElementBean = (RepositoryElementBean)repositoryElementHandler.getElementBeanList(plantUmlContextBean);
        GatawayElementBean gatawayElementBean = (GatawayElementBean)domainGatawayElementHandler.getElementBeanList(plantUmlContextBean);
        DomainMsgBodyElementBean domainMsgBodyElementBean = (DomainMsgBodyElementBean)msgBodyElementHandler.getElementBeanList(plantUmlContextBean);
        FactoryElementBean factoryElementBean = (FactoryElementBean)domainFactoryElementHandler.getElementBeanList(plantUmlContextBean);
        ServiceElementBean serviceElementBean = (ServiceElementBean)domainServiceElementHandler.getElementBeanList(plantUmlContextBean);

        InfrastAclElementBean infrastAclElementBean = (InfrastAclElementBean)infrastAclElementHandler.getElementBeanList(plantUmlContextBean);
        CommandElementBean commandElementBean = (CommandElementBean)appCmdElementHandler.getElementBeanList(plantUmlContextBean);
        ExecutorElementBean executorElementBean = (ExecutorElementBean)appExeElementHandler.getElementBeanList(plantUmlContextBean);
        DomainEventElementBean domainEventElementBean = (DomainEventElementBean)eventElementHandler.getElementBeanList(plantUmlContextBean);
        AppListenerElementBean appListenerElementBean = (AppListenerElementBean)appListenerElementHandler.getElementBeanList(plantUmlContextBean);
        MqConsumerElementBean  mqConsumerElementBean = (MqConsumerElementBean)mqConsumerElementHandler.getElementBeanList(plantUmlContextBean);
        MqProducerElementBean  mqProducerElementBean = (MqProducerElementBean)mqProducerElementHandler.getElementBeanList(plantUmlContextBean);
        MqHandlerElementBean  mqHandlerElementBean = (MqHandlerElementBean)mqHandlerElementHandler.getElementBeanList(plantUmlContextBean);
        CacheElementBean  cacheElementBean = (CacheElementBean)cacheElementHandler.getElementBeanList(plantUmlContextBean);


        //???????????????
        DtoElementBean dtoElementBean = derivedDTOElementHandler.getElementBeanList(plantUmlContextBean);
        FacadeElementBean facadeElementBean = derivedFacadeElementHandler.getElementBeanList(plantUmlContextBean);
        FacadeImplElementBean facadeImplElementBean = derivedFacadeImplElementHandler.getElementBeanList(plantUmlContextBean);
        DtoBoConvertElementBean dtoBoConvertElementBean = derivedDTOBOConvertElementHandler.getElementBeanList(plantUmlContextBean);
        DoBoConvertElementBean doBoConvertElementBean = derivedDOBOConvertElementHandler.getElementBeanList(plantUmlContextBean);
        VoElementBean voElementBean = derivedVOElementHandler.getElementBeanList(plantUmlContextBean);
        ControllerElementBean controllerElementBean = derivedControllerElementHandler.getElementBeanList(plantUmlContextBean);
        VoBoConvertElementBean voBoConvertElementBean = derivedVOBOConvertElementHandler.getElementBeanList(plantUmlContextBean);
        EnumElementBean enumElementBean = derivedEnumElementHandler.getElementBeanList(plantUmlContextBean);
        GatawayImplElementBean gatawayImplElementBean = derivedGatawayImplElementHandler.getElementBeanList(plantUmlContextBean);
        ServiceImplElementBean serviceImplElementBean = derivedServiceImplElementHandler.getElementBeanList(plantUmlContextBean);

        RepositoryImplElementBean repositoryImplElementBean = derivedRepositoryImplElementHandler.getElementBeanList(plantUmlContextBean);
        InfrastAclImplElementBean infrastAclImplElementBean = derivedInfrastAclImplElementHandler.getElementBeanList(plantUmlContextBean);
        FeignElementBean feignElementBean = derivedFeignElementHandler.getElementBeanList(plantUmlContextBean);


        List<IValidateService> validateServiceList = appServiceConfig.getValidateServiceBeanList();
        if(CollectionUtils.isNotEmpty(validateServiceList)){
            validateServiceList.stream().forEach(validateService -> validateService.dealValidate(plantUmlContextBean));
        }

        //????????????????????????????????????????????????
        componentDecorateService.decorateComponent(plantUmlContextBean);


        //??????????????????????????????
        invokeSequenceService.exeDynamicInvoke(plantUmlContextBean);

        elementFactory.dealPomDependency(plantUmlContextBean);

        //??????????????????mapper?????????????????????????????????????????????????????????
        DynamicMapperElementBean dynamicMapperElementBean = (DynamicMapperElementBean)dynamicMapperElementHandler.getElementBeanList(plantUmlContextBean);
        //??????????????????????????????????????????????????????
        Dto2DtoConvertElementBean dto2DtoConvertElementBean = derivedDTO2DTOConvertElementHandler.getElementBeanList(plantUmlContextBean);



        //??????javs????????????
        if(appServiceConfig.enableJavsScript()){
            log.info("????????????javs??????..............");
            IJavsAdapterSerivce javsAdapterSerivce = javsAdapterBeanFactory.getJavsAdapterService();
            javsAdapterSerivce.translateJavsScript(plantUmlContextBean,appServiceConfig.getJavsProjectOutPath());
            javsAdapterSerivce.generateJavsScriptProject(plantUmlContextBean,appServiceConfig.getJavsProjectOutPath());
            log.info("??????javs????????????..............");
        }

        Map<String,ElementBean> customElementMap = elementFactory.getElementMap(plantUmlContextBean);
        if(!customElementMap.isEmpty()){
            List<FtlBean> ftlBeanList = appServiceConfig.getCustomCodeFtlList();
            Map<String,FtlBean> ftlBeanMap = ftlBeanList.stream().collect(Collectors.toMap(FtlBean::getFtlStr,o->o));
            for (Map.Entry<String,ElementBean> entry : customElementMap.entrySet()){
                FtlBean ftlBean = ftlBeanMap.get(entry.getKey());
                if(ClassEnum.isClass(ftlBean.getClassType())){
                    varMap.put(ftlBean.getFtlStr(),entry.getValue().refreshClass(plantUmlContextBean, ftlBean.getCodeTempFileName()).getClassBeanList());
                }
                if(ClassEnum.isInterface(ftlBean.getClassType())){
                    varMap.put(ftlBean.getFtlStr(),entry.getValue().refreshInterface(plantUmlContextBean, ftlBean.getCodeTempFileName()).getInterfaceBeanList());
                }
                if(ClassEnum.isEnum(ftlBean.getClassType())){
                    varMap.put(ftlBean.getFtlStr(),entry.getValue().getClassBeanList());
                }
                if(ClassEnum.isPom(ftlBean.getClassType())){
                    varMap.put(ftlBean.getFtlStr(),entry.getValue().getPomBeanList());
                }
            }
        }


        varMap.put("domainevent",domainEventElementBean.getClassBeanList());
        varMap.put("domainbo",domainBoElementBean.getClassBeanList());
        varMap.put("domainvalueobject",valueObjectElementBean.getClassBeanList());
        varMap.put("valueobjectenum",valueObjectElementBean.getEnumBeanList());
        varMap.put("repository",repositoryElementBean.refreshInterface(plantUmlContextBean,DomainElementEnum.REPOSITORY.getElement()).getInterfaceBeanList());
        varMap.put("gataway",gatawayElementBean.refreshInterface(plantUmlContextBean,DomainElementEnum.GATAWAY.getElement()).getInterfaceBeanList());
        varMap.put("domainmsg",domainMsgBodyElementBean.getClassBeanList());
        varMap.put("domainservice",serviceElementBean.refreshClass(plantUmlContextBean,DomainElementEnum.SERVICE.getElement()).getInterfaceBeanList());

        varMap.put("domainfactory",factoryElementBean.refreshClass(plantUmlContextBean,DomainElementEnum.FACTORY.getElement()).getClassBeanList());
        varMap.put("infrastacl",infrastAclElementBean.getInterfaceBeanList());
        varMap.put("infrastaclparam",infrastAclElementBean.getClassBeanList());
        varMap.put("cmd",commandElementBean.getClassBeanList());
        varMap.put("exeClass",executorElementBean.refreshClass(plantUmlContextBean,DomainElementEnum.EXECUTOR.getElement()).getClassBeanList());
        varMap.put("exeInterface",executorElementBean.getInterfaceBeanList());
        varMap.put("applistener",appListenerElementBean.getClassBeanList());
        varMap.put("mqproducer",mqProducerElementBean.getClassBeanList());
        varMap.put("mqconsumer",mqConsumerElementBean.getClassBeanList());
        varMap.put("mqhandler",mqHandlerElementBean.getClassBeanList());
        varMap.put("cache",cacheElementBean.getClassBeanList());

        //???????????????
        varMap.put("derivedto",dtoElementBean.refreshClass(plantUmlContextBean, DomainDerivedElementEnum.DTO.getElement()).getClassBeanList());
        varMap.put("derivefacade",facadeElementBean.refreshInterface(plantUmlContextBean,DomainDerivedElementEnum.FACADE.getElement()).getInterfaceBeanList());
        varMap.put("derivefeign",feignElementBean.refreshInterface(plantUmlContextBean,DomainDerivedElementEnum.FEIGN.getElement()).getInterfaceBeanList());
        varMap.put("derivefacadeimpl",facadeImplElementBean.refreshClass(plantUmlContextBean, DomainDerivedElementEnum.FACADE_IMPL.getElement()).getClassBeanList());
        varMap.put("dtoboconvert",dtoBoConvertElementBean.getInterfaceBeanList());
        varMap.put("doboconvert",doBoConvertElementBean.getInterfaceBeanList());
        varMap.put("adaptervo",voElementBean.refreshClass(plantUmlContextBean, DomainDerivedElementEnum.VO.getElement()).getClassBeanList());
        varMap.put(CodeElementEnum.CONTROLLER.getCode(),controllerElementBean.refreshClass(plantUmlContextBean,DomainDerivedElementEnum.CONTROLLER.getElement()).getClassBeanList());
        varMap.put("voboconvert",voBoConvertElementBean.getInterfaceBeanList());
        varMap.put("apienum",enumElementBean.getEnumBeanList());
        varMap.put("gatawayimpl",gatawayImplElementBean.refreshClass(plantUmlContextBean, DomainElementEnum.GATAWAY_IMPL.getElement()).getClassBeanList());
        varMap.put("serviceimpl",gatawayImplElementBean.refreshClass(plantUmlContextBean, DomainElementEnum.SERVICE_IMPL.getElement()).getClassBeanList());

        varMap.put("repositoryimpl",repositoryImplElementBean.refreshClass(plantUmlContextBean, DomainElementEnum.REPOSITORY_IMPL.getElement()).getClassBeanList());
        varMap.put("infrastaclimpl",infrastAclImplElementBean.refreshClass(plantUmlContextBean, DomainElementEnum.ADAPTER_ACL_IMPL.getElement()).getClassBeanList());
        varMap.put("dynamicmapper",dynamicMapperElementBean.getInterfaceBeanList());
        varMap.put("dynamicmapperxml",dynamicMapperElementBean.getClassBeanList());
        varMap.put("convertdto2dto",dto2DtoConvertElementBean.getInterfaceBeanList());
        varMap.put("projectBean",configFactory.getProjectBean(plantUmlContextBean));

        return varMap;
    }

    public  abstract Map<String,Object> getRegistVarMap();



}
