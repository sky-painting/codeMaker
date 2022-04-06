package com.tianhua.codemaker.service;

import com.tianhua.codemaker.app.IWriteFileService;
import com.tianhua.codemaker.bean.WriteContentBean;
import com.tianhua.codemaker.bean.config.PomBean;
import com.tianhua.codemaker.bean.plantuml.ClassBean;
import com.tianhua.codemaker.bean.plantuml.EnumBean;
import com.tianhua.codemaker.bean.plantuml.InterfaceBean;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.tianhua.codemaker.service.template.FreemarkerService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Description:
 * date: 2021/6/30
 *
 * @author shenshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class WriteDynamicDDDService extends AbstractEleWriteService{
    @Autowired
    private FreemarkerService freemarkerService;

    /**
     * 写dynamicddd模块代码生成
     * @param dynamicDDDMap
     */
    public void writeDynamicDDD(Map<String, Object> dynamicDDDMap, IWriteFileService writeFileService){
        writeClassEle((List<ClassBean>)dynamicDDDMap.get("domainbo"), TemplateFileEnum.BUSINESS_OBJECT_DDD, TemplateFileEnum.BUSINESS_OBJECT_DDD, writeFileService);
        writeClassEle((List<ClassBean>)dynamicDDDMap.get("domainvalueobject"), TemplateFileEnum.VALUE_OBJECT, TemplateFileEnum.VALUE_OBJECT, writeFileService);

        //写接口
        List<InterfaceBean> repositoryInterfaceBeanList = (List<InterfaceBean>)dynamicDDDMap.get("repository");
        List<InterfaceBean> gatawayInterfaceBeanList = (List<InterfaceBean>)dynamicDDDMap.get("gataway");
        repositoryInterfaceBeanList.addAll(gatawayInterfaceBeanList);
        writeInterfaceAndImpl(repositoryInterfaceBeanList,writeFileService,"ddd");

        writeEnumEle((List<EnumBean>)dynamicDDDMap.get("valueobjectenum"), writeFileService);
        writeClassEle((List<ClassBean>)dynamicDDDMap.get("domainmsg"), TemplateFileEnum.MESSAGE_BODY, TemplateFileEnum.MESSAGE_BODY, writeFileService);
        writeClassEle((List<ClassBean>)dynamicDDDMap.get("domainevent"), TemplateFileEnum.EVENT_BODY, TemplateFileEnum.EVENT_BODY, writeFileService);
        writeClassEle( (List<ClassBean>)dynamicDDDMap.get("domainfactory"), TemplateFileEnum.FACTORY, TemplateFileEnum.FACTORY, writeFileService);
        writeInterfaceEle((List<InterfaceBean>)dynamicDDDMap.get("infrastacl"), TemplateFileEnum.ACL, TemplateFileEnum.ACL, writeFileService);

        writeClassEle((List<ClassBean>)dynamicDDDMap.get("infrastaclimpl"), TemplateFileEnum.ACL_IMPL, TemplateFileEnum.ACL_IMPL, writeFileService);

        writeClassEle((List<ClassBean>)dynamicDDDMap.get("infrastaclparam"), TemplateFileEnum.BUSINESS_OBJECT_DDD, TemplateFileEnum.ACL_PARAM, writeFileService);
        writeClassEle((List<ClassBean>)dynamicDDDMap.get("cmd"), TemplateFileEnum.BUSINESS_OBJECT_DDD, TemplateFileEnum.CMD, writeFileService);
        writeInterfaceEle((List<InterfaceBean>)dynamicDDDMap.get("exeInterface"), TemplateFileEnum.GATAWAY, TemplateFileEnum.EXE, writeFileService);
        writeAppExeImpl((List<ClassBean>)dynamicDDDMap.get("exeClass"),writeFileService,"ddd");
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
     * 写interface
     * @param interfaceBeanList
     * @param writeFileService
     */
    public void writeInterfaceBean(List<InterfaceBean> interfaceBeanList, IWriteFileService writeFileService,String templateFileCode){
        for (InterfaceBean interfaceBean : interfaceBeanList){
            Map<String, Object> varMap = interfaceBean.buildVarMap();
            String interfaceContent = freemarkerService.parseTpl(templateFileCode, varMap);
            WriteContentBean writeContentBean = interfaceBean.buildWriteContentBeanV2(interfaceContent,templateFileCode,true);
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写interface
     * @param pomBeanList
     * @param writeFileService
     */
    public void writePomBean(List<PomBean> pomBeanList, IWriteFileService writeFileService, String templateFileCode){
        if(CollectionUtils.isEmpty(pomBeanList)){
            return;
        }
        for (PomBean pomBean : pomBeanList){
            if(pomBean == null){
                continue;
            }
            Map<String, Object> varMap = pomBean.buildVarMap();
            String interfaceContent = freemarkerService.parseTpl(templateFileCode, varMap);
            WriteContentBean writeContentBean = pomBean.buildWriteContentBean(interfaceContent,templateFileCode);
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写classBean
     * @param classBeanList
     * @param writeFileService
     */
    public void writeClassBean(List<ClassBean> classBeanList, IWriteFileService writeFileService,String dddTag, String templateFileCode){
        if(CollectionUtils.isEmpty(classBeanList)){
            return;
        }
        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = classBean.buildVarMap();
            String boContent;
            if(StringUtils.isNotEmpty(dddTag)){
                boContent = freemarkerService.parseTplDynamicDDD(templateFileCode, varMap);
            }else {
                boContent = freemarkerService.parseTpl(templateFileCode, varMap);
            }
            WriteContentBean writeContentBean = classBean.buildWriteContentBeanV2(boContent,templateFileCode,true);
            writeFileService.writeContent(writeContentBean);
        }
    }

}
