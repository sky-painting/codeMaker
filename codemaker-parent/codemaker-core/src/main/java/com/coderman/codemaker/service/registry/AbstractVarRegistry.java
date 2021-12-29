package com.coderman.codemaker.service.registry;

import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.ColumnBean;
import com.coderman.codemaker.service.component.ComponentDecorateService;
import com.coderman.codemaker.service.invoker.InvokeElementRegistService;
import com.coderman.codemaker.service.invoker.InvokeSequenceService;
import com.coderman.codemaker.app.dynamicddd.derivedhandler.*;
import com.coderman.codemaker.bean.TableBean;
import com.coderman.codemaker.bean.dddelement.*;
import com.coderman.codemaker.bean.dddelementderive.*;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.enums.DomainDerivedElementEnum;
import com.coderman.codemaker.enums.DomainElementEnum;
import com.coderman.codemaker.service.plantuml.ReadDomainPlantDocService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * description: AbstractVarRegistry <br>
 * date: 2020/7/7 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
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

    //------------------以下为领域元素派生类对象处理器

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

    @Resource(name = "derivedInfrastAclImplElementHandler")
    private DerivedInfrastAclImplElementHandler derivedInfrastAclImplElementHandler;


    @Resource(name = "derivedFeignElementHandler")
    private DerivedFeignElementHandler derivedFeignElementHandler;


    @Resource(name = "derivedDTO2DTOConvertElementHandler")
    private DerivedDTO2DTOConvertElementHandler derivedDTO2DTOConvertElementHandler;


    //------------------以下为动态调用处理服务
    @Autowired
    private InvokeSequenceService invokeSequenceService;

    @Autowired
    private InvokeElementRegistService invokeElementRegistService;

    //------------------以下为组件装饰处理服务
    @Autowired
    private ComponentDecorateService componentDecorateService;



    /**
     * 合并全局配置
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

        Map<String, TableBean> tableBeanMap = (Map<String, TableBean>)varMap.get("table");


        Map<String, List<ColumnBean>> columnBeanListMap = (Map<String, List<ColumnBean>>)varMap.get("columns");

        plantUmlContextBean.setAppName(appServiceConfig.getApplicationName());
        plantUmlContextBean.setApplicationType(appServiceConfig.getApplicationType());


        tableBeanMap.forEach((k,v)->{
            ClassBean classBean = v.convertToClassBean(appServiceConfig.getPackage()+".infrast.dao.dataobject",columnBeanListMap.get(k));
            InterfaceBean mapperInterface = v.convertToMapperInterface(appServiceConfig.getPackage()+".infrast.dao.mapper",columnBeanListMap.get(k));
            plantUmlContextBean.getClassBeanMap().put(classBean.getClassName(),classBean);
            plantUmlContextBean.getInterfaceBeanMap().put(mapperInterface.getClassName(),mapperInterface);

        });

        //这里统一注册派生类对象上下文，后续各种handler内部逻辑则不需要再判断是否为空了
        plantUmlContextBean.setDerivedPlantUmlContextBean(new PlantUmlContextBean());


        //打标
        varMap.put("dynamicddd","dynamicddd");

        DomainBoElementBean domainBoElementBean = (DomainBoElementBean)domainElementHandler.getElementBeanList(plantUmlContextBean);
        ValueObjectElementBean valueObjectElementBean = (ValueObjectElementBean)valueObjectElementHandler.getElementBeanList(plantUmlContextBean);
        RepositoryElementBean repositoryElementBean = (RepositoryElementBean)repositoryElementHandler.getElementBeanList(plantUmlContextBean);
        GatawayElementBean gatawayElementBean = (GatawayElementBean)domainGatawayElementHandler.getElementBeanList(plantUmlContextBean);
        DomainMsgBodyElementBean domainMsgBodyElementBean = (DomainMsgBodyElementBean)msgBodyElementHandler.getElementBeanList(plantUmlContextBean);
        FactoryElementBean factoryElementBean = (FactoryElementBean)domainFactoryElementHandler.getElementBeanList(plantUmlContextBean);
        InfrastAclElementBean infrastAclElementBean = (InfrastAclElementBean)infrastAclElementHandler.getElementBeanList(plantUmlContextBean);
        CommandElementBean commandElementBean = (CommandElementBean)appCmdElementHandler.getElementBeanList(plantUmlContextBean);
        ExecutorElementBean executorElementBean = (ExecutorElementBean)appExeElementHandler.getElementBeanList(plantUmlContextBean);
        DomainEventElementBean domainEventElementBean = (DomainEventElementBean)eventElementHandler.getElementBeanList(plantUmlContextBean);
        AppListenerElementBean appListenerElementBean = (AppListenerElementBean)appListenerElementHandler.getElementBeanList(plantUmlContextBean);
        MqConsumerElementBean  mqConsumerElementBean = (MqConsumerElementBean)mqConsumerElementHandler.getElementBeanList(plantUmlContextBean);
        MqProducerElementBean  mqProducerElementBean = (MqProducerElementBean)mqProducerElementHandler.getElementBeanList(plantUmlContextBean);
        MqHandlerElementBean  mqHandlerElementBean = (MqHandlerElementBean)mqHandlerElementHandler.getElementBeanList(plantUmlContextBean);
        CacheElementBean  cacheElementBean = (CacheElementBean)cacheElementHandler.getElementBeanList(plantUmlContextBean);


        //处理派生类
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
        RepositoryImplElementBean repositoryImplElementBean = derivedRepositoryImplElementHandler.getElementBeanList(plantUmlContextBean);
        InfrastAclImplElementBean infrastAclImplElementBean = derivedInfrastAclImplElementHandler.getElementBeanList(plantUmlContextBean);
        FeignElementBean feignElementBean = derivedFeignElementHandler.getElementBeanList(plantUmlContextBean);



        //进行框架中间件和组件的包装和注册
        componentDecorateService.decorateComponent(plantUmlContextBean);

        //注册独立类和工具
        invokeElementRegistService.registDefaultClass(plantUmlContextBean);

        //最后进行动态调用绘制
        invokeSequenceService.exeDynamicInvoke(plantUmlContextBean);

        //这里增加动态mapper的代码生成，补足调用时序图中缺失的方法
        DynamicMapperElementBean dynamicMapperElementBean = (DynamicMapperElementBean)dynamicMapperElementHandler.getElementBeanList(plantUmlContextBean);
        //在动态调用绘制之后进行处理，比较特殊
        Dto2DtoConvertElementBean dto2DtoConvertElementBean = derivedDTO2DTOConvertElementHandler.getElementBeanList(plantUmlContextBean);



        varMap.put("domainevent",domainEventElementBean.getClassBeanList());
        varMap.put("domainbo",domainBoElementBean.getClassBeanList());
        varMap.put("domainvalueobject",valueObjectElementBean.getClassBeanList());
        varMap.put("valueobjectenum",valueObjectElementBean.getEnumBeanList());
        varMap.put("repository",repositoryElementBean.refreshInterface(plantUmlContextBean,DomainElementEnum.REPOSITORY.getElement()).getInterfaceBeanList());
        varMap.put("gataway",gatawayElementBean.refreshInterface(plantUmlContextBean,DomainElementEnum.GATAWAY.getElement()).getInterfaceBeanList());
        varMap.put("domainmsg",domainMsgBodyElementBean.getClassBeanList());
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

        //处理派生类
        varMap.put("derivedto",dtoElementBean.getClassBeanList());
        varMap.put("derivefacade",facadeElementBean.refreshInterface(plantUmlContextBean,DomainDerivedElementEnum.FACADE.getElement()).getInterfaceBeanList());
        varMap.put("derivefeign",feignElementBean.refreshInterface(plantUmlContextBean,DomainDerivedElementEnum.FEIGN.getElement()).getInterfaceBeanList());
        varMap.put("derivefacadeimpl",facadeImplElementBean.refreshClass(plantUmlContextBean, DomainDerivedElementEnum.FACADE_IMPL.getElement()).getClassBeanList());
        varMap.put("dtoboconvert",dtoBoConvertElementBean.getInterfaceBeanList());
        varMap.put("doboconvert",doBoConvertElementBean.getInterfaceBeanList());
        varMap.put("adaptervo",voElementBean.getClassBeanList());
        varMap.put("controller",controllerElementBean.refreshClass(plantUmlContextBean,DomainDerivedElementEnum.CONTROLLER.getElement()).getClassBeanList());
        varMap.put("voboconvert",voBoConvertElementBean.getInterfaceBeanList());
        varMap.put("apienum",enumElementBean.getEnumBeanList());
        varMap.put("gatawayimpl",gatawayImplElementBean.refreshClass(plantUmlContextBean, DomainElementEnum.GATAWAY_IMPL.getElement()).getClassBeanList());
        varMap.put("repositoryimpl",repositoryImplElementBean.refreshClass(plantUmlContextBean, DomainElementEnum.REPOSITORY_IMPL.getElement()).getClassBeanList());
        varMap.put("infrastaclimpl",infrastAclImplElementBean.refreshClass(plantUmlContextBean, DomainElementEnum.ADAPTER_ACL_IMPL.getElement()).getClassBeanList());
        varMap.put("dynamicmapper",dynamicMapperElementBean.getInterfaceBeanList());
        varMap.put("dynamicmapperxml",dynamicMapperElementBean.getClassBeanList());
        varMap.put("convertdto2dto",dto2DtoConvertElementBean.getInterfaceBeanList());

        return varMap;
    }

    public  abstract Map<String,Object> getRegistVarMap();

}
