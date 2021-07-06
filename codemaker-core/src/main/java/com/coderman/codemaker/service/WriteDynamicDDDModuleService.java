package com.coderman.codemaker.service;

import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.bean.plantuml.ClassBean;
import com.coderman.codemaker.bean.plantuml.EnumBean;
import com.coderman.codemaker.bean.plantuml.InterfaceBean;
import com.coderman.codemaker.enums.TemplateFileEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2021/6/30
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Service
public class WriteDynamicDDDModuleService {
    @Autowired
    private FreemarkerService freemarkerService;

    /**
     * 写dynamicddd模块代码生成
     * @param dynamicDDDMap
     */
    public void writeDynamicDDD(Map<String, Object> dynamicDDDMap, IWriteFileService writeFileService){

        //写bo
        List<ClassBean> classBeanList = (List<ClassBean>)dynamicDDDMap.get("domainbo");
        writeBO(classBeanList,writeFileService);


        //写valueobject
        List<ClassBean> valueObjectBeanList = (List<ClassBean>)dynamicDDDMap.get("domainvalueobject");
        writeValueObject(valueObjectBeanList,writeFileService);

        //写接口
        List<InterfaceBean> repositoryInterfaceBeanList = (List<InterfaceBean>)dynamicDDDMap.get("repository");
        List<InterfaceBean> gatawayInterfaceBeanList = (List<InterfaceBean>)dynamicDDDMap.get("gataway");
        repositoryInterfaceBeanList.addAll(gatawayInterfaceBeanList);
        writeInterfaceAndImpl(repositoryInterfaceBeanList,writeFileService);

        //写枚举
        List<EnumBean> enumBeanList = (List<EnumBean>)dynamicDDDMap.get("valueobjectenum");
        writeEnum(enumBeanList,writeFileService);

        //写msgbody
        List<ClassBean> domainMsgBeanList = (List<ClassBean>)dynamicDDDMap.get("domainmsg");
        writeMsgBody(domainMsgBeanList,writeFileService);

        //写factory
        List<ClassBean> domainFactoryBeanList = (List<ClassBean>)dynamicDDDMap.get("domainfactory");
        writeFactory(domainFactoryBeanList,writeFileService);

        //写防腐层接口
        List<InterfaceBean> infrastAclInterfaceList = (List<InterfaceBean>)dynamicDDDMap.get("infrastacl");
        writeAclInterfaceAndImpl(infrastAclInterfaceList,writeFileService);
        //写防腐层接口需要的参数
        List<ClassBean> infrastAclParamClassList = (List<ClassBean>)dynamicDDDMap.get("infrastaclparam");
        writeACLParam(infrastAclParamClassList,writeFileService);


        //写app层的命令服务
        List<ClassBean> appCommandClassList = (List<ClassBean>)dynamicDDDMap.get("cmd");
        writeAppCommand(appCommandClassList,writeFileService);

        //写app.exe interface
        List<InterfaceBean> appExeInterfaceList = (List<InterfaceBean>)dynamicDDDMap.get("exeInterface");
        writeAppExeInterface(appExeInterfaceList,writeFileService);

        //写app.exeImpl
        List<ClassBean> appExeClassImplList = (List<ClassBean>)dynamicDDDMap.get("exeClass");
        writeAppExeImpl(appExeClassImplList,writeFileService);




    }

