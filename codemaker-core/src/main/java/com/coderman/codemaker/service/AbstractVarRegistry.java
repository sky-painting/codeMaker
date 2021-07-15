package com.coderman.codemaker.service;

import com.alibaba.fastjson.JSON;
import com.coderman.codemaker.app.dynamicddd.DomainElementHandler;
import com.coderman.codemaker.app.dynamicddd.derivedhandler.*;
import com.coderman.codemaker.bean.TableBean;
import com.coderman.codemaker.bean.dddelement.*;
import com.coderman.codemaker.bean.dddelementderive.*;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.PlantUmlContextBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.config.ProjectTemplateDubboConfig;
import com.coderman.codemaker.config.ProjectTemplateSpringbootConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * description: AbstractVarRegistry <br>
 * date: 2020/7/7 9:56 <br>
 * author: coderman <br>
 * version: 1.0 <br>
 */
public abstract   class AbstractVarRegistry {
    @Autowired
    //private ReadPlantUMLFileService readFileService;
    private ReadPlantUMLFileServiceV2 readFileService;

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


    /**
     * 合并全局配置
     * @return
     */
    public Map<String,Object> getTemplateVar(){
        Map<String,Object> varMap = getRegistVarMap();

        varMap.putIfAbsent("package",appServiceConfig.getPackage());
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


        tableBeanMap.forEach((k,v)->{
            ClassBean classBean = v.convertToClassBean(appServiceConfig.getPackage()+".dao.dataobject");
            plantUmlContextBean.getClassBeanMap().put(classBean.getClassName(),classBean);
        });


        //打标
        varMap.put("dynamicddd","dynamicddd");
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


        varMap.put("domainbo",domainBoElementBean.getClassBeanList());
        varMap.put("domainvalueobject",valueObjectElementBean.getClassBeanList());
        varMap.put("valueobjectenum",valueObjectElementBean.getEnumBeanList());
        varMap.put("repository",repositoryElementBean.getInterfaceBeanList());
        varMap.put("gataway",gatawayElementBean.getInterfaceBeanList());
        varMap.put("domainmsg",domainMsgBodyElementBean.getClassBeanList());
        varMap.put("domainfactory",factoryElementBean.getClassBeanList());
        varMap.put("infrastacl",infrastAclElementBean.getInterfaceBeanList());
        varMap.put("infrastaclparam",infrastAclElementBean.getClassBeanList());
        varMap.put("cmd",commandElementBean.getClassBeanList());
        varMap.put("exeClass",executorElementBean.getClassBeanList());
        varMap.put("exeInterface",executorElementBean.getInterfaceBeanList());

        //处理派生类
        DtoElementBean dtoElementBean = derivedDTOElementHandler.getElementBeanList(plantUmlContextBean);
        varMap.put("derivedto",dtoElementBean.getClassBeanList());

        FacadeElementBean facadeElementBean = derivedFacadeElementHandler.getElementBeanList(plantUmlContextBean);
        varMap.put("derivefacade",facadeElementBean.getInterfaceBeanList());

        FacadeImplElementBean facadeImplElementBean = derivedFacadeImplElementHandler.getElementBeanList(plantUmlContextBean);
        varMap.put("derivefacadeimpl",facadeImplElementBean.getClassBeanList());

        DtoBoConvertElementBean dtoBoConvertElementBean = derivedDTOBOConvertElementHandler.getElementBeanList(plantUmlContextBean);
        varMap.put("dtoboconvert",dtoBoConvertElementBean.getInterfaceBeanList());

        DoBoConvertElementBean doBoConvertElementBean = derivedDOBOConvertElementHandler.getElementBeanList(plantUmlContextBean);
        varMap.put("doboconvert",doBoConvertElementBean.getInterfaceBeanList());

        VoElementBean voElementBean = derivedVOElementHandler.getElementBeanList(plantUmlContextBean);
        varMap.put("adaptervo",voElementBean.getClassBeanList());

        ControllerElementBean controllerElementBean = derivedControllerElementHandler.getElementBeanList(plantUmlContextBean);
        varMap.put("controller",controllerElementBean.getClassBeanList());

        VoBoConvertElementBean voBoConvertElementBean = derivedVOBOConvertElementHandler.getElementBeanList(plantUmlContextBean);
        varMap.put("voboconvert",voBoConvertElementBean.getInterfaceBeanList());

        return varMap;
    }

    public  abstract Map<String,Object> getRegistVarMap();

}
