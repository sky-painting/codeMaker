package com.coderman.codemaker.service.registry;

import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.app.dynamicddd.derivedhandler.DerivedInfrastAclImplElementHandler;
import com.coderman.codemaker.bean.dddelement.*;
import com.coderman.codemaker.bean.dddelementderive.InfrastAclImplElementBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.config.ProjectTemplateDynamicDDDConfig;
import com.coderman.codemaker.enums.DomainElementEnum;
import com.coderman.codemaker.service.AbstractVarRegistry;
import com.coderman.codemaker.service.ReadPlantUMLFileServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * date: 2021/6/29
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "dynamicDDDVarRegistry")
public class DynamicDDDVarRegistry extends AbstractVarRegistry {

    @Autowired
    //private ReadPlantUMLFileService readFileService;
   private ReadPlantUMLFileServiceV2 readFileService;

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


    @Resource(name = "derivedInfrastAclImplElementHandler")
    private DerivedInfrastAclImplElementHandler derivedInfrastAclImplElementHandler;

    @Resource(name = "mqHandlerElementHandler")
    private DomainElementHandler mqHandlerElementHandler;

    @Resource(name = "mqConsumerElementHandler")
    private DomainElementHandler mqConsumerElementHandler;

    @Resource(name = "mqProducerElementHandler")
    private DomainElementHandler mqProducerElementHandler;

    @Resource(name = "eventElementHandler")
    private DomainElementHandler eventElementHandler;






    @Autowired
    private ProjectTemplateDynamicDDDConfig projectTemplateDynamicDDDConfig;

    @Override
    public Map<String, Object> getRegistVarMap() {
        PlantUmlContextBean plantUmlContextBean = readFileService.getPlantUmlContextBean(projectTemplateDynamicDDDConfig.getPlantumlFileName());
        DomainBoElementBean domainBoElementBean = (DomainBoElementBean)domainElementHandler.getElementBeanList(plantUmlContextBean);
        ValueObjectElementBean valueObjectElementBean = (ValueObjectElementBean)valueObjectElementHandler.getElementBeanList(plantUmlContextBean);
        RepositoryElementBean repositoryElementBean = (RepositoryElementBean)repositoryElementHandler.getElementBeanList(plantUmlContextBean);
        GatawayElementBean gatawayElementBean = (GatawayElementBean)domainGatawayElementHandler.getElementBeanList(plantUmlContextBean);
        DomainMsgBodyElementBean domainMsgBodyElementBean = (DomainMsgBodyElementBean)msgBodyElementHandler.getElementBeanList(plantUmlContextBean);
        FactoryElementBean factoryElementBean = (FactoryElementBean)domainFactoryElementHandler.getElementBeanList(plantUmlContextBean);
        InfrastAclElementBean infrastAclElementBean = (InfrastAclElementBean)infrastAclElementHandler.getElementBeanList(plantUmlContextBean);
        CommandElementBean commandElementBean = (CommandElementBean)appCmdElementHandler.getElementBeanList(plantUmlContextBean);
        ExecutorElementBean executorElementBean = (ExecutorElementBean)appExeElementHandler.getElementBeanList(plantUmlContextBean);
        InfrastAclImplElementBean infrastAclImplElementBean = derivedInfrastAclImplElementHandler.getElementBeanList(plantUmlContextBean);
        MqConsumerElementBean  mqConsumerElementBean = (MqConsumerElementBean)mqConsumerElementHandler.getElementBeanList(plantUmlContextBean);
        MqProducerElementBean  mqProducerElementBean = (MqProducerElementBean)mqProducerElementHandler.getElementBeanList(plantUmlContextBean);
        MqHandlerElementBean  mqHandlerElementBean = (MqHandlerElementBean)mqHandlerElementHandler.getElementBeanList(plantUmlContextBean);
        DomainEventElementBean domainEventElementBean = (DomainEventElementBean)eventElementHandler.getElementBeanList(plantUmlContextBean);

        Map<String, Object> map = new HashMap<>();

        map.put("domainbo",domainBoElementBean.getClassBeanList());
        map.put("domainvalueobject",valueObjectElementBean.getClassBeanList());
        map.put("valueobjectenum",valueObjectElementBean.getEnumBeanList());
        map.put("repository",repositoryElementBean.getInterfaceBeanList());
        map.put("gataway",gatawayElementBean.getInterfaceBeanList());
        map.put("domainmsg",domainMsgBodyElementBean.getClassBeanList());
        map.put("domainfactory",factoryElementBean.getClassBeanList());
        map.put("infrastacl",infrastAclElementBean.getInterfaceBeanList());
        map.put("infrastaclparam",infrastAclElementBean.getClassBeanList());
        map.put("cmd",commandElementBean.getClassBeanList());
        map.put("exeClass",executorElementBean.getClassBeanList());
        map.put("exeInterface",executorElementBean.getInterfaceBeanList());
        map.put("infrastaclimpl",infrastAclImplElementBean.refreshClass(plantUmlContextBean, DomainElementEnum.ADAPTER_ACL_IMPL.getElement()).getClassBeanList());
        map.put("mqproducer",mqProducerElementBean.getClassBeanList());
        map.put("mqconsumer",mqConsumerElementBean.getClassBeanList());
        map.put("mqhandler",mqHandlerElementBean.getClassBeanList());
        map.put("domainevent",domainEventElementBean.getClassBeanList());

        return map;
    }
}
