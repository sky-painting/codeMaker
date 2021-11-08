package com.coderman.codemaker.app.cola;

import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.ClassContentBean;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.config.ProjectTemplateColaConfig;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.app.IWriteFileService;
import com.coderman.codemaker.utils.Constant;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Description:
 * date: 2021/7/6
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "colaInfrastWriteService")
public class ColaInfrastWriteServiceImpl extends WriteService implements IWriteFileService {
    @Autowired
    private ProjectTemplateColaConfig projectTemplateColaConfig;

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateColaConfig.getModuleInfrastPath());

        //写do class
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.DATA_OBJECT.getTempFileName())){
            classContentBean.setChildPackageName("dataobject");
            classContentBean.setClassSuffix("DO.java");
            writeDO(classContentBean);
        }
        //写mapper class
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MAPPER.getTempFileName())){
            classContentBean.setChildPackageName("mapper");
            classContentBean.setClassSuffix("Mapper.java");
            writeMapper(classContentBean);
        }

        //写mapper.xml
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MAPPER_XML.getTempFileName())){
            classContentBean.setChildPackageName("mapper");
            classContentBean.setClassSuffix("Mapper.xml");
            writeMapperXml(classContentBean);
        }

        //写domain.gataway.impl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.GATAWAY_IMPL.getTempFileName())){
            classContentBean.setChildPackageName("gatawayimpl");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName()+".gatawayimpl");

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                classContentBean.setClassPackageName(appServiceConfig.getPackage()+".infrast.gatawayimpl");
                writeClassFileV2(classContentBean);
            }
        }

        //写domain.repository.impl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.REPOSITORY_IMPL.getTempFileName())){
            classContentBean.setChildPackageName("repositoryimpl");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName()+".repositoryimpl");
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                classContentBean.setClassPackageName(appServiceConfig.getPackage()+".infrast.repositoryimpl");
                writeClassFileV2(classContentBean);
            }
        }


        //写infrast.acl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ACL.getTempFileName())){
            if(writeContentBean.getHumpClassName().toLowerCase().contains(TemplateFileEnum.ACL.getTempFileName())){
                classContentBean.setChildPackageName("infrast.acl");
            }else {
                classContentBean.setChildPackageName("infrast.adapter");
            }
            writeRoute(classContentBean);
        }
        //写infrast.acl.impl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ACL_IMPL.getTempFileName())){
            if(writeContentBean.getHumpClassName().toLowerCase().contains(TemplateFileEnum.ACL.getTempFileName())){
                classContentBean.setChildPackageName("infrast.acl.impl");
            }else {
                classContentBean.setChildPackageName("infrast.adapter.impl");
            }
            writeRoute(classContentBean);
        }

        //写acl.param
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ACL_PARAM.getTempFileName())){
            writeClassFileV2(classContentBean);
        }

        //写convert
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CONVERT.getTempFileName())){
            classContentBean.setClassPackageName(appServiceConfig.getPackage()+".infrast.dataconvert");
            writeClassFileV2(classContentBean);
        }


        //写serviceimpl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SERVICE_IMPL.getTempFileName())){
            classContentBean.setChildPackageName("infrast.service.impl");
            classContentBean.setClassSuffix("ServiceImpl.java");
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName().toLowerCase().endsWith("serviceimpl") ? writeContentBean.getHumpClassName() : writeContentBean.getHumpClassName()+"ServiceImpl");
            writeClassFile(classContentBean);
        }

        //写infras.mq.consumer
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MQ_CONSUMER.getTempFileName())){
            classContentBean.setChildPackageName("mq.consumer");
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                classContentBean.setClassPackageName(appServiceConfig.getPackage()+".infrast.mq.consumer");

                writeClassFileV2(classContentBean);
            }
        }

        //写infras.mq.handler
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MQ_HANDLER.getTempFileName())){
            classContentBean.setChildPackageName("mq.handler");
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                classContentBean.setClassPackageName(appServiceConfig.getPackage()+".infrast.mq.handler");
                writeClassFileV2(classContentBean);
            }
        }

        //写infras.mq.producer
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MQ_PRODUCER.getTempFileName())){
            classContentBean.setChildPackageName("mq.producer");
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                classContentBean.setClassPackageName(appServiceConfig.getPackage()+".infrast.mq.producer");
                writeClassFileV2(classContentBean);
            }
        }


        //写infrast.dao.mapper
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MAPPER_DDD.getTempFileName())){
            classContentBean.setChildPackageName("infrast.dao.mapper");
            writeRoute(classContentBean);
        }
        //写mapper.xml
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MAPPER_XML_DDD.getTempFileName())){
            classContentBean.setChildPackageName("mapper");
            classContentBean.setClassSuffix(".xml");
            writeMapperXml(classContentBean);
        }

    }

    /**
     * 写DO文件
     * @param classContentBean
     */
    public void writeDO(ClassContentBean classContentBean) {
        String filePath = getFilePath(classContentBean.getChildPackageName(), classContentBean.getHumpClassName(), classContentBean.getClassSuffix());
        try {
            FileUtils.write(new File(filePath), classContentBean.getClassContent(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写Mapper文件
     * @param classContentBean
     */
    public void writeMapper(ClassContentBean classContentBean) {
        String filePath = getFilePath(classContentBean.getChildPackageName(), classContentBean.getHumpClassName(), classContentBean.getClassSuffix());
        try {
            FileUtils.write(new File(filePath), classContentBean.getClassContent(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写Mapper.xml文件
     * @param classContentBean
     */
    public void writeMapperXml(ClassContentBean classContentBean) {
        String filePath = getMapperFilePath(classContentBean.getHumpClassName(), classContentBean.getClassSuffix());
        try {
            FileUtils.write(new File(filePath), classContentBean.getClassContent(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param childPackageName 最后一级子包名称
     * @param humpClassName 驼峰式类名
     * @param classSuffix   文件后缀 egg：DTO.java
     * @return
     */
    private String getFilePath(String childPackageName, String humpClassName, String classSuffix) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/") + "/infrast/dao";
        packagePath = Constant.JAVA + "/" + packagePath + "/" + childPackageName;
        String fileName = humpClassName + classSuffix;
        return projectTemplateColaConfig.getModuleInfrastPath()  + packagePath + "/" + fileName;
    }


    /**
     *
     * @param humpClassName 驼峰式类名
     * @param classSuffix   文件后缀 egg：DTO.java
     * @return
     */
    private String getMapperFilePath(String humpClassName, String classSuffix) {
        String fileName = humpClassName + classSuffix;
        return projectTemplateColaConfig.getModuleInfrastPath() +  Constant.MAPPER + "/" + fileName;
    }
}
