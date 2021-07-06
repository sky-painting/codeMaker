package com.coderman.codemaker.app.dubbo;

import com.coderman.codemaker.bean.ClassContentBean;
import com.coderman.codemaker.config.ProjectTemplateDubboConfig;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.service.IWriteFileService;
import com.coderman.codemaker.utils.Constant;
import org.apache.commons.io.FileUtils;
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
public class DubboCoreWriteServiceImpl implements IWriteFileService {

    @Autowired
    private ProjectTemplateDubboConfig projectTemplateDubboConfig;

    @Override
    public void writeContent(String templateName, String content, String humpClassName) {
        //写FacadeImpl
        if(templateName.equals(TemplateFileEnum.FACADE_IMPL.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(content);
            classContentBean.setHumpClassName(humpClassName);
            classContentBean.setChildPackageName("facade.impl");
            classContentBean.setClassSuffix("FacadeImpl.java");
            writeClassFile(classContentBean);
        }

        //写model.bo
        if(templateName.equals(TemplateFileEnum.BUSINESS_OBJECT.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(content);
            classContentBean.setHumpClassName(humpClassName);
            classContentBean.setChildPackageName("model.bo");
            classContentBean.setClassSuffix("BO.java");
            writeClassFile(classContentBean);
        }

        //写converter
        if(templateName.equals(TemplateFileEnum.CONVERT.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(content);
            classContentBean.setHumpClassName(humpClassName);
            classContentBean.setChildPackageName("convert");
            classContentBean.setClassSuffix("Converter.java");
            writeClassFile(classContentBean);
        }

        //写service
        if(templateName.equals(TemplateFileEnum.SERVICE.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(content);
            classContentBean.setHumpClassName(humpClassName);
            classContentBean.setChildPackageName("service");
            classContentBean.setClassSuffix("Service.java");
            writeClassFile(classContentBean);
        }


        //写serviceimpl
        if(templateName.equals(TemplateFileEnum.SERVICE_IMPL.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(content);
            classContentBean.setHumpClassName(humpClassName);
            classContentBean.setChildPackageName("service.impl");
            classContentBean.setClassSuffix("ServiceImpl.java");
            writeClassFile(classContentBean);
        }

        //写test
        if(templateName.equals(TemplateFileEnum.TEST.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(content);
            classContentBean.setHumpClassName(humpClassName);
            classContentBean.setChildPackageName("test");
            classContentBean.setClassSuffix("FacadeTest.java");
            writeClassTestFile(classContentBean);
        }

        //写Application类
        if(templateName.equals(TemplateFileEnum.APPLICATION.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(content);
            classContentBean.setHumpClassName(humpClassName);
            classContentBean.setChildPackageName("");
            classContentBean.setClassSuffix("");
            writeClassFile(classContentBean);
        }

        //写Application类
        if(templateName.equals(TemplateFileEnum.FACADE_AOP.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(content);
            classContentBean.setHumpClassName(humpClassName);
            classContentBean.setChildPackageName("aop");
            classContentBean.setClassSuffix("");
            writeClassFile(classContentBean);
        }

        //写serviceimpl
        if(templateName.equals(TemplateFileEnum.SPRING_APPLICATION_CONTEXT.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(content);
            classContentBean.setHumpClassName(humpClassName);
            classContentBean.setChildPackageName("utils");
            classContentBean.setClassSuffix("");
            writeClassFile(classContentBean);
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
        String packageName = projectTemplateDubboConfig.getGlobalPackage();
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
        String packageName = projectTemplateDubboConfig.getGlobalPackage();
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
