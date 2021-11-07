package com.coderman.codemaker.app.dubbo;

import com.coderman.codemaker.app.WriteService;
import com.coderman.codemaker.bean.ClassContentBean;
import com.coderman.codemaker.bean.WriteContentBean;
import com.coderman.codemaker.config.AppServiceConfig;
import com.coderman.codemaker.config.ProjectTemplateDubboConfig;
import com.coderman.codemaker.enums.TemplateFileEnum;
import com.coderman.codemaker.app.IWriteFileService;
import com.coderman.codemaker.utils.Constant;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Description:
 * date: 2021/6/18
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 * 写持久层模块服务
 */
@Component(value = "dubboCommonWriteFileService")
public class DubboCommonWriteServiceImpl extends WriteService implements IWriteFileService {

    @Autowired
    private ProjectTemplateDubboConfig projectTemplateDubboConfig;

    @Autowired
    private AppServiceConfig appServiceConfig;

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        ClassContentBean classContentBean = writeContentBean.buildClassContentBean(projectTemplateDubboConfig.getModuleCommonPath());

        //写do class
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.DATA_OBJECT.getTempFileName())){
            classContentBean.setChildPackageName("infrast.dao.dataobject");
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
        //写infrast.dao.mapper
        if(writeContentBean.getTemplateName().equals(TemplateFileEnum.MAPPER_DDD.getTempFileName())){
            classContentBean.setChildPackageName("infrast.dao.mapper");
            writeRoute(classContentBean);
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
        String packagePath = packageName.replace(".", "/") ;
        packagePath = Constant.JAVA + "/" + packagePath + "/" + childPackageName;
        String fileName = humpClassName + classSuffix;
        return projectTemplateDubboConfig.getModuleCommonPath()  + packagePath + "/" + fileName;
    }


    /**
     *
     * @param humpClassName 驼峰式类名
     * @param classSuffix   文件后缀 egg：DTO.java
     * @return
     */
    private String getMapperFilePath(String humpClassName, String classSuffix) {
        String fileName = humpClassName + classSuffix;
        return projectTemplateDubboConfig.getModuleCommonPath() +  Constant.MAPPER + "/" + fileName;
    }

}
