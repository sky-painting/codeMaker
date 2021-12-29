package com.coderman.codemaker.service;

import com.coderman.codemaker.app.IWriteFileService;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.EnumBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.service.template.FreemarkerService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Description:
 * date: 2021/6/30
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class WriteDynamicDDDService {
    @Autowired
    private FreemarkerService freemarkerService;

    /**
     * 写dynamicddd模块代码生成
     * @param dynamicDDDMap
     */
    public void writeDynamicDDD(Map<String, Object> dynamicDDDMap, IWriteFileService writeFileService){
        //写bo
        List<ClassBean> classBeanList = (List<ClassBean>)dynamicDDDMap.get("domainbo");
        writeBO(classBeanList,writeFileService,"ddd");

        //写valueobject
        List<ClassBean> valueObjectBeanList = (List<ClassBean>)dynamicDDDMap.get("domainvalueobject");
        writeValueObject(valueObjectBeanList,writeFileService,"ddd");

        //写接口
        List<InterfaceBean> repositoryInterfaceBeanList = (List<InterfaceBean>)dynamicDDDMap.get("repository");
        List<InterfaceBean> gatawayInterfaceBeanList = (List<InterfaceBean>)dynamicDDDMap.get("gataway");
        repositoryInterfaceBeanList.addAll(gatawayInterfaceBeanList);
        writeInterfaceAndImpl(repositoryInterfaceBeanList,writeFileService,"ddd");

        //写枚举
        List<EnumBean> enumBeanList = (List<EnumBean>)dynamicDDDMap.get("valueobjectenum");
        writeEnum(enumBeanList,writeFileService,"ddd");

        //写msgbody
        List<ClassBean> domainMsgBeanList = (List<ClassBean>)dynamicDDDMap.get("domainmsg");
        writeMsgBody(domainMsgBeanList,writeFileService,"ddd");


        //写domainevent
        List<ClassBean> domainEventBeanList = (List<ClassBean>)dynamicDDDMap.get("domainevent");
        writeDomainEvent(domainEventBeanList,writeFileService,"ddd");

        //写factory
        List<ClassBean> domainFactoryBeanList = (List<ClassBean>)dynamicDDDMap.get("domainfactory");
        writeFactory(domainFactoryBeanList,writeFileService,"ddd");

        //写防腐层接口
        List<InterfaceBean> infrastAclInterfaceList = (List<InterfaceBean>)dynamicDDDMap.get("infrastacl");
        writeAclInterface(infrastAclInterfaceList,writeFileService,"ddd");

        //写防腐层接口
        List<ClassBean> infrastAclInterfaceImplList = (List<ClassBean>)dynamicDDDMap.get("infrastaclimpl");
        writeAclInterfaceImpl(infrastAclInterfaceImplList,writeFileService,"ddd");

        //写防腐层接口需要的参数
        List<ClassBean> infrastAclParamClassList = (List<ClassBean>)dynamicDDDMap.get("infrastaclparam");
        writeACLParam(infrastAclParamClassList,writeFileService,"ddd");

        //写app层的命令服务
        List<ClassBean> appCommandClassList = (List<ClassBean>)dynamicDDDMap.get("cmd");
        writeAppCommand(appCommandClassList,writeFileService,"ddd");

        //写app.exe interface
        List<InterfaceBean> appExeInterfaceList = (List<InterfaceBean>)dynamicDDDMap.get("exeInterface");
        writeAppExeInterface(appExeInterfaceList,writeFileService,"ddd");

        //写app.exeImpl
        List<ClassBean> appExeClassImplList = (List<ClassBean>)dynamicDDDMap.get("exeClass");
        writeAppExeImpl(appExeClassImplList,writeFileService,"ddd");
    }

    /**
     * 写BO
     * @param classBeanList
     * @param writeFileService
     */
    public void writeBO(List<ClassBean> classBeanList, IWriteFileService writeFileService,String dddTag){
        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            String boContent;
            if (StringUtils.isNotEmpty(dddTag)){
                boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName(), varMap);
            }else {
                boContent = freemarkerService.parseTpl(TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.BUSINESS_OBJECT_DDD);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写msgbody
     * @param classBeanList
     * @param writeFileService
     */
    public void writeMsgBody(List<ClassBean> classBeanList, IWriteFileService writeFileService,String dddTag){

        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();

            String boContent;
            if(StringUtils.isNotEmpty(dddTag)){
                boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.MESSAGE_BODY.getTempFileName(), varMap);
            }else {
                boContent = freemarkerService.parseTpl(TemplateFileEnum.MESSAGE_BODY.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.MESSAGE_BODY);
            writeFileService.writeContent(writeContentBean);
        }
    }



    /**
     * 写domainEvent
     * @param classBeanList
     * @param writeFileService
     */
    public void writeDomainEvent(List<ClassBean> classBeanList, IWriteFileService writeFileService,String dddTag){

        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();

            String boContent;
            if(StringUtils.isNotEmpty(dddTag)){
                boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.EVENT_BODY.getTempFileName(), varMap);
            }else {
                boContent = freemarkerService.parseTpl(TemplateFileEnum.EVENT_BODY.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.EVENT_BODY);
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写DTO
     * @param classBeanList
     * @param writeFileService
     */
    public void writeDTO(List<ClassBean> classBeanList, IWriteFileService writeFileService){
        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            String boContent = freemarkerService.parseTpl(TemplateFileEnum.DTO_DDD.getTempFileName(), varMap);
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.DTO_DDD);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写VO
     * @param classBeanList
     * @param writeFileService
     */
    public void writeVO(List<ClassBean> classBeanList, IWriteFileService writeFileService){

        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            String boContent = freemarkerService.parseTpl(TemplateFileEnum.VO_DDD.getTempFileName(), varMap);
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.VO_DDD);
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写VO
     * @param classBeanList
     * @param writeFileService
     */
    public void writeController(List<ClassBean> classBeanList, IWriteFileService writeFileService){
        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            String boContent = freemarkerService.parseTpl(TemplateFileEnum.CONTROLLER_DDD.getTempFileName(), varMap);
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.CONTROLLER_DDD);
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写facadeimpl
     * @param classBeanList
     * @param writeFileService
     */
    public void writeFacadeImpl(List<ClassBean> classBeanList, IWriteFileService writeFileService){
        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            String boContent = freemarkerService.parseTpl(TemplateFileEnum.FACADE_IMPL_DDD.getTempFileName(), varMap);
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.FACADE_IMPL);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写facade
     * @param classBeanList
     * @param writeFileService
     */
    public void writeFacade(List<InterfaceBean> classBeanList, IWriteFileService writeFileService){
        for (InterfaceBean interfaceBean : classBeanList){
            Map<String, Object> varMap = interfaceBean.buildVarMap();
            String boContent = freemarkerService.parseTpl(TemplateFileEnum.FACADE_DDD.getTempFileName(), varMap);
            WriteContentBean writeContentBean = interfaceBean.buildWriteContentBean(boContent,TemplateFileEnum.FACADE);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写facade
     * @param classBeanList
     * @param writeFileService
     */
    public void writeFeign(List<InterfaceBean> classBeanList, IWriteFileService writeFileService){
        for (InterfaceBean interfaceBean : classBeanList){
            Map<String, Object> varMap = interfaceBean.buildVarMap();
            String boContent = freemarkerService.parseTpl(TemplateFileEnum.FEIGN_DDD.getTempFileName(), varMap);
            WriteContentBean writeContentBean = interfaceBean.buildWriteContentBean(boContent,TemplateFileEnum.FEIGN);
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写valueobject
     * @param classBeanList
     * @param writeFileService
     */
    public void writeValueObject(List<ClassBean> classBeanList, IWriteFileService writeFileService,String dddTag){
        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            String boContent;
            if(StringUtils.isNotEmpty(dddTag)){
                boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.VALUE_OBJECT.getTempFileName(), varMap);
            }else {
                boContent = freemarkerService.parseTpl(TemplateFileEnum.VALUE_OBJECT.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.VALUE_OBJECT);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写接口和实现
     * @param repositoryInterfaceBeanList
     * @param writeFileService
     */
    private void writeInterfaceAndImpl(List<InterfaceBean> repositoryInterfaceBeanList, IWriteFileService writeFileService,String dddTag){
        for (InterfaceBean interfaceBean : repositoryInterfaceBeanList){
            Map<String, Object> varMap = interfaceBean.buildVarMap();
            String interfaceContent;
            if (StringUtils.isNotEmpty(dddTag)){
                interfaceContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.GATAWAY.getTempFileName(), varMap);
            }else {
                interfaceContent = freemarkerService.parseTpl(TemplateFileEnum.GATAWAY.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = interfaceBean.buildWriteContentBean(interfaceContent,TemplateFileEnum.GATAWAY);
            writeFileService.writeContent(writeContentBean);

            //写接口实现
            String interfaceImplContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.GATAWAY_IMPL.getTempFileName(), varMap);
            WriteContentBean writeContentBean2 = interfaceBean.buildWriteContentBean(interfaceImplContent,TemplateFileEnum.GATAWAY_IMPL);
            writeFileService.writeContent(writeContentBean2);
        }
    }

    /**
     * 写gataway接口和repository接口
     * @param gataWayBeanList
     * @param writeFileService
     */
    public void writeGataWay(List<InterfaceBean> gataWayBeanList, IWriteFileService writeFileService,String dddTag) {
        for (InterfaceBean interfaceBean : gataWayBeanList) {
            Map<String, Object> varMap = interfaceBean.buildVarMap();
            String interfaceContent = freemarkerService.parseTpl(TemplateFileEnum.GATAWAY.getTempFileName(), varMap);
            WriteContentBean writeContentBean = interfaceBean.buildWriteContentBean(interfaceContent,TemplateFileEnum.GATAWAY);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写gataway接口和repository接口
     * @param repositoryBeanList
     * @param writeFileService
     */
    public void writeRepository(List<InterfaceBean> repositoryBeanList, IWriteFileService writeFileService,String dddTag) {
        for (InterfaceBean interfaceBean : repositoryBeanList) {
            Map<String, Object> varMap = interfaceBean.buildVarMap();
            String interfaceContent = freemarkerService.parseTpl(TemplateFileEnum.GATAWAY.getTempFileName(), varMap);
            WriteContentBean writeContentBean = interfaceBean.buildWriteContentBean(interfaceContent,TemplateFileEnum.REPOSITORY);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写dynamicmapper
     * @param dynamicMapperBeanList
     * @param writeFileService
     */
    public void writeDynamicMapper(List<InterfaceBean> dynamicMapperBeanList, IWriteFileService writeFileService,String dddTag) {
        for (InterfaceBean interfaceBean : dynamicMapperBeanList) {
            Map<String, Object> varMap = interfaceBean.buildVarMap();
            String interfaceContent = freemarkerService.parseTpl(TemplateFileEnum.MAPPER_DDD.getTempFileName(), varMap);
            WriteContentBean writeContentBean = interfaceBean.buildWriteContentBean(interfaceContent,TemplateFileEnum.MAPPER_DDD);
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写dynamicmapperxml
     * @param dynamicMapperXmlBeanList
     * @param writeFileService
     */
    public void writeDynamicMapperXml(List<ClassBean> dynamicMapperXmlBeanList, IWriteFileService writeFileService,String dddTag) {
        for (ClassBean classBean : dynamicMapperXmlBeanList) {
            Map<String, Object> varMap = classBean.buildVarMap();
            if(CollectionUtils.isNotEmpty(classBean.getImportClassList())){
                Optional<String> doPackageName = classBean.getImportClassList().stream().filter(importClassName -> importClassName.toLowerCase().endsWith(TemplateFileEnum.DATA_OBJECT.getTempFileName())).findFirst();
                if(doPackageName.isPresent()){
                    varMap.put("doPackageName",doPackageName.get());
                }
            }

            String interfaceContent = freemarkerService.parseTpl(TemplateFileEnum.MAPPER_XML_DDD.getTempFileName(), varMap);
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(interfaceContent,TemplateFileEnum.MAPPER_XML_DDD);
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写gataway实现
     * @param gatawayImplInterfaceBeanList
     * @param writeFileService
     */
    public void writeGatawayImpl(List<ClassBean> gatawayImplInterfaceBeanList, IWriteFileService writeFileService,String dddTag){
        for (ClassBean classBean : gatawayImplInterfaceBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            //写接口实现
            String interfaceImplContent = freemarkerService.parseTpl(TemplateFileEnum.GATAWAY_IMPL.getTempFileName(), varMap);
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(interfaceImplContent,TemplateFileEnum.GATAWAY_IMPL);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写repository实现
     * @param repositoryImplInterfaceBeanList
     * @param writeFileService
     */
    public void writeRepositoryImpl(List<ClassBean> repositoryImplInterfaceBeanList, IWriteFileService writeFileService,String dddTag){
        for (ClassBean classBean : repositoryImplInterfaceBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            //写接口实现
            String interfaceImplContent = freemarkerService.parseTpl(TemplateFileEnum.GATAWAY_IMPL.getTempFileName(), varMap);
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(interfaceImplContent,TemplateFileEnum.REPOSITORY_IMPL);
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写枚举
     * @param enumBeanList
     * @param writeFileService
     */
    public void writeEnum(List<EnumBean> enumBeanList, IWriteFileService writeFileService,String dddTag){
        //写枚举
        for (EnumBean enumBean : enumBeanList){
            Map<String, Object> varMap = enumBean.buildVarMap();
            String enumContent;
            if(StringUtils.isNotEmpty(dddTag)){
                enumContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.ENUM.getTempFileName(), varMap);
            }else {
                enumContent = freemarkerService.parseTpl(TemplateFileEnum.ENUM.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = enumBean.buildWriteContentBean(enumContent,TemplateFileEnum.ENUM);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写factory
     * @param domainFactoryBeanList
     * @param writeFileService
     */
    public void writeFactory(List<ClassBean> domainFactoryBeanList, IWriteFileService writeFileService,String dddTag){
        //写factory
        for (ClassBean classBean : domainFactoryBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            String boContent;
            if(StringUtils.isNotEmpty(dddTag)){
                boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.FACTORY.getTempFileName(), varMap);
            }else {
                boContent = freemarkerService.parseTpl(TemplateFileEnum.FACTORY.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.FACTORY);
            writeFileService.writeContent(writeContentBean);
        }
    }
    /**
     * 写cache
     * @param domainFactoryBeanList
     * @param writeFileService
     */
    public void writeCache(List<ClassBean> domainFactoryBeanList, IWriteFileService writeFileService,String dddTag){
        //写factory
        for (ClassBean classBean : domainFactoryBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            String boContent;
            if(StringUtils.isNotEmpty(dddTag)){
                boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.CACHE.getTempFileName(), varMap);
            }else {
                boContent = freemarkerService.parseTpl(TemplateFileEnum.CACHE.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.CACHE);
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写app.listener
     * @param appListenerBeanList
     * @param writeFileService
     */
    public void writeAppListner(List<ClassBean> appListenerBeanList, IWriteFileService writeFileService,String dddTag){
        //写app.listener
        for (ClassBean classBean : appListenerBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            String boContent;
            if(StringUtils.isNotEmpty(dddTag)){
                boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.MQ_LISTENER.getTempFileName(), varMap);
            }else {
                boContent = freemarkerService.parseTpl(TemplateFileEnum.MQ_LISTENER.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.MQ_LISTENER);
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写infrast.mq.producer
     * @param appListenerBeanList
     * @param writeFileService
     */
    public void writeMqProducer(List<ClassBean> appListenerBeanList, IWriteFileService writeFileService,String dddTag){
        //写infrast.mq.producer
        for (ClassBean classBean : appListenerBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            String boContent;
            if(StringUtils.isNotEmpty(dddTag)){
                boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.MQ_PRODUCER.getTempFileName(), varMap);
            }else {
                boContent = freemarkerService.parseTpl(TemplateFileEnum.MQ_PRODUCER.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.MQ_PRODUCER);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写infrast.mq.consumer
     * @param appListenerBeanList
     * @param writeFileService
     */
    public void writeMqConsumer(List<ClassBean> appListenerBeanList, IWriteFileService writeFileService,String dddTag){
        //写infrast.mq.consumer
        for (ClassBean classBean : appListenerBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            String boContent;
            if(StringUtils.isNotEmpty(dddTag)){
                boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.MQ_CONSUMER.getTempFileName(), varMap);
            }else {
                boContent = freemarkerService.parseTpl(TemplateFileEnum.MQ_CONSUMER.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.MQ_CONSUMER);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写infrast.mq.handler
     * @param appListenerBeanList
     * @param writeFileService
     */
    public void writeMqHandler(List<ClassBean> appListenerBeanList, IWriteFileService writeFileService,String dddTag){
        //写infrast.mq.handler
        for (ClassBean classBean : appListenerBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            String boContent;
            if(StringUtils.isNotEmpty(dddTag)){
                boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.MQ_HANDLER.getTempFileName(), varMap);
            }else {
                boContent = freemarkerService.parseTpl(TemplateFileEnum.MQ_HANDLER.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.MQ_HANDLER);
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写infrastacl接口
     * @param aclInterfaceBeanList
     * @param writeFileService
     */
    public void writeAclInterface(List<InterfaceBean> aclInterfaceBeanList, IWriteFileService writeFileService,String dddTag){
        for (InterfaceBean interfaceBean : aclInterfaceBeanList){
            Map<String, Object> varMap = interfaceBean.buildVarMap();

            String interfaceContent;
            if(StringUtils.isNotEmpty(dddTag)){
                interfaceContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.ACL.getTempFileName(), varMap);
            }else {
                interfaceContent = freemarkerService.parseTpl(TemplateFileEnum.ACL.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = interfaceBean.buildWriteContentBean(interfaceContent,TemplateFileEnum.ACL);
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写infrastaclImpl
     * @param infrastAclImplBeanList
     * @param writeFileService
     */
    public void writeAclInterfaceImpl(List<ClassBean> infrastAclImplBeanList, IWriteFileService writeFileService,String dddTag){
        for (ClassBean classBean : infrastAclImplBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            String interfaceImplContent;
            if(StringUtils.isNotEmpty(dddTag)){
                interfaceImplContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.ACL_IMPL.getTempFileName(), varMap);
            }else {
                interfaceImplContent = freemarkerService.parseTpl(TemplateFileEnum.ACL_IMPL.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(interfaceImplContent,TemplateFileEnum.ACL_IMPL);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写acl.param
     * @param classBeanList
     * @param writeFileService
     */
    public void writeACLParam(List<ClassBean> classBeanList, IWriteFileService writeFileService,String dddTag){

        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            //使用BO的模板
            String boContent;
            if(StringUtils.isNotEmpty(dddTag)){
                boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName(), varMap);
            }else {
                boContent = freemarkerService.parseTpl(TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.ACL_PARAM);
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写app.cmd
     * @param classBeanList
     * @param writeFileService
     */
    public void writeAppCommand(List<ClassBean> classBeanList, IWriteFileService writeFileService,String dddTag){
        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            //使用BO的模板
            String boContent;
            if(StringUtils.isNotEmpty(dddTag)){
                boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName(), varMap);
            }else {
                boContent = freemarkerService.parseTpl(TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.CMD);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写app.exe--->class 接口实现
     * @param classBeanList
     * @param writeFileService
     */
    public void writeAppExeImpl(List<ClassBean> classBeanList, IWriteFileService writeFileService,String dddTag){
        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            String boContent;
            if(StringUtils.isNotEmpty(dddTag)){
                boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.EXE_IMPL.getTempFileName(), varMap);
                if(StringUtils.isEmpty(classBean.getRelationClassStr())){
                    boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.EXE_ABSTRACT.getTempFileName(), varMap);
                }
            }else {
                boContent = freemarkerService.parseTpl(TemplateFileEnum.EXE_IMPL.getTempFileName(), varMap);
                if(StringUtils.isEmpty(classBean.getRelationClassStr())){
                    boContent = freemarkerService.parseTpl(TemplateFileEnum.EXE_ABSTRACT.getTempFileName(), varMap);
                }
            }
            WriteContentBean writeContentBean = classBean.buildWriteContentBean(boContent,TemplateFileEnum.EXE);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写app.exe--->interface 接口
     * @param interfaceBeanList
     * @param writeFileService
     */
    public void writeAppExeInterface(List<InterfaceBean> interfaceBeanList, IWriteFileService writeFileService,String dddTag){
        for (InterfaceBean interfaceBean : interfaceBeanList){
            Map<String, Object> varMap = interfaceBean.buildVarMap();
            //借用gataway的模板
            String interfaceContent;
            if(StringUtils.isNotEmpty(dddTag)){
                interfaceContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.GATAWAY.getTempFileName(), varMap);
            }else {
                interfaceContent = freemarkerService.parseTpl(TemplateFileEnum.GATAWAY.getTempFileName(), varMap);
            }
            WriteContentBean writeContentBean = interfaceBean.buildWriteContentBean(interfaceContent,TemplateFileEnum.EXE);
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写dtobo-convert 接口
     * @param interfaceBeanList
     * @param writeFileService
     */
    public void writeDtoBOConvert(List<InterfaceBean> interfaceBeanList, IWriteFileService writeFileService){
        for (InterfaceBean interfaceBean : interfaceBeanList){
            Map<String, Object> varMap = interfaceBean.buildVarMap();
            String interfaceContent = freemarkerService.parseTpl(TemplateFileEnum.CONVERT.getTempFileName(), varMap);
            WriteContentBean writeContentBean = interfaceBean.buildWriteContentBean(interfaceContent,TemplateFileEnum.CONVERT);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写vobo-convert 接口
     * @param interfaceBeanList
     * @param writeFileService
     */
    public void writeVoBOConvert(List<InterfaceBean> interfaceBeanList, IWriteFileService writeFileService){
        for (InterfaceBean interfaceBean : interfaceBeanList){
            Map<String, Object> varMap = interfaceBean.buildVarMap();
            String interfaceContent = freemarkerService.parseTpl(TemplateFileEnum.CONVERT.getTempFileName(), varMap);
            WriteContentBean writeContentBean = interfaceBean.buildWriteContentBean(interfaceContent,TemplateFileEnum.CONVERT);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写dobo-convert 接口
     * @param interfaceBeanList
     * @param writeFileService
     */
    public void writeDoBOConvert(List<InterfaceBean> interfaceBeanList, IWriteFileService writeFileService){
        for (InterfaceBean interfaceBean : interfaceBeanList){
            Map<String, Object> varMap = interfaceBean.buildVarMap();
            String interfaceContent = freemarkerService.parseTpl(TemplateFileEnum.CONVERT.getTempFileName(), varMap);
            WriteContentBean writeContentBean = interfaceBean.buildWriteContentBean(interfaceContent,TemplateFileEnum.CONVERT);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写dto2dto-convert 接口
     * @param interfaceBeanList
     * @param writeFileService
     */
    public void writeDTO2DTOConvert(List<InterfaceBean> interfaceBeanList, IWriteFileService writeFileService){
        for (InterfaceBean interfaceBean : interfaceBeanList){
            Map<String, Object> varMap = interfaceBean.buildVarMap();
            String interfaceContent = freemarkerService.parseTpl(TemplateFileEnum.CONVERT.getTempFileName(), varMap);
            WriteContentBean writeContentBean = interfaceBean.buildWriteContentBean(interfaceContent,TemplateFileEnum.DTO2DTO_CONVERT);
            writeFileService.writeContent(writeContentBean);
        }
    }
}
