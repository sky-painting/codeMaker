package com.tianhua.codemaker.app.dubbo;

import com.tianhua.codemaker.app.FunctionCodeAppWriteService;
import com.tianhua.codemaker.app.WriteService;
import com.tianhua.codemaker.bean.ClassContentBean;
import com.tianhua.codemaker.bean.GlobalConstant;
import com.tianhua.codemaker.bean.WriteContentBean;
import com.tianhua.codemaker.config.AppServiceConfig;
import com.tianhua.codemaker.config.ProjectTemplateDubboConfig;
import com.tianhua.codemaker.app.IWriteFileService;
import com.tianhua.codemaker.enums.TemplateFileEnum;
import com.tianhua.codemaker.utils.Constant;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Description:
 * date: 2021/6/21
 *
 * @author shenshuai
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
    private FunctionCodeAppWriteService functionCodeAppWriteService;

    private static final String moduleCode = "core";

    @Override
    public void writeContent(WriteContentBean writeContentBean) {
        ClassContentBean classContentBean = writeContentBean.buildClassContentBean(moduleCode, projectTemplateDubboConfig.getModuleCorePath());
        functionCodeAppWriteService.write(writeContentBean.getTemplateName(),moduleCode,classContentBean);

        //写自定义工具类
        if(writeContentBean.getTemplateName().equals(GlobalConstant.SINGLE_CLASS_COMMON)){
            writeRoute(classContentBean);
        }
        //写config文件
        if(writeContentBean.getTemplateName().equals(GlobalConstant.CONFIG)){
            writeConfig(classContentBean);
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


}
