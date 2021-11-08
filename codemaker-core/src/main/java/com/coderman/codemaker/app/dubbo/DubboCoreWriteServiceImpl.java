package com.coderman.codemaker.app.dubbo;

import com.coderman.codemaker.app.CommonWriteService;
import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.ClassContentBean;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.config.ProjectTemplateDubboConfig;
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
 * date: 2021/6/21
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "dubboCoreWriteFileService")
public class DubboCoreWriteServiceImpl  extends WriteService implements IWriteFileService {

    @Autowired
    private ProjectTemplateDubboConfig projectTemplateDubboConfig;


    @Autowired
    private AppServiceConfig appServiceConfig;

    @Autowired
    private CommonWriteService commonWriteService;

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateDubboConfig.getModuleCorePath());

        //写FacadeImpl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.FACADE_IMPL.getTempFileName())){
            classContentBean.setChildPackageName("facade.impl");
            classContentBean.setClassSuffix("FacadeImpl.java");
            writeRoute(classContentBean);
        }

        //写model.bo
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName())){
            classContentBean.setChildPackageName("model.bo");
            classContentBean.setClassSuffix("BO.java");
            writeClassFile(classContentBean);
        }
        //写domain.bo-ddd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName())){
            classContentBean.setChildPackageName("domain.bo");
            writeRoute(classContentBean);
        }

        //写convert
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CONVERT.getTempFileName())){
            writeClassFileV2(classContentBean);
        }

        //写service
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SERVICE.getTempFileName())){
            classContentBean.setChildPackageName("service");
            classContentBean.setClassSuffix("Service.java");
            writeClassFile(classContentBean);
        }


        //写serviceimpl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SERVICE_IMPL.getTempFileName())){
            classContentBean.setChildPackageName("service.impl");
            classContentBean.setClassSuffix("ServiceImpl.java");
            writeClassFile(classContentBean);
        }

        //写test
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.TEST.getTempFileName())){
            classContentBean.setChildPackageName("test");
            classContentBean.setClassSuffix("FacadeTest.java");
            writeClassTestFile(classContentBean);
        }

        //写Application类
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.APPLICATION.getTempFileName())){
            classContentBean.setChildPackageName("");
            writeClassFile(classContentBean);
        }

        //写Aop类
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.FACADE_AOP.getTempFileName())){
            classContentBean.setChildPackageName("aop");
            writeClassFile(classContentBean);
        }

        //写springapplication
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName())){
            classContentBean.setChildPackageName("utils");
            writeClassFile(classContentBean);
        }


        //指定服务类 or 工具类
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.APP_EVENT_PUBLISHER.getTempFileName())){
            classContentBean.setChildPackageName("utils");
            writeClassFile(classContentBean);
        }

        //写domain.enum
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ENUM.getTempFileName())){
            classContentBean.setChildPackageName("domain.enums");
            writeRoute(classContentBean);
        }

        //写domain.valueobject
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.VALUE_OBJECT.getTempFileName())){
            classContentBean.setChildPackageName("domain.valueobject");
            writeRoute(classContentBean);
        }
        //写domain.msgbody
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MESSAGE_BODY.getTempFileName())){
            classContentBean.setChildPackageName("domain.msgbody");
            writeRoute(classContentBean);
        }

        //写domain.event
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.EVENT_BODY.getTempFileName())){
            classContentBean.setChildPackageName("domain.event");
            writeRoute(classContentBean);
        }

        //写domain.gataway
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.GATAWAY.getTempFileName())){
            classContentBean.setChildPackageName("domain.gataway");
            writeRoute(classContentBean);
        }

        //写domain.gataway.impl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.GATAWAY_IMPL.getTempFileName())){
            classContentBean.setChildPackageName("gataway.impl");
            writeRoute(classContentBean);
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

        //写app.cmd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CMD.getTempFileName())){
            classContentBean.setChildPackageName("app.command");
            writeRoute(classContentBean);
        }
        //写app.exe
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.EXE.getTempFileName())){
            classContentBean.setChildPackageName("app.executor");
            classContentBean.setModulePath(projectTemplateDubboConfig.getModuleCorePath());
            writeRoute(classContentBean);
        }

        //写domain.factory
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.FACTORY.getTempFileName())){
            classContentBean.setChildPackageName("domain.factory");
            writeRoute(classContentBean);
        }
        //写app.listener
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MQ_LISTENER.getTempFileName())){
            classContentBean.setChildPackageName("app.listener");

        }

        //写infras.mq.consumer
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MQ_CONSUMER.getTempFileName())){
            classContentBean.setChildPackageName("infrast.mq.consumer");
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
            classContentBean.setChildPackageName("infrast.mq.handler");
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
            classContentBean.setChildPackageName("infrast.mq.producer");
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                classContentBean.setClassPackageName(appServiceConfig.getPackage()+".infrast.mq.producer");
                writeClassFileV2(classContentBean);
            }
        }


        //写BaseEvent
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.APP_EVENT_PUBLISHER.getTempFileName())){
            commonWriteService.writeBaseEvent(writeContentBean.getContent(),projectTemplateDubboConfig.getModuleCorePath());
        }
    }

    /**
     * 写class文件
     * @param classContentBean
     */
    public void writeClassFile(ClassContentBean classContentBean) {
        String filePath = getFilePath(classContentBean.getChildPackageName(), classContentBean.getHumpClassName(), classContentBean.getClassSuffix());
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
        String packagePath = packageName.replace(".", "/");
        packagePath = Constant.JAVA + "/" + packagePath + "/" + childPackageName;
        String fileName = humpClassName + classSuffix;
        return projectTemplateDubboConfig.getModuleCorePath()  + packagePath + "/" + fileName;
    }

    /**
     * 写测试文件
     * @param childPackageName
     * @param humpClassName
     * @param classSuffix
     * @return
     */
    public String getTestFilePath(String childPackageName, String humpClassName, String classSuffix) {
        String packageName = appServiceConfig.getPackage();
        String packagePath = packageName.replace(".", "/");
        packagePath = Constant.TEST_JAVA + "/" + packagePath + childPackageName;
        String fileName = humpClassName + classSuffix;
        return projectTemplateDubboConfig.getModuleCorePath()  + packagePath + "/" + fileName;
    }

    /**
     * 写class文件
     * @param classContentBean
     */
    public void writeClassTestFile(ClassContentBean classContentBean) {
        String filePath = getTestFilePath(classContentBean.getChildPackageName(), classContentBean.getHumpClassName(), classContentBean.getClassSuffix());
        try {
            FileUtils.write(new File(filePath), classContentBean.getClassContent(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
