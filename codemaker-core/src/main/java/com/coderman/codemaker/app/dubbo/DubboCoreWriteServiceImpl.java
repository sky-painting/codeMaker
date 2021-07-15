package com.coderman.codemaker.app.dubbo;

import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.ClassContentBean;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.config.ProjectTemplateDubboConfig;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.service.IWriteFileService;
import com.coderman.codemaker.utils.Constant;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

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

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        //写FacadeImpl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.FACADE_IMPL.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("facade.impl");
            classContentBean.setClassSuffix("FacadeImpl.java");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateDubboConfig.getModuleCorePath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写model.bo
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("model.bo");
            classContentBean.setClassSuffix("BO.java");
            writeClassFile(classContentBean);
        }
        //写domain.bo-ddd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.bo");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateDubboConfig.getModuleCorePath());
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }


        //写convert
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CONVERT.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateDubboConfig.getModuleCorePath());
            writeClassFileV2(classContentBean);
        }

        //写service
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SERVICE.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("service");
            classContentBean.setClassSuffix("Service.java");
            writeClassFile(classContentBean);
        }


        //写serviceimpl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SERVICE_IMPL.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("service.impl");
            classContentBean.setClassSuffix("ServiceImpl.java");
            writeClassFile(classContentBean);
        }

        //写test
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.TEST.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("test");
            classContentBean.setClassSuffix("FacadeTest.java");
            writeClassTestFile(classContentBean);
        }

        //写Application类
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.APPLICATION.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("");
            classContentBean.setClassSuffix("");
            writeClassFile(classContentBean);
        }

        //写Aop类
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.FACADE_AOP.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("aop");
            classContentBean.setClassSuffix("");
            writeClassFile(classContentBean);
        }

        //写springapplication
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("utils");
            classContentBean.setClassSuffix("");
            writeClassFile(classContentBean);
        }

        //写domain.enum
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ENUM.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.enums");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateDubboConfig.getModuleCorePath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写domain.valueobject
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.VALUE_OBJECT.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.valueobject");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateDubboConfig.getModuleCorePath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }
        //写domain.msgbody
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MESSAGE_BODY.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.msgbody");
            classContentBean.setClassSuffix("");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateDubboConfig.getModuleCorePath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }

        }

        //写domain.gataway
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.GATAWAY.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.gataway");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateDubboConfig.getModuleCorePath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写domain.gataway.impl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.GATAWAY_IMPL.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName()+"Impl");
            classContentBean.setChildPackageName("gataway.impl");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName()+".impl");
            classContentBean.setModulePath(projectTemplateDubboConfig.getModuleCorePath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写infrast.acl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ACL.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            if(writeContentBean.getHumpClassName().toLowerCase().contains(TemplateFileEnum.ACL.getTempFileName())){
                classContentBean.setChildPackageName("infrast.acl");
            }else {
                classContentBean.setChildPackageName("infrast.adapter");
            }
            classContentBean.setClassSuffix("");
            classContentBean.setModulePath(projectTemplateDubboConfig.getModuleCorePath());
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }
        //写infrast.acl.impl
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ACL_IMPL.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            if(writeContentBean.getHumpClassName().toLowerCase().contains(TemplateFileEnum.ACL.getTempFileName())){
                classContentBean.setChildPackageName("infrast.acl.impl");
            }else {
                classContentBean.setChildPackageName("infrast.adapter.impl");
            }
            classContentBean.setClassSuffix("");
            classContentBean.setModulePath(projectTemplateDubboConfig.getModuleCorePath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                if(!classContentBean.getClassPackageName().endsWith("impl") || !classContentBean.getClassPackageName().contains("impl")){
                    classContentBean.setClassPackageName(classContentBean.getClassPackageName()+".impl");
                }
                if(!classContentBean.getHumpClassName().endsWith("impl") || !classContentBean.getHumpClassName().contains("impl")){
                    classContentBean.setHumpClassName(classContentBean.getHumpClassName()+"Impl");
                }
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写acl.param
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ACL_PARAM.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateDubboConfig.getModuleCorePath());
            writeClassFileV2(classContentBean);
        }

        //写app.cmd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CMD.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setChildPackageName("app.command");
            classContentBean.setModulePath(projectTemplateDubboConfig.getModuleCorePath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }
        //写app.exe
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.EXE.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setChildPackageName("app.executor");
            classContentBean.setModulePath(projectTemplateDubboConfig.getModuleCorePath());
            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }

        //写domain.factory
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.FACTORY.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.factory");
            classContentBean.setClassSuffix("");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateDubboConfig.getModuleCorePath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }
    }

    @Override
    public void writeAllContent(String humpClassName, Map<String, Object> varMap, String fast) {

    }

    @Override
    public void writeCommonContent(Map<String, Object> varMap, String fast) {

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
        String packagePath = packageName.replace(".", "/") + "/core";
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
        String packagePath = packageName.replace(".", "/")+"/core/";
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
