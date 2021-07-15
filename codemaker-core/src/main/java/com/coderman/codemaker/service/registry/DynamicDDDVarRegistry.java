package com.coderman.codemaker.service.registry;

import com.alibaba.fastjson.JSON;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.bean.dddelement.*;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.config.ProjectTemplateDynamicDDDConfig;
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

    @Autowired
    private ProjectTemplateDynamicDDDConfig projectTemplateDynamicDDDConfig;

    @Override
    public Map<String, Object> getRegistVarMap() {
        PlantUmlContextBean plantUmlContextBean = readFileService.getPlantUmlContextBean(projectTemplateDynamicDDDConfig.getPlantumlFileName());
        System.out.println(JSON.toJSONString(plantUmlContextBean));
        DomainBoElementBean domainBoElementBean = (DomainBoElementBean)domainElementHandler.getElementBeanList(plantUmlContextBean);
        ValueObjectElementBean valueObjectElementBean = (ValueObjectElementBean)valueObjectElementHandler.getElementBeanList(plantUmlContextBean);
        RepositoryElementBean repositoryElementBean = (RepositoryElementBean)repositoryElementHandler.getElementBeanList(plantUmlContextBean);
        GatawayElementBean gatawayElementBean = (GatawayElementBean)domainGatawayElementHandler.getElementBeanList(plantUmlContextBean);
        DomainMsgBodyElementBean domainMsgBodyElementBean = (DomainMsgBodyElementBean)msgBodyElementHandler.getElementBeanList(plantUmlContextBean);
        FactoryElementBean factoryElementBean = (FactoryElementBean)domainFactoryElementHandler.getElementBeanList(plantUmlContextBean);
        InfrastAclElementBean infrastAclElementBean = (InfrastAclElementBean)infrastAclElementHandler.getElementBeanList(plantUmlContextBean);
        CommandElementBean commandElementBean = (CommandElementBean)appCmdElementHandler.getElementBeanList(plantUmlContextBean);
        ExecutorElementBean executorElementBean = (ExecutorElementBean)appExeElementHandler.getElementBeanList(plantUmlContextBean);

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

        //由领域实体派生的类dto
        //map.put("domainbo_dto",domainBoElementBean.getDerivedElementBean().getClassBeanList());

        return map;
    }
}
