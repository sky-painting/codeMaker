package com.coderman.codemaker.app.cola;

import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.ClassContentBean;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.config.ProjectTemplateColaConfig;
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
 * date: 2021/7/6
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 */
@Component(value = "colaDomainWriteService")
public class ColaDomainWriteServiceImpl extends WriteService implements IWriteFileService {

    @Autowired
    private ProjectTemplateColaConfig projectTemplateColaConfig;


    @Override
    public void writeContent(WriteContentBean writeContentBean) {
         //写domain.bo-ddd
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.BUSINESS_OBJECT_DDD.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.bo");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleDomainPath());
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
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName().toLowerCase().endsWith("bo") ? writeContentBean.getHumpClassName() : writeContentBean.getHumpClassName()+"BO");
            classContentBean.setChildPackageName("model.bo");
            classContentBean.setClassSuffix("BO.java");
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleDomainPath());

            writeClassFile(classContentBean);
        }

        //写domain.enum
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.ENUM.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("domain.enums");
            classContentBean.setClassPackageName(writeContentBean.getClassPackageName());
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleDomainPath());

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
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleDomainPath());

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
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleDomainPath());

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
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleDomainPath());

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
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleDomainPath());

            //走默认的包生成方式
            if(StringUtils.isEmpty(classContentBean.getClassPackageName())){
                writeClassFile(classContentBean);
            }else {
                //走文档里的package包生成方式
                writeClassFileV2(classContentBean);
            }
        }
        //写service
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.SERVICE.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(writeContentBean.getContent());
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName());
            classContentBean.setChildPackageName("service");
            classContentBean.setClassSuffix("Service.java");
            classContentBean.setHumpClassName(writeContentBean.getHumpClassName().toLowerCase().endsWith("service") ? writeContentBean.getHumpClassName() : writeContentBean.getHumpClassName()+"Service");
            classContentBean.setModulePath(projectTemplateColaConfig.getModuleDomainPath());
            writeClassFile(classContentBean);
        }

    }

    @Override
    public void writeAllContent(String humpClassName, Map<String, Object> varMap, String fast) {

    }

    @Override
    public void writeCommonContent(Map<String, Object> varMap, String fast) {

    }




}
