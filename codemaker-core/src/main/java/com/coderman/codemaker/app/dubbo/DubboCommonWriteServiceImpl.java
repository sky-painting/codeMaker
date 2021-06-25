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
 * date: 2021/6/18
 *
 * @author fanchunshuai
 * @version 1.0.0
 * @since JDK 1.8
 * 写持久层模块服务
 */
@Component(value = "dubboCommonWriteFileService")
public class DubboCommonWriteServiceImpl implements IWriteFileService {

    @Autowired
    private ProjectTemplateDubboConfig projectTemplateDubboConfig;

    @Override
    public void writeContent(String templateName, String content, String humpClassName) {
        //写do class
        if(templateName.equals(TemplateFileEnum.DATA_OBJECT.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(content);
            classContentBean.setHumpClassName(humpClassName);
            classContentBean.setChildPackageName("dataobject");
            classContentBean.setClassSuffix("DO.java");
            writeDO(classContentBean);
        }
        //写mapper class
        if(templateName.equals(TemplateFileEnum.MAPPER.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(content);
            classContentBean.setHumpClassName(humpClassName);
            classContentBean.setChildPackageName("mapper");
            classContentBean.setClassSuffix("Mapper.java");
            writeMapper(classContentBean);
        }

        //写mapper.xml
        if(templateName.equals(TemplateFileEnum.MAPPER_XML.getTempFileName())){
            ClassContentBean classContentBean = new ClassContentBean();
            classContentBean.setClassContent(content);
            classContentBean.setHumpClassName(humpClassName);
            classContentBean.setChildPackageName("mapper");
            classContentBean.setClassSuffix("Mapper.xml");
            writeMapperXml(classContentBean);
        }
    }

    @Override
    public void writeAllContent(String humpClassName, Map<String, Object> varMap, String fast) {

    }

    @Override
    public void writeCommonContent(Map<String, Object> varMap, String fast) {

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
        String packageName = projectTemplateDubboConfig.getGlobalPackage();
        String packagePath = packageName.replace(".", "/") + "/common";
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
