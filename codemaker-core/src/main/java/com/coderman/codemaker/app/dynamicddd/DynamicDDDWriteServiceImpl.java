package com.coderman.codemaker.app.dynamicddd;

import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.ClassContentBean;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.config.ProjectTemplateDynamicDDDConfig;
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
 * date: 2021/6/29
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "dynamicDDDWriteServiceImpl")
public class DynamicDDDWriteServiceImpl extends WriteService implements IWriteFileService {


    @Autowired
    private AppServiceConfig appServiceConfig;

    @Autowired
    private ProjectTemplateDynamicDDDConfig projectTemplateDynamicDDDConfig;

    @Override
    public void writeContent(WriteContentBean writeContentBean) {

        //写domain.bo
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.bo");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateDynamicDDDConfig.getOutPath());
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
            classContentBean.setModulePath(projectTemplateDynamicDDDConfig.getOutPath());

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
            classContentBean.setModulePath(projectTemplateDynamicDDDConfig.getOutPath());

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
            classContentBean.setModulePath(projectTemplateDynamicDDDConfig.getOutPath());

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
            classContentBean.setChildPackageName("domain.gataway.impl");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName()+".impl");
            classContentBean.setModulePath(projectTemplateDynamicDDDConfig.getOutPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }
        //写domain.enum
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ENUM.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.enums");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateDynamicDDDConfig.getOutPath());

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
            classContentBean.setModulePath(projectTemplateDynamicDDDConfig.getOutPath());

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
            classContentBean.setModulePath(projectTemplateDynamicDDDConfig.getOutPath());

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
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName()+"Impl");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName()+".impl");
            if(writeContentBean.getHumpClassName().toLowerCase().contains(TemplateFileEnum.ACL.getTempFileName())){
                classContentBean.setChildPackageName("infrast.acl.impl");
            }else {
                classContentBean.setChildPackageName("infrast.adapter.impl");
            }
            classContentBean.setClassSuffix("");
            classContentBean.setModulePath(projectTemplateDynamicDDDConfig.getOutPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
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
            classContentBean.setModulePath(projectTemplateDynamicDDDConfig.getOutPath());

            writeClassFileV2(classContentBean);
        }

        //写app.cmd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.CMD.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setChildPackageName("app.command");
            classContentBean.setModulePath(projectTemplateDynamicDDDConfig.getOutPath());

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
            classContentBean.setModulePath(projectTemplateDynamicDDDConfig.getOutPath());

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



}