    /**
     * 写BO
     * @param classBeanList
     * @param writeFileService
     */
    private void writeBO(List<ClassBean> classBeanList, IWriteFileService writeFileService){

        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = new HashMap<>();

            varMap.put("class",classBean);
            varMap.put("fields",classBean.getFieldBeanList());
            varMap.put("methods",classBean.getMethodBeanList());
            varMap.put("imports",classBean.getImportClassList());
            String boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName(), varMap);
            WriteContentBean writeContentBean = WriteContentBean.builder().content(boContent)
                    .templateName(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName())
                    .humpClassName(classBean.getClassName())
                    .classPackageName(classBean.getPackageName())
                    .build();
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写BO
     * @param classBeanList
     * @param writeFileService
     */
    private void writeValueObject(List<ClassBean> classBeanList, IWriteFileService writeFileService){

        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = new HashMap<>();

            varMap.put("class",classBean);
            varMap.put("fields",classBean.getFieldBeanList());
            varMap.put("methods",classBean.getMethodBeanList());
            varMap.put("imports",classBean.getImportClassList());
            String boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName(), varMap);
            WriteContentBean writeContentBean = WriteContentBean.builder().content(boContent)
                    .templateName(TemplateFileEnum.VALUE_OBJECT.getTempFileName())
                    .humpClassName(classBean.getClassName())
                    .classPackageName(classBean.getPackageName())
                    .build();
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写接口和实现
     * @param repositoryInterfaceBeanList
     * @param writeFileService
     */
    private void writeInterfaceAndImpl(List<InterfaceBean> repositoryInterfaceBeanList, IWriteFileService writeFileService){
        for (InterfaceBean interfaceBean : repositoryInterfaceBeanList){
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("class",interfaceBean);
            varMap.put("methods",interfaceBean.getMethodBeanList());
            varMap.put("imports",interfaceBean.getImportClassList());

            String interfaceContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.GATAWAY.getTempFileName(), varMap);
            WriteContentBean writeContentBean = WriteContentBean.builder().content(interfaceContent)
                    .templateName(TemplateFileEnum.GATAWAY.getTempFileName())
                    .humpClassName(interfaceBean.getClassName())
                    .classPackageName(interfaceBean.getPackageName())
                    .build();
            writeFileService.writeContent(writeContentBean);

            //写接口实现
            String interfaceImplContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.GATAWAY_IMPL.getTempFileName(), varMap);
            WriteContentBean writeContentBean2 = WriteContentBean.builder().content(interfaceImplContent)
                    .templateName(TemplateFileEnum.GATAWAY_IMPL.getTempFileName())
                    .humpClassName(interfaceBean.getClassName())
                    .classPackageName(interfaceBean.getPackageName())
                    .build();
            writeFileService.writeContent(writeContentBean2);
        }
    }

    /**
     * 写枚举
     * @param enumBeanList
     * @param writeFileService
     */
    private void writeEnum(List<EnumBean> enumBeanList, IWriteFileService writeFileService){
        //写枚举
        for (EnumBean enumBean : enumBeanList){
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("class",enumBean);
            varMap.put("fields",enumBean.getFieldBeanList());
            varMap.put("methods",enumBean.getMethodBeanList());
            varMap.put("enums",enumBean.getEnumValueList());
            varMap.put("methods",enumBean.getMethodBeanList());
            varMap.put("bodys",enumBean.getConstructMethodBodyList());

            String enumContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.ENUM.getTempFileName(), varMap);
            WriteContentBean writeContentBean = WriteContentBean.builder().content(enumContent)
                    .templateName(TemplateFileEnum.ENUM.getTempFileName())
                    .humpClassName(enumBean.getClassName())
                    .classPackageName(enumBean.getPackageName())
                    .build();
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写msgbody
     * @param domainMsgBeanList
     * @param writeFileService
     */
    private void writeMsgBody(List<ClassBean> domainMsgBeanList, IWriteFileService writeFileService){
        //写msgbody
        for (ClassBean classBean : domainMsgBeanList){
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("class",classBean);
            varMap.put("fields",classBean.getFieldBeanList());
            varMap.put("methods",classBean.getMethodBeanList());
            String boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.MESSAGE_BODY.getTempFileName(), varMap);
            WriteContentBean writeContentBean = WriteContentBean.builder().content(boContent)
                    .templateName(TemplateFileEnum.MESSAGE_BODY.getTempFileName())
                    .humpClassName(classBean.getClassName())
                    .classPackageName(classBean.getPackageName())
                    .build();
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写factory
     * @param domainFactoryBeanList
     * @param writeFileService
     */
    private void writeFactory(List<ClassBean> domainFactoryBeanList, IWriteFileService writeFileService){
        //写factory
        for (ClassBean classBean : domainFactoryBeanList){
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("class",classBean);
            varMap.put("fields",classBean.getFieldBeanList());
            varMap.put("methods",classBean.getMethodBeanList());
            varMap.put("imports",classBean.getImportClassList());
            String boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.FACTORY.getTempFileName(), varMap);
            WriteContentBean writeContentBean = WriteContentBean.builder().content(boContent)
                    .templateName(TemplateFileEnum.FACTORY.getTempFileName())
                    .humpClassName(classBean.getClassName())
                    .classPackageName(classBean.getPackageName())
                    .build();
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写接口和实现
     * @param repositoryInterfaceBeanList
     * @param writeFileService
     */
    private void writeAclInterfaceAndImpl(List<InterfaceBean> repositoryInterfaceBeanList, IWriteFileService writeFileService){
        for (InterfaceBean interfaceBean : repositoryInterfaceBeanList){
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("class",interfaceBean);
            varMap.put("methods",interfaceBean.getMethodBeanList());
            varMap.put("imports",interfaceBean.getImportClassList());

            String interfaceContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.ACL.getTempFileName(), varMap);
            WriteContentBean writeContentBean = WriteContentBean.builder().content(interfaceContent)
                    .templateName(TemplateFileEnum.ACL.getTempFileName())
                    .humpClassName(interfaceBean.getClassName())
                    .classPackageName(interfaceBean.getPackageName())
                    .build();
            writeFileService.writeContent(writeContentBean);

            //写接口实现
            String interfaceImplContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.GATAWAY_IMPL.getTempFileName(), varMap);
            WriteContentBean writeImplBean = WriteContentBean.builder()
                    .content(interfaceImplContent)
                    .templateName(TemplateFileEnum.GATAWAY_IMPL.getTempFileName())
                    .humpClassName(interfaceBean.getClassName())
                    .classPackageName(interfaceBean.getPackageName())
                    .build();
            writeFileService.writeContent(writeImplBean);
        }
    }


    /**
     * 写acl.param
     * @param classBeanList
     * @param writeFileService
     */
    private void writeACLParam(List<ClassBean> classBeanList, IWriteFileService writeFileService){

        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("class",classBean);
            //varMap.put("fields",classBean.getFieldBeanList());
            //varMap.put("methods",classBean.getMethodBeanList());
            //varMap.put("imports",classBean.getImportClassList());
            //使用BO的模板
            String boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName(), varMap);
            WriteContentBean writeContentBean = WriteContentBean.builder()
                    .content(boContent)
                    .templateName(TemplateFileEnum.ACL_PARAM.getTempFileName())
                    .humpClassName(classBean.getClassName())
                    .classPackageName(classBean.getPackageName())
                    .build();
            writeFileService.writeContent(writeContentBean);
        }
    }


    /**
     * 写app.cmd
     * @param classBeanList
     * @param writeFileService
     */
    private void writeAppCommand(List<ClassBean> classBeanList, IWriteFileService writeFileService){

        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("class",classBean);
            varMap.put("fields",classBean.getFieldBeanList());
            varMap.put("methods",classBean.getMethodBeanList());
            varMap.put("imports",classBean.getImportClassList());
            //使用BO的模板
            String boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName(), varMap);
            WriteContentBean writeContentBean = WriteContentBean.builder()
                    .content(boContent)
                    .templateName(TemplateFileEnum.CMD.getTempFileName())
                    .humpClassName(classBean.getClassName())
                    .classPackageName(classBean.getPackageName())
                    .build();
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写app.exe--->class 接口实现
     * @param classBeanList
     * @param writeFileService
     */
    private void writeAppExeImpl(List<ClassBean> classBeanList, IWriteFileService writeFileService){

        for (ClassBean classBean : classBeanList){
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("class",classBean);
            varMap.put("fields",classBean.getFieldBeanList());
            varMap.put("methods",classBean.getMethodBeanList());
            varMap.put("imports",classBean.getImportClassList());

            String boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.EXE_IMPL.getTempFileName(), varMap);
            if(StringUtils.isEmpty(classBean.getRelationClassStr())){
                boContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.EXE_ABSTRACT.getTempFileName(), varMap);
            }

            WriteContentBean writeContentBean = WriteContentBean.builder()
                    .content(boContent)
                    .templateName(TemplateFileEnum.EXE.getTempFileName())
                    .humpClassName(classBean.getClassName())
                    .classPackageName(classBean.getPackageName())
                    .build();
            writeFileService.writeContent(writeContentBean);
        }
    }

    /**
     * 写app.exe--->interface 接口
     * @param interfaceBeanList
     * @param writeFileService
     */
    private void writeAppExeInterface(List<InterfaceBean> interfaceBeanList, IWriteFileService writeFileService){
        for (InterfaceBean interfaceBean : interfaceBeanList){
            Map<String, Object> varMap = new HashMap<>();
            varMap.put("class",interfaceBean);
            varMap.put("methods",interfaceBean.getMethodBeanList());
            varMap.put("imports",interfaceBean.getImportClassList());

            //借用gataway的模板
            String interfaceContent = freemarkerService.parseTplDynamicDDD(TemplateFileEnum.GATAWAY.getTempFileName(), varMap);
            WriteContentBean writeContentBean = WriteContentBean.builder().content(interfaceContent)
                    .templateName(TemplateFileEnum.EXE.getTempFileName())
                    .humpClassName(interfaceBean.getClassName())
                    .classPackageName(interfaceBean.getPackageName())
                    .build();
            writeFileService.writeContent(writeContentBean);

        }
    }
}
